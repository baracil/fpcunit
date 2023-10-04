package net.femtoparsec.units.core;


import lombok.Getter;
import lombok.NonNull;
import net.femtoparsec.units.api.*;

import java.io.ObjectStreamException;
import java.io.Serial;
import java.io.Serializable;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author Bastien Aracil
 */
@Getter
public abstract class MeasurementBase<Q extends Quantity, M extends MeasurementBase<Q,M>> implements Measurement<Q>, Serializable {

  private final Unit<Q> unit;

  private final double value;

  /**
   * Value of the unit is S.I. (used for equality test)
   */
  private final double valueInSI;

  protected MeasurementBase(double value, @NonNull Unit<Q> unit) {
    this.unit = unit;
    this.value = value;
    this.valueInSI = unit.convertToSI(value);
  }

  protected abstract M getThis();

  protected abstract M createWith(double value, Unit<Q> unit);

  protected static String toString(double value, @NonNull Unit<?> unit) {
    return value + (unit.getName().isEmpty() ? "" : " " + unit.getName());
  }

  @Override
  public Dimension getDimension() {
    return unit.getDimension();
  }

  @Override
  public M convert(Unit<Q> newUnit) {
    if (newUnit.equals(this.unit)) {
      return getThis();
    }
    return createWith(this.getValueInUnit(newUnit),newUnit);
  }

  @Override
  public <NQ extends NamedQuantity<NM>, NM extends Measurement<NQ>> Optional<NM> tryAs(NQ quantity) {
    if (this.getQuantity().hasSameDimension(quantity)) {
      return Optional.ofNullable(quantity.createWithSI(getValueInSI()));
    }
    return Optional.empty();
  }

  @Override
  public <NQ extends NamedQuantity<NM>, NM extends Measurement<NQ>> NM as(NQ quantity) {
    return tryAs(quantity).orElseThrow(() -> new IncompatibleDimension(this.getDimension(), quantity.getDimension()));
  }


  @Override
  public M sameUnitAs(Measurement<Q> reference) {
    return convert(reference.getUnit());
  }

  @Override
  public Measurement<Q> convertToSI() {
    return convert(this.unit.getReferenceSI());
  }

  @Override
  public boolean isNaN() {
    return Double.isNaN(value);
  }

  @Override
  public boolean isFinite() {
    return Double.isFinite(value);
  }

  @Override
  public boolean isGreaterThan(Measurement<Q> other) {
    return this.compareTo(other) > 0;
  }

  @Override
  public boolean isGreaterOrEqualTo(Measurement<Q> other) {
    return this.compareTo(other) >= 0;
  }

  @Override
  public boolean isLowerThan(Measurement<Q> other) {
    return other.isGreaterThan(this);
  }

  @Override
  public boolean isLowerOrEqualTo(Measurement<Q> other) {
    return other.isGreaterOrEqualTo(this);
  }

  @Override
  public double getValueInUnit(Unit<Q> otherUnit) {
    if (otherUnit.isSI()) {
      return this.valueInSI;
    }
    return this.unit.convertTo(this.value, otherUnit);
  }

  @Override
  public String toString() {
    return toString(this.value, this.unit);
  }

  @Override
  public Measurement<Q> max(double value) {
    if (this.value >= value) {
      return this;
    }
    return unit.create(value);
  }

  @Override
  public Measurement<Q> max(Measurement<Q> other) {
    if (this.valueInSI > other.getValueInSI()) {
      return this;
    }
    return other;
  }

  @Override
  public Measurement<Q> min(double value) {
    if (this.value <= value) {
      return this;
    }
    return unit.create(value);
  }

  @Override
  public Measurement<Q> min(Measurement<Q> other) {
    if (this.valueInSI <= other.getValueInSI()) {
      return this;
    }
    return other;
  }

  @Override
  public String toPrettyString() {
    return toPrettyString("%e");
  }

  @Override
  public String toPrettyString(String format) {
    return toPrettyString(d -> String.format(format, d));
  }

  @Override
  public String toPrettyString(Function<Double, String> converter) {
    final String value = converter.apply(this.getValue());
    final String unit = this.getUnit().getPrettyName();
    if (unit.isEmpty()) {
      return value;
    }
    return value + " " + unit;
  }

  @Override
  public String encode() {
    return this.toText();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;

    if (o instanceof MeasurementBase<?,?> that) {
      final var thisQuantity = this.unit.getQuantity();
      final var thatQuantity = that.unit.getQuantity();

      return thisQuantity.equals(thatQuantity) && Double.compare(that.valueInSI, valueInSI) == 0;
    }
    return false;
  }

  @Override
  public int compareTo(Measurement<Q> o) {
    return Double.compare(this.getValueInSI(), o.getValueInSI());
  }

  @Override
  public boolean equals(Measurement<Q> other, double error) {
    return Math.abs(this.value - other.getValueInUnit(this.unit)) <= error;
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    temp = Double.doubleToLongBits(valueInSI);
    result = (int) (temp ^ (temp >>> 32));
    result = 31 * result + this.unit.getQuantity().hashCode();
    return result;
  }

  @Serial
  protected Object writeReplace() throws ObjectStreamException {
    return MeasurementSerializableForm.create(this);
  }

  @Override
  public Measurement<Q> round(Unit<Q> unit, int nbDecimals) {
    double value = this.getValueInUnit(unit);
    for (int i = 0; i < nbDecimals; i++) {
      value *= 10;
    }
    value = Math.round(value);
    for (int i = 0; i < nbDecimals; i++) {
      value *= 0.1;
    }
    return unit.create(value);
  }

}
