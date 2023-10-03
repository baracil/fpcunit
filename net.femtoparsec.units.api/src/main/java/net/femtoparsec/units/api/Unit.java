package net.femtoparsec.units.api;


import java.io.Serializable;

/**
 * @author Bastien Aracil
 */
public interface Unit<Q extends Quantity> extends Dimensioned, Serializable {

    /**
     * @return true if this unit is an S.I. unit
     */
    boolean isSI();

    /**
     * @return the S.I. unit for this unit (if this is an S.I. unit, this method returns this)
     */
    Unit<Q> getReferenceSI();

    double getFactorToSI();

    double getOffsetToSI();

    /**
     * Convert a value in this unit to S.I. unit
     *
     * @param value the value to convert in this unit
     * @return the value in S.I. unit
     */
    double convertToSI(double value);

    /**
     * Convert a value in S.I. unit to this unit
     * @param value the value to convert in S.I. unit
     * @return the value in this unit
     */
    double convertFromSI(double value);

    /**
     * Convert a value int this unit to another unit
     * @param value the value to convert in this unit
     * @param unit the new unit for the value
     * @return the value in the new units
     */
    double convertTo(double value, Unit<Q> unit);

    /**
     * Check if this unit is compatible with another unit (for instance <code>m</code> and <code>cm</code>
     * are compatible but not <code>m</code> and <code>s</code>
     *
     * @param other the other unit to check against
     * @return true if this unit is compatible with the other unit, false otherwise
     */
    boolean isCompatible(Unit<?> other);

    /**
     * @return the physical quantity this unit applies to
     */
    Q getQuantity();

    /**
     * @return the name of this unit
     */
    String getName();

    /**
     * @param quantity a physical quantity
     * @return true if this unit applies to the given physical quantity
     */
    boolean appliesTo(Quantity quantity);

    /**
     * Create a measurement with the given value
     * @param value the value of the measurement in this unit
     * @return a measurement with the given value in this unit
     */
    Measurement<Q> create(double value);

    String getPrettyName();
}
