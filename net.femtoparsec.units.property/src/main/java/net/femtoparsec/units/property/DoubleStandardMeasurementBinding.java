package net.femtoparsec.units.property;

import net.femtoparsec.units.api.Measurement;
import net.femtoparsec.units.api.Quantity;
import net.femtoparsec.units.api.LinearMeasurement;
import net.femtoparsec.units.api.Unit;
import javafx.beans.value.ObservableValue;

/**
 * @author Bastien Aracil
 */
public abstract class DoubleStandardMeasurementBinding<M1 extends Measurement<?,?,M1>, M2 extends Measurement<?,?,M2>, Q3 extends Quantity<Q3,U3,M3>,U3 extends Unit<Q3,U3,M3>, M3 extends LinearMeasurement<Q3,U3, M3>> extends StandardMeasurementBinding<Q3,U3,M3> {

    private final ObservableValue<M1> measurement1;

    private final ObservableValue<M2> measurement2;

    public DoubleStandardMeasurementBinding(ObservableValue<M1> measurement1, ObservableValue<M2> measurement2) {
        this.measurement1 = measurement1;
        this.measurement2 = measurement2;
        this.bind(measurement1, measurement2);
    }

    @Override
    protected M3 computeValue() {
        return computeValue(getMeasurement(measurement1), getMeasurement(measurement2));
    }

    protected abstract M3 computeValue(M1 measurement, M2 measurement1);

    private <M extends Measurement<?,?,M>> M getMeasurement(ObservableValue<M> observableMeasurementValue) {
        return observableMeasurementValue == null ? null : observableMeasurementValue.getValue();
    }
}
