package net.femtoparsec.units.api;

/**
 * @author Bastien Aracil
 */
public interface LinearMeasurement<
    Q extends Quantity<Q, U, M>,
    U extends Unit<Q, U, M>,
    M extends LinearMeasurement<Q, U, M>
    > extends Measurement<Q, U, M> {

  M add(M other);

  M subtract(M other);

  M negate();

  M scale(double factor);

  M abs();

  double divide(M other);

}
