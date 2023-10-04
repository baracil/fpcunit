package net.femtoparsec.units.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.femtoparsec.units.api.Dimension;
import net.femtoparsec.units.api.Measurement;
import net.femtoparsec.units.api.Quantity;
import net.femtoparsec.units.api.UnknownUnitException;

import java.util.Optional;

@RequiredArgsConstructor
@Getter
public class UnsafeQuantity implements Quantity {

  private final Dimension dimension;

  @Override
  public String getName() {
    return "Unsafe : "+dimension.getEncoded();
  }

  @Override
  public Optional<UnsafeUnit> findUnit(String name) {
    if (UnsafeUnit.NAME.equals(name)) {
      return Optional.of(new UnsafeUnit(this));
    }
    return Optional.empty();
  }

  @Override
  public UnsafeUnit getUnit(String name) {
    return findUnit(name).orElseThrow(() -> new UnknownUnitException(name, this));
  }

  @Override
  public Optional<? extends Measurement<?>> safeParseMeasurement(String measurementAsString) {
    throw new UnsupportedOperationException("Cannot parse unsafe measurement");
  }
}
