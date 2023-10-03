package net.femtoparsec.units.core;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.femtoparsec.units.api.Dimension;
import net.femtoparsec.units.api.Measurement;
import net.femtoparsec.units.api.Quantity;
import net.femtoparsec.units.api.Unit;

import java.io.ObjectStreamException;
import java.io.Serial;
import java.util.List;
import java.util.Optional;

/**
 * The physical quantities
 *
 * @author Bastien Aracil
 */
@RequiredArgsConstructor
public abstract class QuantityBase<Q extends Quantity, U extends Unit<Q>> implements Quantity {

  @NonNull
  @Getter(onMethod = @__({@Override}))
  private final Dimension dimension;

  public QuantityBase(@NonNull String encodedDimension) {
    this(new Dimension(encodedDimension));
  }


  protected abstract QuantityUnits<Q, U> getQuantityUnits();


  @Override
  public Optional<U> findUnit(String name) {
    return getQuantityUnits().findUnit(name);
  }

  @Override
  @NonNull
  public U getUnit(String name) {
    return getQuantityUnits().getUnit(name);
  }


  @Override
  public Optional<? extends Measurement<Q>> safeParseMeasurement(String measurementAsString) {
    return MeasurementParser.parse(measurementAsString)
        .flatMap(
            p -> this.findUnit(p.unit())
                .map(u -> u.create(p.value()))
        );
  }

  protected abstract List<U> getUnits();


  protected abstract Q getThis();

  @Override
  public String toString() {
    return this.getClass().getSimpleName();
  }

  @Serial
  protected Object writeReplace() throws ObjectStreamException {
    return QuantitySerializableForm.create(this);
  }

}
