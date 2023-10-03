package net.femtoparsec.units.api;

import lombok.NonNull;

import java.io.Serializable;
import java.util.Optional;


/**
 * A physical quantity.
 *
 * @author Bastien Aracil
 */
public interface Quantity extends Dimensioned, Serializable {

  default boolean hasSameDimension(Quantity quantity) {
    return this.getDimension().equals(quantity.getDimension());
  }

  /**
   * @param name the name of the unit to find
   * @return an optional containing the unit with the provided name if it exits, an empty optional otherwise
   */
  Optional<? extends Unit<?>> findUnit(String name);

  /**
   * @param name the name of the unit to get
   * @return the unit with the provided name
   * @throws UnknownUnitException if no unit exists with this name
   */
  Unit<?> getUnit(String name);

  /**
   * @param measurementAsString the measurement as string to convert
   * @return the measurement of this quantity parsed from the provided string
   * @throws IllegalArgumentException is the provided string does not represent a measurement of this quantity
   */
  default Measurement<?> parseMeasurement(String measurementAsString) {
    return safeParseMeasurement(measurementAsString)
        .orElseThrow(() -> new IllegalArgumentException("Invalid measurement " + measurementAsString));
  }

  /**
   * @param measurementAsString the measurement as string to convert
   * @return an optional containing the measurement of this quantity parsed from the provided string, an empty optional if the parsing could not
   * be done
   */
  Optional<? extends Measurement<?>> safeParseMeasurement(String measurementAsString);

}
