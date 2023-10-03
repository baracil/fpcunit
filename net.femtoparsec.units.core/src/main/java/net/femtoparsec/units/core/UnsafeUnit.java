package net.femtoparsec.units.core;

import lombok.Getter;
import net.femtoparsec.units.api.Measurement;

public class UnsafeUnit extends UnitBase<UnsafeQuantity> {

  public static final String NAME = "unsafe";

  @Getter
  private final UnsafeQuantity quantity;

  public UnsafeUnit(UnsafeQuantity quantity) {
    super(NAME);
    this.quantity = quantity;
  }

  @Override
  public Measurement<UnsafeQuantity> create(double value) {
    return new UnsafeLinearMeasurement(value,this);
  }
}
