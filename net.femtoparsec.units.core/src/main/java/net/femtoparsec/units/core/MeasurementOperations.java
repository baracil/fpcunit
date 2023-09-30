package net.femtoparsec.units.core;

import net.femtoparsec.units.api.LinearMeasurement;
import net.femtoparsec.units.api.Measurement;
import net.femtoparsec.units.api.Quantity;
import net.femtoparsec.units.api.Unit;

/**
 * @author Bastien Aracil
 */
public class MeasurementOperations {


  public static <Q extends Quantity<Q, U, M>, U extends Unit<Q, U, M>, M extends LinearMeasurement<Q, U, M>> M scale(M measurement, double factor) {
    return measurement.getUnit().create(measurement.getValue() * factor);
  }

  public static <Q extends Quantity<Q, U, M>, U extends Unit<Q, U, M>, M extends Measurement<Q, U, M>> M min(M measurement, double value) {
    return measurement.getUnit().create(Math.min(value, measurement.getValue()));
  }

  public static <Q extends Quantity<Q, U, M>, U extends Unit<Q, U, M>, M extends Measurement<Q, U, M>> M max(M measurement, double value) {
    return measurement.getUnit().create(Math.max(value, measurement.getValue()));
  }

  public static <Q extends Quantity<Q, U, M>, U extends Unit<Q, U, M>, M extends Measurement<Q, U, M>> M max(M measurement1, M measurement2) {
    return max(measurement1, measurement2.getValueInUnit(measurement1.getUnit()));
  }

  public static <Q extends Quantity<Q, U, M>, U extends Unit<Q, U, M>, M extends Measurement<Q, U, M>> M min(M measurement1, M measurement2) {
    return min(measurement1, measurement2.getValueInUnit(measurement1.getUnit()));
  }

  public static <Q extends Quantity<Q, U, M>, U extends Unit<Q, U, M>, M extends Measurement<Q, U, M>> M abs(M measurement) {
    return measurement.getUnit().create(Math.abs(measurement.getValue()));
  }


  public static <Q extends Quantity<Q, U, M>, U extends Unit<Q, U, M>, M extends LinearMeasurement<Q, U, M>> M add(M lhs, M rhs) {
    return add(lhs, rhs, lhs.getUnit());
  }

  public static <Q extends Quantity<Q, U, M>, U extends Unit<Q, U, M>, M extends LinearMeasurement<Q, U, M>> M add(M lhs, M rhs, U resultUnit) {
    return resultUnit.create(lhs.getValueInUnit(resultUnit) + rhs.getValueInUnit(resultUnit));
  }


  public static <Q extends Quantity<Q, U, M>, U extends Unit<Q, U, M>, M extends LinearMeasurement<Q, U, M>> M subtract(M lhs, M rhs, U resultUnit) {
    return resultUnit.create(lhs.getValueInUnit(resultUnit) - rhs.getValueInUnit(resultUnit));
  }

  public static <Q extends Quantity<Q, U, M>, U extends Unit<Q, U, M>, M extends LinearMeasurement<Q, U, M>> M subtract(M lhs, M rhs) {
    return subtract(lhs, rhs, lhs.getUnit());
  }

  public static <Q extends Quantity<Q, U, M>, U extends Unit<Q, U, M>, M extends LinearMeasurement<Q, U, M>> double divide(M measurement1, M measurement2) {
    return measurement1.getValue() / measurement2.getValueInUnit(measurement1.getUnit());
  }


  public static <Q extends Quantity<Q, U, M>, U extends Unit<Q, U, M>, M extends LinearMeasurement<Q, U, M>> M multiply(LinearMeasurement m1, LinearMeasurement m2, U unit) {
    return UnitOperationsChecker.checkMultiplyUnit(m1.getUnit(), m2.getUnit(), unit)
        .getReferenceSI()
        .create(m1.getValueInSI() * m2.getValueInSI())
        .convert(unit);
  }

  public static <Q extends Quantity<Q, U, M>, M extends LinearMeasurement<Q, U, M>, U extends Unit<Q, U, M>> M divide(LinearMeasurement m1, LinearMeasurement m2, U unit) {
    return UnitOperationsChecker.checkDividedUnit(m1.getUnit(), m2.getUnit(), unit)
        .getReferenceSI()
        .create(m1.getValueInSI() / m2.getValueInSI())
        .convert(unit);
  }

  public static <Q extends Quantity<Q, U, M>, M extends LinearMeasurement<Q, U, M>, U extends Unit<Q, U, M>> M inverse(LinearMeasurement measurement, U unit) {
    return UnitOperationsChecker.checkInvertedUnit(measurement.getUnit(), unit)
        .getReferenceSI()
        .create(1. / measurement.getValueInSI())
        .convert(unit);
  }

  public static <Q extends Quantity<Q, U, M>, M extends LinearMeasurement<Q, U, M>, U extends Unit<Q, U, M>> M cubic(LinearMeasurement measurement, U unit) {
    final double value = measurement.getValueInSI();

    return UnitOperationsChecker.checkCubicUnit(measurement.getUnit(), unit)
        .getReferenceSI()
        .create(Math.pow(value, 3))
        .convert(unit);
  }

  public static <
      Q extends Quantity<Q, U, M>,
      U extends Unit<Q, U, M>,
      M extends LinearMeasurement<Q, U, M>>
  M square(LinearMeasurement<?,?,?> measurement, U unit) {
    final double value = measurement.getValueInSI();

    return UnitOperationsChecker.checkSquareUnit(measurement.getUnit(), unit)
        .getReferenceSI()
        .create(value * value)
        .convert(unit);
  }

  public static <
      Q extends Quantity<Q, U, M>,
      M extends LinearMeasurement<Q, U, M>,
      U extends Unit<Q, U, M>>
  M squareRoot(LinearMeasurement<?, ?, ?> measurement, U unit) {
    final double value = measurement.getValueInSI();
    return UnitOperationsChecker.checkSquareRootUnit(measurement.getUnit(), unit)
        .getReferenceSI()
        .create(Math.sqrt(value))
        .convert(unit);
  }


}
