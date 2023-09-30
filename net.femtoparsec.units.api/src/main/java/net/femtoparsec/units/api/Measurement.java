package net.femtoparsec.units.api;

import java.io.Serializable;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Bastien Aracil
 */
public interface Measurement<
    Q extends Quantity<Q,U,M>,
    U extends Unit<Q,U,M>,
    M extends Measurement<Q,U,M>> extends Comparable<M>, Dimensioned, Serializable {

    /**
     * @return the quantity this is measuring
     */
    default Q getQuantity() {
        return getUnit().getQuantity();
    }


    /**
     * @return the unit this measurement is expressed in
     */
    U getUnit();

    /**
     * @return the value in the unit of this measurement
     */
    double getValue();

    /**
     * @return the value in the S.I. unit system.
     */
    double getValueInSI();

    /**
     * @param otherUnit a unit
     * @return the value of this measurement in the provided unit
     */
    double getValueInUnit(U otherUnit);

    default double toDouble(U unit) {
        return getValueInUnit(unit);
    }

    default float toFloat(U unit) {
        return (float)getValueInUnit(unit);
    }

    default long toLong(U unit) {
        return (long)getValueInUnit(unit);
    }

    default long toRoundedLong(U unit) {
        return Math.round(getValueInUnit(unit));
    }

    default int toInt(U unit) {
        return (int)getValueInUnit(unit);
    }

    default int toRoundedInt(U unit) {
        return Math.round(toFloat(unit));
    }

    /**
     * Create a new <code>Measurement</code> representing the same value than this one but
     * in another unit.
     *
     * @param newUnit the new unit to use for the new <code>Measurement</code>
     * @return the new <code>Measurement</code> in the given unit
     */
    M convert(U newUnit);

    default M sameUnitAs(M reference) {
        return convert(reference.getUnit());
    }

    M inAdaptedUnit();

    M inAdaptedUnit(U unitForZero);

    M inAdaptedUnit(Predicate<? super U> unitSelector);

    M inAdaptedUnit(UnitSystem unitSystem);

    M inAdaptedUnit(U unitForZero, Predicate<? super U> unitSelector);

    M inAdaptedUnit(U unitForZero, UnitSystem unitSystem);

    /**
     * Create a new <code>Measurement</code> representing the same value than this one but
     * in S.I. unit
     *
     * @return the new <code>Measurement</code> in S.I. unit
     */
    M convertToSI();

    default M ceil(U unit) {
        double value = Math.ceil(this.getValueInUnit(unit));
        return unit.create(value);
    }

    default M floor(U unit) {
        double value = Math.floor(this.getValueInUnit(unit));
        return unit.create(value);
    }

    M round(U unit, int nbDecimals);

//    default M round(U unit, int nbDecimals) {
//        double value = Math.floor(this.getValueInUnit(unit));
//        return unit.measurement(MeasurementUtils.round(value,nbDecimals));
//    }

    default M round(U unit) {
        return round(unit, 0);
    }

    M max(double value);

    M max(M other);

    M min(double value);

    M min(M other);

    boolean isNaN();

    boolean isFinite();

    boolean isGreaterThan(M other);

    boolean isGreaterOrEqualTo(M other);

    boolean isLowerThan(M other);

    boolean isLowerOrEqualTo(M other);

    default String toPrettyString() {
        return toPrettyString(d -> String.format("%e",d));
    }

    default String toPrettyString(String format) {
        return toPrettyString(d -> String.format(format,d));
    }

    default String toPrettyString(Locale locale) {
        return toPrettyString(d -> String.format(locale,"%e",d));
    }

    default String toPrettyString(Locale locale, String format) {
        return toPrettyString(d -> String.format(locale, format,d));
    }

    String toPrettyString(Function<Double,String> converter);

    String encode();

    boolean equals(M other, double error);


    default String toText() {
        return this.getValue()+" "+this.getUnit().getName();
    }
}
