package net.femtoparsec.units.core;

import net.femtoparsec.units.api.LinearMeasurement;
import net.femtoparsec.units.api.Measurement;
import net.femtoparsec.units.api.Quantity;
import net.femtoparsec.units.api.Unit;

/**
 * @author Bastien Aracil
 */
public class MeasurementOperations {

  public static <Q extends Quantity, M extends Measurement<Q>> M multiply(LinearMeasurement<?> m1,
                                                                          LinearMeasurement<?> m2,
                                                                          Unit<Q> unit,
                                                                          MeasurementFactory<Q, M> factory) {
    QuantityOperationsChecker.INSTANCE.checkMultiply(m1.getQuantity(), m2.getQuantity(), unit.getQuantity());
    final var siValue = m1.getValueInSI() * m2.getValueInSI();
    return factory.create(unit.convertFromSI(siValue), unit);
  }


  public static <Q extends Quantity> double divide(LinearMeasurement<Q> measurement1, LinearMeasurement<Q> measurement2) {
    return measurement1.getValueInSI() / measurement2.getValueInSI();
  }


  public static <Q extends Quantity, M extends Measurement<Q>> M divide(LinearMeasurement<?> m1,
                                                                        LinearMeasurement<?> m2,
                                                                        Unit<Q> unit,
                                                                        MeasurementFactory<Q, M> factory) {
    QuantityOperationsChecker.INSTANCE.checkDivided(m1.getQuantity(), m2.getQuantity(), unit.getQuantity());
    final var siValue = m1.getValueInSI() / m2.getValueInSI();
    return factory.create(unit.convertFromSI(siValue), unit);
  }

  public static <Q extends Quantity, M extends Measurement<Q>> M inverse(LinearMeasurement<?> measurement,
                                                                         Unit<Q> unit,
                                                                         MeasurementFactory<Q, M> factory) {
    QuantityOperationsChecker.INSTANCE.checkInverted(measurement.getQuantity(), unit.getQuantity());
    final var siValue = 1. / measurement.getValueInSI();
    return factory.create(unit.convertFromSI(siValue), unit);
  }

  public static <Q extends Quantity, M extends Measurement<Q>> M cubic(LinearMeasurement<?> measurement,
                                                                       Unit<Q> unit,
                                                                       MeasurementFactory<Q, M> factory) {
    QuantityOperationsChecker.INSTANCE.checkCubic(measurement.getQuantity(), unit.getQuantity());
    final double value = measurement.getValueInSI();
    return factory.create(unit.convertFromSI(value * value * value), unit);
  }

  public static <Q extends Quantity, M extends Measurement<Q>> M square(LinearMeasurement<?> measurement,
                                                                        Unit<Q> unit,
                                                                        MeasurementFactory<Q, M> factory) {
    QuantityOperationsChecker.INSTANCE.checkSquare(measurement.getQuantity(), unit.getQuantity());
    final double value = measurement.getValueInSI();
    return factory.create(unit.convertFromSI(value * value), unit);
  }

  public static <Q extends Quantity, M extends Measurement<Q>> M squareRoot(LinearMeasurement<?> measurement,
                                                                            Unit<Q> unit,
                                                                            MeasurementFactory<Q, M> factory) {
    QuantityOperationsChecker.INSTANCE.checkSquareRoot(measurement.getQuantity(), unit.getQuantity());
    final double value = measurement.getValueInSI();
    return factory.create(unit.convertFromSI(Math.sqrt(value)), unit);
  }

}
