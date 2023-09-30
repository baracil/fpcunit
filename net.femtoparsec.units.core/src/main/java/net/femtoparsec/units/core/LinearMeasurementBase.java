package net.femtoparsec.units.core;

import net.femtoparsec.units.api.LinearMeasurement;
import net.femtoparsec.units.api.Quantity;
import net.femtoparsec.units.api.Unit;

/**
 * @author Bastien Aracil
 */
public abstract class LinearMeasurementBase<Q extends Quantity<Q, U, M>, U extends Unit<Q, U, M>, M extends LinearMeasurement<Q, U, M>> extends MeasurementBase<Q, U, M> implements LinearMeasurement<Q, U, M> {

  protected LinearMeasurementBase(double value, U unit) {
    super(value, unit);
  }


  @Override
  public M add(M other) {
    final var unit = getUnit();
    return unit.create(getValue() + other.getValueInUnit(unit));
  }

  @Override
  public M subtract(M other) {
    final var unit = getUnit();
    return unit.create(getValue() - other.getValueInUnit(unit));
  }

  @Override
  public M negate() {
    return scale(-1);
  }

  @Override
  public M scale(double factor) {
    return getUnit().create(getValue() * factor);
  }

  @Override
  public M abs() {
    final var value = this.getValue();
    if (value >= 0) {
      return getThis();
    }
    return getUnit().create(-value);
  }

  @Override
  public double divide(M other) {
    return this.getValueInSI() / other.getValueInSI();
  }



}
