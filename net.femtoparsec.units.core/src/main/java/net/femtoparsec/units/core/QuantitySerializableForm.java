package net.femtoparsec.units.core;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.femtoparsec.units.api.Quantity;

import java.io.ObjectStreamException;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author Bastien Aracil
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class QuantitySerializableForm implements Serializable  {

    public static QuantitySerializableForm create(@NonNull Quantity<?,?,?> quantity) {
        return new QuantitySerializableForm(quantity.getClass());
    }

    @NonNull
    private final Class<?> quantityClass;

    @Serial
    protected Object readResolve() throws ObjectStreamException {
        try {
            return quantityClass.getField("INSTANCE").get(null);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}
