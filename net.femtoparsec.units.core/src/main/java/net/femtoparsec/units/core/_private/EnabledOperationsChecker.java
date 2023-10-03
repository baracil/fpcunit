package net.femtoparsec.units.core._private;

import net.femtoparsec.units.api.*;
import net.femtoparsec.units.core.OperationsChecker;

import java.util.function.BiFunction;
import java.util.function.Function;

public class EnabledOperationsChecker implements OperationsChecker {

  @Override
  public void checkMultiply(Quantity q1, Quantity q2, Quantity target) {
    checkBinary(q1, q2, EnabledOperationsChecker::getMultiplyDimension, target, "*");
  }

  @Override
  public void checkDivided(Quantity q1, Quantity q2, Quantity target) {
    checkBinary(q1,q2, EnabledOperationsChecker::getDividedDimension, target, "/");
  }

  @Override
  public void checkInverted(Quantity quantity, Quantity target) {
    checkUnary(quantity, target, EnabledOperationsChecker::getInvertedDimension, Dimensioned::getDimension, "^-1");
  }

  @Override
  public void checkSquare(Quantity quantity, Quantity target) {
    checkUnary(quantity, target, EnabledOperationsChecker::getSquareDimension, Dimensioned::getDimension, "^2");
  }

  @Override
  public void checkSquareRoot(Quantity quantity, Quantity target) {
    checkUnary(quantity, target, Dimensioned::getDimension, EnabledOperationsChecker::getSquareDimension, "^0.5");
  }

  @Override
  public void checkCubic(Quantity unit, Quantity target) {
    checkUnary(unit, target, EnabledOperationsChecker::getCubicDimension, Dimensioned::getDimension, "^3");
  }


  private static void checkBinary(Quantity q1, Quantity q2, BiFunction<Quantity,Quantity, Dimension> dimensionGetter, Quantity result, String operationName) {
      final Dimension dimension1 = dimensionGetter.apply(q1, q2);
      final Dimension dimension2 = result.getDimension();

      if (!dimension1.equals(dimension2)) {
        throw new InvalidBinaryOperation(q1.getDimension(),q2.getDimension(),operationName,result.getDimension());
      }
  }

  private static void checkUnary(Quantity q1, Quantity q2, Function<Quantity, Dimension> dimensionGetter1, Function<Quantity, Dimension> dimensionGetter2, String operationName) {
      final Dimension dimension1 = dimensionGetter1.apply(q1);
      final Dimension dimension2 = dimensionGetter2.apply(q2);

      if (!dimension1.equals(dimension2)) {
        throw new InvalidUnaryOperation(q1.getDimension(),operationName,q2.getDimension());
      }
  }

  public static Dimension getSquareDimension(Quantity quantity) {
    return quantity.getDimension().scale(2);
  }

  public static Dimension getCubicDimension(Quantity quantity) {
    return quantity.getDimension().scale(3);
  }

  public static Dimension getMultiplyDimension(Quantity...multiplicators) {
    return Dimension.sum(multiplicators);
  }

  public static Dimension getDividedDimension(Quantity q1, Quantity q2) {
    return q1.getDimension().subtract(q2.getDimension());
  }

  public static Dimension getInvertedDimension(Quantity quantity) {
    return quantity.getDimension().negate();
  }



}
