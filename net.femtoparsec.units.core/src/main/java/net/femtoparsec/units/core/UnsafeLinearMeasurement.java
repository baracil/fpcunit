package net.femtoparsec.units.core;

import net.femtoparsec.units.api.Unit;

public class UnsafeLinearMeasurement extends LinearMeasurementBase<UnsafeQuantity,UnsafeLinearMeasurement> {

  public UnsafeLinearMeasurement(double value, Unit<UnsafeQuantity> unit) {
    super(value, unit);
  }

  @Override
  protected UnsafeLinearMeasurement getThis() {
    return this;
  }

  @Override
  protected UnsafeLinearMeasurement createWith(double value, Unit<UnsafeQuantity> unit) {
    return new UnsafeLinearMeasurement(value,unit);
  }

}
