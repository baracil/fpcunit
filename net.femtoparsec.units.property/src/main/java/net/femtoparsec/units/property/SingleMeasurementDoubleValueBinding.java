package net.femtoparsec.units.property;

import net.femtoparsec.units.api.Measurement;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.value.ObservableValue;

/**
 * @author Bastien Aracil
 */
public abstract class SingleMeasurementDoubleValueBinding<M extends Measurement<?,?,M>> extends ObjectBinding<Double> {

    private final ObservableValue<M> observableMeasurement;

    public SingleMeasurementDoubleValueBinding(ObservableValue<M> observableMeasurement) {
        this.observableMeasurement = observableMeasurement;
        super.bind(observableMeasurement);
    }

    @Override
    protected Double computeValue() {
        final M measurement = observableMeasurement == null ? null : observableMeasurement.getValue();
        return measurement == null ? null : computeValue(measurement);
    }

    protected abstract Double computeValue(M measurement);


}
