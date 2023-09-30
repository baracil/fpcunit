package net.femtoparsec.units.core;


import lombok.Getter;
import lombok.NonNull;
import net.femtoparsec.units.api.*;

import java.io.ObjectStreamException;
import java.io.Serial;
import java.io.Serializable;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Bastien Aracil
 */
@Getter
public abstract class MeasurementBase<Q extends Quantity<Q, U, M>, U extends Unit<Q, U, M>, M extends Measurement<Q, U, M>> implements Measurement<Q, U, M>, Serializable {

  private final U unit;

  private final double value;

  /**
   * Value of the unit is S.I. (used for equality test)
   */
  private final double valueInSI;

  protected MeasurementBase(double value, @NonNull U unit) {
    this.unit = unit;
    this.value = value;
    this.valueInSI = unit.convertToSI(value);
  }

  protected static String toString(double value, @NonNull Unit<?, ?, ?> unit) {
    return value + (unit.getName().isEmpty() ? "" : " " + unit.getName());
  }

  @Override
  public Dimension getDimension() {
    return unit.getDimension();
  }

  @Override
  public M convert(U newUnit) {
    if (newUnit == this.unit) {
      return this.getThis();
    }
    return newUnit.create(this.getValueInUnit(newUnit));
  }

  @Override
  public M sameUnitAs(M reference) {
    return convert(reference.getUnit());
  }

  @Override
  public M inAdaptedUnit() {
    return UnitUtils.adaptUnit(getThis());
  }

  @Override
  public M inAdaptedUnit(U unitForZero) {
    return UnitUtils.adaptUnit(getThis(), unitForZero);
  }

  @Override
  public M inAdaptedUnit(U unitForZero, Predicate<? super U> unitSelector) {
    return UnitUtils.adaptUnit(getThis(), unitForZero, unitSelector);
  }

  @Override
  public M inAdaptedUnit(Predicate<? super U> unitSelector) {
    return UnitUtils.adaptUnit(getThis(), unitSelector);
  }

  @Override
  public M inAdaptedUnit(UnitSystem unitSystem) {
    return UnitUtils.adaptUnit(getThis(), unitSystem);
  }

  @Override
  public M inAdaptedUnit(U unitForZero, UnitSystem unitSystem) {
    return UnitUtils.adaptUnit(getThis(), unitForZero, unitSystem);
  }

  @Override
  public M convertToSI() {
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
  public boolean isGreaterThan(M other) {
    return this.compareTo(other) > 0;
  }

  @Override
  public boolean isGreaterOrEqualTo(M other) {
    return this.compareTo(other) >= 0;
  }

  @Override
  public boolean isLowerThan(M other) {
    return other.isGreaterThan(getThis());
  }

  @Override
  public boolean isLowerOrEqualTo(M other) {
    return other.isGreaterOrEqualTo(getThis());
  }

  @Override
  public double getValueInUnit(U otherUnit) {
    if (otherUnit == this.unit.getReferenceSI()) {
      return this.valueInSI;
    }
    return this.unit.convertTo(this.value, otherUnit);
  }

  protected abstract M getThis();

  @Override
  public String toString() {
    return toString(this.value, this.unit);
  }

  @Override
  public M max(double value) {
    if (this.value >= value) {
      return getThis();
    }
    return unit.create(value);
  }

  @Override
  public M max(M other) {
    if (this.valueInSI > other.getValueInSI()) {
      return getThis();
    }
    return other;
  }

  @Override
  public M min(double value) {
    if (this.value <= value) {
      return getThis();
    }
    return unit.create(value);
  }

  @Override
  public M min(M other) {
    if (this.valueInSI <= other.getValueInSI()) {
      return getThis();
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

    if (o instanceof MeasurementBase<?, ?, ?> that) {
      final var thisQuantity = this.unit.getQuantity();
      final var thatQuantity = that.unit.getQuantity();

      return thisQuantity.equals(thatQuantity) && Double.compare(that.valueInSI, valueInSI) == 0;
    }
    return false;
  }

  @Override
  public int compareTo(M o) {
    return Double.compare(this.getValueInSI(), o.getValueInSI());
  }

  @Override
  public boolean equals(M other, double error) {
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
  public M round(U unit, int nbDecimals) {
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
