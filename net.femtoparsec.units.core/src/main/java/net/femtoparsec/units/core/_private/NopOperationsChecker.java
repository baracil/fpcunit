package net.femtoparsec.units.core._private;

import net.femtoparsec.units.api.Quantity;
import net.femtoparsec.units.core.OperationsChecker;

public class NopOperationsChecker implements OperationsChecker {
  @Override
  public void checkMultiply(Quantity q1, Quantity q2, Quantity target) {}

  @Override
  public void checkDivided(Quantity q1, Quantity q2, Quantity target) {}

  @Override
  public void checkInverted(Quantity quantity, Quantity target) {}

  @Override
  public void checkSquare(Quantity quantity, Quantity target) {}

  @Override
  public void checkSquareRoot(Quantity quantity, Quantity target) {}

  @Override
  public void checkCubic(Quantity quantity, Quantity target) {}
}
