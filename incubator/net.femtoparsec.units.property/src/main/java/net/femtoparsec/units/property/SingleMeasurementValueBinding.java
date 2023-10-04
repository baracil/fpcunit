package net.femtoparsec.units.property;

import net.femtoparsec.units.api.Measurement;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.value.ObservableValue;

/**
 * @author Bastien Aracil
 */
public abstract class SingleMeasurementValueBinding<M extends Measurement<?,?,M>> extends DoubleBinding {

    private final ObservableValue<M> observableMeasurement;

    private final double defaultValue;

    public SingleMeasurementValueBinding(ObservableValue<M> observableMeasurement, double defaultValue) {
        this.observableMeasurement = observableMeasurement;
        this.defaultValue = defaultValue;
        super.bind(observableMeasurement);
    }

    @Override
    protected double computeValue() {
        final M measurement = observableMeasurement == null ? null : observableMeasurement.getValue();
        return measurement == null ? defaultValue : computeValue(measurement);
    }

    protected abstract double computeValue(M measurement);


}
