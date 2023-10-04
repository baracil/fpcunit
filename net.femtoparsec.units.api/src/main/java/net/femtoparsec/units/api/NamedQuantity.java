package net.femtoparsec.units.api;

import java.io.Serializable;
import java.util.Optional;


/**
 * A physical quantity.
 *
 * @author Bastien Aracil
 */
public interface NamedQuantity<M extends Measurement<?>>  extends Quantity {

  M createWithSI(double siValue);

  Class<M> getMeasurementType();

  @Override
  default M parseMeasurement(String measurementAsString) {
    return safeParseMeasurement(measurementAsString)
        .orElseThrow(() -> new IllegalArgumentException("Invalid measurement " + measurementAsString));
  }

  @Override
  Optional<M> safeParseMeasurement(String measurementAsString);
}
