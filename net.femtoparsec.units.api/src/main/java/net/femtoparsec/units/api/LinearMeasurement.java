package net.femtoparsec.units.api;

import java.util.Optional;

/**
 * @author Bastien Aracil
 */
public interface LinearMeasurement<Q extends Quantity> extends Measurement<Q> {


  LinearMeasurement<Q> negate();

  LinearMeasurement<Q> scale(double factor);

  LinearMeasurement<Q> abs();

  double divide(LinearMeasurement<Q> other);

  LinearMeasurement<Q> add(LinearMeasurement<Q> other);

  LinearMeasurement<Q> subtract(LinearMeasurement<Q> other);


  LinearMeasurement<?> unsafeMultiply(LinearMeasurement<?> other);

  LinearMeasurement<?> unsafeDivide(LinearMeasurement<?> other);

  LinearMeasurement<?> unsafeSquare(LinearMeasurement<?> other);

  LinearMeasurement<?> unsafeCubic(LinearMeasurement<?> other);

}
