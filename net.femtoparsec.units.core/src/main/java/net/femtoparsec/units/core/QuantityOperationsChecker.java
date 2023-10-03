package net.femtoparsec.units.core;

import net.femtoparsec.units.api.Quantity;
import net.femtoparsec.units.core._private.EnabledOperationsChecker;
import net.femtoparsec.units.core._private.NopOperationsChecker;

/**
 * @author bastien.a
 */
public enum QuantityOperationsChecker implements OperationsChecker {
  INSTANCE;

  public static boolean USER_CHECK_ENABLED = false;

  @Override
  public void checkMultiply(Quantity q1, Quantity q2, Quantity target) {
    Holder.CHECKER.checkMultiply(q1, q2, target);
  }

  @Override
  public void checkDivided(Quantity q1, Quantity q2, Quantity target) {
    Holder.CHECKER.checkDivided(q1, q2, target);
  }

  @Override
  public void checkInverted(Quantity quantity, Quantity target) {
    Holder.CHECKER.checkInverted(quantity, target);
  }

  @Override
  public void checkSquare(Quantity quantity, Quantity target) {
    Holder.CHECKER.checkSquare(quantity, target);
  }

  @Override
  public void checkSquareRoot(Quantity quantity, Quantity target) {
    Holder.CHECKER.checkSquareRoot(quantity, target);
  }

  @Override
  public void checkCubic(Quantity quantity, Quantity target) {
    Holder.CHECKER.checkCubic(quantity, target);
  }

  private static class Holder {
    private static final OperationsChecker CHECKER;
    static {
      final var enabled = USER_CHECK_ENABLED || Boolean.getBoolean("units-debug");
      CHECKER = enabled?new EnabledOperationsChecker():new NopOperationsChecker();
    }
  }

}
