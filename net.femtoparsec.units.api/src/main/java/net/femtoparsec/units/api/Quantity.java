package net.femtoparsec.units.api;

import lombok.NonNull;

import java.io.Serializable;
import java.util.List;
import java.util.NavigableMap;
import java.util.Optional;


/**
 * A physical quantity.
 *
 * @author Bastien Aracil
 */
public interface Quantity<Q extends Quantity<Q,U,M>, U extends Unit<Q,U,M>, M extends Measurement<Q,U,M>> extends Dimensioned, Serializable {

    default boolean hasSameDimension(Quantity<?,?,?> quantity) {
        return this.getDimension().equals(quantity.getDimension());
    }

    /**
     * @param name the name of the unit to find
     * @return an optional containing the unit with the provided name if it exits, an empty optional otherwise
     */
    Optional<U> findUnit(String name);

    /**
     * @param name the name of the unit to get
     * @return the unit with the provided name
     * @throws UnknownUnitException if no unit exists with this name
     */
    @NonNull
    U getUnit(String name);

    /**
     * @return the S.I. unit for this quantity
     */
    U getSIUnit();

    /**
     * @return the class of the measurement associated with this quantity
     */
    Class<M> getMeasurementType();

    /**
     * @param measurementAsString the measurement as string to convert
     * @return the measurement of this quantity parsed from the provided string
     * @throws IllegalArgumentException is the provided string does not represent a measurement of this quantity
     */
    @NonNull
    default M parseMeasurement(String measurementAsString) {
        return safeParseMeasurement(measurementAsString)
            .orElseThrow(() -> new IllegalArgumentException("Invalid measurement " + measurementAsString));
    }

    /**
     * @param measurementAsString the measurement as string to convert
     * @return an optional containing the measurement of this quantity parsed from the provided string, an empty optional if the parsing could not
     * be done
     */
    Optional<M> safeParseMeasurement(String measurementAsString);

    /**
     * @param measurementAsString the measurement as string to convert
     * @param defaultValue the value to used if the conversion failed
     * @return the measurement of this quantity parsed from the provided string
     * @throws IllegalArgumentException is the provided string does not represent a measurement of this quantity
     */
    @NonNull
    default M parseMeasurement(String measurementAsString,@NonNull M defaultValue) {
        return safeParseMeasurement(measurementAsString).orElse(defaultValue);
    }

    /**
     * @return all the units registered to this quantity
     */
    List<U> getAllUnits();

    /**
     * @return all the unit by factor
     */
    NavigableMap<Double, List<U>> getUnitsByFactor();
}
