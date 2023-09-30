package net.femtoparsec.units.core;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.femtoparsec.units.api.Measurement;
import net.femtoparsec.units.api.Unit;

import java.io.ObjectStreamException;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author Bastien Aracil
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class MeasurementSerializableForm implements Serializable {

    public static MeasurementSerializableForm create(@NonNull Measurement<?,?,?> measurement) {
        return new MeasurementSerializableForm(measurement.getUnit(), measurement.getValue());
    }

    @NonNull
    private final Unit<?,?,?> unit;

    private final double value;

    @Serial
    protected Object readResolve() throws ObjectStreamException {
        return unit.create(value);
    }

}
