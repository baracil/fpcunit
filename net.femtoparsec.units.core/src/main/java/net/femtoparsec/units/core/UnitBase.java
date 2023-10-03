package net.femtoparsec.units.core;

import net.femtoparsec.units.api.Dimension;
import net.femtoparsec.units.api.Measurement;
import net.femtoparsec.units.api.Quantity;
import net.femtoparsec.units.api.Unit;

import java.io.ObjectStreamException;
import java.io.Serial;
import java.util.Objects;

/**
 * @author Bastien Aracil
 */
public abstract class UnitBase<Q extends Quantity> implements Unit<Q> {

    /**
     * The reference of the unit in the S.I. system
     */
    private final Unit<Q> referenceSI;

    /**
     * The offset to convert this unit into S.I. system (only not nil for temperature units)
     */
    private final double offsetToSI;

    /**
     * The factor to convert this unit into S.I. system (1 for SI unit)
     */
    private final double factorToSI;

    /**
     * The unit name
     */
    private final String name;

    private final String prettyName;

    /**
     * S.I. unit constructor
     *
     * @param name  the name of the S.I. unit
     */
    protected UnitBase(String name) {
        this.name = Objects.requireNonNull(name, "name");
        this.referenceSI = null;
        this.factorToSI = 1;
        this.offsetToSI = 0;
        this.prettyName = UnitUtils.format(name);
    }

    @Override
    public Dimension getDimension() {
        return this.getQuantity().getDimension();
    }

    /**
     * Create a unit by using its related S.I. unit
     *
     * @param name        the name of the new unit
     * @param referenceSI the reference S.I. unit
     * @param factorToSI  the factor to convert this unit to S.I. unit
     */
    protected UnitBase(String name, Unit<Q> referenceSI, double factorToSI) {
        this(name, referenceSI, factorToSI, 0);
    }

    /**
     * Create a unit by using its related S.I. unit
     *
     * @param name        the name of the new unit
     * @param referenceSI the reference S.I. unit
     * @param factorToSI  the factor to convert this unit to S.I. unit
     * @param offsetToSI  the oddset to convert this unit to S.I. unit (this only applied to temperature unit)
     */
    protected UnitBase(String name, Unit<Q> referenceSI, double factorToSI, double offsetToSI) {
        if (factorToSI <= 0) {
            throw new IllegalArgumentException("Invalid factor to convert to SI : " + factorToSI);
        }
        this.referenceSI = Objects.requireNonNull(referenceSI, "referenceSI");
        this.factorToSI = factorToSI;
        this.offsetToSI = offsetToSI;
        this.name = Objects.requireNonNull(name, "name");
        this.prettyName = UnitUtils.format(name);
    }

    /**
     * @return true if this unit is an S.I. unit
     */
    @Override
    public boolean isSI() {
        return this.referenceSI == null || this.referenceSI == this;
    }

    /**
     * @return the S.I. unit for this unit (if this is a S.I. unit, this method returns this)
     */
    @Override
    public Unit<Q> getReferenceSI() {
        return this.referenceSI == null ? this : this.referenceSI;
    }

    @Override
    public double getFactorToSI() {
        return this.factorToSI;
    }

    @Override
    public double getOffsetToSI() {
        return offsetToSI;
    }

    /**
     * Convert a value in this unit to S.I. unit
     *
     * @param value the value to convert in this unit
     *
     * @return the value in S.I. unit
     */
    @Override
    public double convertToSI(double value) {
        return (value + offsetToSI) * factorToSI;
    }

    /**
     * Convert a value in S.I. unit to this unit
     *
     * @param value the value to convert in S.I. unit
     *
     * @return the value in this unit
     */
    @Override
    public double convertFromSI(double value) {
        if (isSI()) {
            return value;
        }
        return value / factorToSI - offsetToSI;
    }

    /**
     * Convert a value int this unit to another unit
     *
     * @param value the value to convert in this unit
     * @param unit  the new unit for the value
     *
     * @return the value in the new units
     */
    @Override
    public double convertTo(double value, Unit<Q> unit) {
        if (this == unit) {
            return value;
        }
        return unit.convertFromSI(this.convertToSI(value));
    }

    /**
     * Check if this unit is compatible with another unit (for instance <code>m</code> and <code>cm</code>
     * are compatible but not <code>m</code> and <code>s</code>
     *
     * @param other the other unit to check against
     *
     * @return true if this unit is compatible with the other unit, false otherwise
     */
    @Override
    public boolean isCompatible(Unit<?> other) {
        return appliesTo(other.getQuantity());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean appliesTo(Quantity quantity) {
        return this.getQuantity().equals(quantity);
    }

    @Override
    public abstract Measurement<Q> create(double value);

    @Override
    public String getPrettyName() {
        return this.prettyName;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+"{" +
                "name='" + name + '\'' +
                ", quantity=" + getQuantity() +
                '}';
    }

    @Override
    public int hashCode() {
        int result = getQuantity().hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof UnitBase<?> unit))
            return false;

        return name.equals(unit.name) && getQuantity().equals(unit.getQuantity());

    }

    @Serial
    protected Object writeReplace() throws ObjectStreamException {
        return UnitSerializableForm.create(this);
    }

}
