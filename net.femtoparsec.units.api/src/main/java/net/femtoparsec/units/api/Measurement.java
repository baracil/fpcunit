package net.femtoparsec.units.api;

import java.io.Serializable;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Bastien Aracil
 */
public interface Measurement<Q extends Quantity> extends Comparable<Measurement<Q>>, Dimensioned, Serializable {

    /**
     * @return the quantity this is measuring
     */
    default Quantity getQuantity() {
        return getUnit().getQuantity();
    }


    /**
     * @return the unit this measurement is expressed in
     */
    Unit<Q> getUnit();

    /**
     * @return the value in the unit of this measurement
     */
    double getValue();

    /**
     * @return the value in the S.I. unit system.
     */
    double getValueInSI();

    <NQ extends NamedQuantity<M>, M extends Measurement<NQ>> Optional<M> tryAs(NQ quantity);

    <NQ extends NamedQuantity<M>, M extends Measurement<NQ>> M as(NQ quantity);

    /**
     * @param otherUnit a unit
     * @return the value of this measurement in the provided unit
     */
    double getValueInUnit(Unit<Q> otherUnit);

    default double toDouble(Unit<Q> unit) {
        return getValueInUnit(unit);
    }

    default float toFloat(Unit<Q> unit) {
        return (float)getValueInUnit(unit);
    }

    default long toLong(Unit<Q> unit) {
        return (long)getValueInUnit(unit);
    }

    default long toRoundedLong(Unit<Q> unit) {
        return Math.round(getValueInUnit(unit));
    }

    default int toInt(Unit<Q> unit) {
        return (int)getValueInUnit(unit);
    }

    default int toRoundedInt(Unit<Q> unit) {
        return Math.round(toFloat(unit));
    }

    /**
     * Create a new <code>Measurement</code> representing the same value than this one but
     * in another unit.
     *
     * @param newUnit the new unit to use for the new <code>Measurement</code>
     * @return the new <code>Measurement</code> in the given unit
     */
    Measurement<Q> convert(Unit<Q> newUnit);

    default Measurement<Q> sameUnitAs(Measurement<Q> reference) {
        return convert(reference.getUnit());
    }

    /**
     * Create a new <code>Measurement</code> representing the same value than this one but
     * in S.I. unit
     *
     * @return the new <code>Measurement</code> in S.I. unit
     */
    Measurement<Q> convertToSI();

    default Measurement<Q> ceil(Unit<Q> unit) {
        double value = Math.ceil(this.getValueInUnit(unit));
        return unit.create(value);
    }

    default Measurement<Q> floor(Unit<Q> unit) {
        double value = Math.floor(this.getValueInUnit(unit));
        return unit.create(value);
    }

    Measurement<Q> round(Unit<Q> unit, int nbDecimals);

    default Measurement<Q> round(Unit<Q> unit) {
        return round(unit, 0);
    }

    Measurement<Q> max(double value);

    Measurement<Q> max(Measurement<Q> other);

    Measurement<Q> min(double value);

    Measurement<Q> min(Measurement<Q> other);

    boolean isNaN();

    boolean isFinite();

    boolean isGreaterThan(Measurement<Q> other);

    boolean isGreaterOrEqualTo(Measurement<Q> other);

    boolean isLowerThan(Measurement<Q> other);

    boolean isLowerOrEqualTo(Measurement<Q> other);

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

    boolean equals(Measurement<Q> other, double error);


    default String toText() {
        return this.getValue()+" "+this.getUnit().getName();
    }
}
