package net.femtoparsec.units.property;

import net.femtoparsec.units.api.Measurement;
import net.femtoparsec.units.api.Unit;
import net.femtoparsec.units.api.Quantity;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WeakChangeListener;

import java.util.Objects;

/**
 * @author Bastien Aracil
 */
public class MeasurementValueBinding<Q extends Quantity<Q,U,M>, U extends Unit<Q,U,M>, M extends Measurement<Q,U,M>> extends SimpleDoubleProperty implements ChangeListener<M> {

    private final Property<M> measurement;

    private final U unit;

    private boolean updating = false;

    public MeasurementValueBinding(Property<M> measurement, U unit) {
        this.measurement = Objects.requireNonNull(measurement, "measurement");
        this.unit = Objects.requireNonNull(unit, "unit");
        this.set(measurement.getValue().getValueInUnit(unit));
        this.measurement.addListener(new WeakChangeListener<>(this));
    }

    @Override
    protected void invalidated() {
        super.invalidated();
        if (!this.updating) {
            measurement.setValue(unit.measurement(get()));
        }
    }

    @Override
    public void changed(ObservableValue<? extends M> observable, M oldValue, M newValue) {
        this.updating = true;
        try {
            this.set(newValue.getValueInUnit(this.unit));
        } finally {
            this.updating = false;
        }
    }
}
