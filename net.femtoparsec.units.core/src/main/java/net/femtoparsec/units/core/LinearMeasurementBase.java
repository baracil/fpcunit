package net.femtoparsec.units.core;

import net.femtoparsec.units.api.LinearMeasurement;
import net.femtoparsec.units.api.NamedQuantity;
import net.femtoparsec.units.api.Quantity;
import net.femtoparsec.units.api.Unit;

import java.util.Optional;

/**
 * @author Bastien Aracil
 */
public abstract class LinearMeasurementBase<Q extends Quantity, M extends LinearMeasurementBase<Q, M>> extends MeasurementBase<Q, M> implements LinearMeasurement<Q> {

  protected LinearMeasurementBase(double value, Unit<Q> unit) {
    super(value, unit);
  }

  @Override
  public UnsafeLinearMeasurement unsafeMultiply(LinearMeasurement<?> other) {
    final var dims = this.getDimension().add(other.getDimension());
    final var unit = new UnsafeUnit(new UnsafeQuantity(dims));
    return new UnsafeLinearMeasurement(this.getValueInSI() * other.getValueInSI(), unit);
  }

  @Override
  public LinearMeasurement<?> unsafeDivide(LinearMeasurement<?> other) {
    final var dims = this.getDimension().subtract(other.getDimension());
    final var unit = new UnsafeUnit(new UnsafeQuantity(dims));
    return new UnsafeLinearMeasurement(this.getValueInSI() / other.getValueInSI(), unit);
  }

  @Override
  public LinearMeasurement<?> unsafeSquare(LinearMeasurement<?> other) {
    final var dims = this.getDimension().scale(2);
    final var unit = new UnsafeUnit(new UnsafeQuantity(dims));
    final var value = this.getValueInSI();
    return new UnsafeLinearMeasurement(value*value, unit);
  }

  @Override
  public LinearMeasurement<?> unsafeCubic(LinearMeasurement<?> other) {
    final var dims = this.getDimension().scale(3);
    final var unit = new UnsafeUnit(new UnsafeQuantity(dims));
    final var value = this.getValueInSI();
    return new UnsafeLinearMeasurement(value*value*value, unit);
  }


  @Override
  public M add(LinearMeasurement<Q> other) {
    final var unit = getUnit();
    return createWith(getValue() + other.getValueInUnit(unit), unit);
  }

  @Override
  public M subtract(LinearMeasurement<Q> other) {
    final var unit = getUnit();
    return createWith(getValue() - other.getValueInUnit(unit), unit);
  }

  @Override
  public M negate() {
    return scale(-1);
  }

  @Override
  public M scale(double factor) {
    return createWith(getValue() * factor, getUnit());
  }

  @Override
  public M abs() {
    final var value = this.getValue();
    if (value >= 0) {
      return getThis();
    }
    return createWith(-value, getUnit());
  }

  @Override
  public double divide(LinearMeasurement<Q> other) {
    return this.getValueInSI() / other.getValueInSI();
  }

}
