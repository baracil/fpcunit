package net.femtoparsec.units.core;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.femtoparsec.units.api.Quantity;
import net.femtoparsec.units.api.Unit;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * @author Bastien Aracil
 */
@RequiredArgsConstructor
public class UnitSerializableForm implements Serializable  {

    public static UnitSerializableForm create(@NonNull Unit<?,?,?> unit) {
        return new UnitSerializableForm(unit.getQuantity(), unit.getName());
    }

    @NonNull
    private final Quantity<?,?,?> quantity;

    @NonNull
    private final String name;

    protected Object readResolve() throws ObjectStreamException {
        return quantity.getUnit(name);
    }
}
