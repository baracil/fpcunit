package net.femtoparsec.units.property;

import net.femtoparsec.units.api.Measurement;
import net.femtoparsec.units.api.Unit;
import net.femtoparsec.units.api.Quantity;
import javafx.beans.value.ObservableValue;

/**
 * @author Bastien Aracil
 */
public abstract class DoubleSameMeasurementBinding<Q extends Quantity<Q,U,M>, U extends Unit<Q,U,M>, M extends Measurement<Q,U,M>> extends DoubleMeasurementBinding<M,M,Q,U,M> {

    public DoubleSameMeasurementBinding(ObservableValue<M> measurement1, ObservableValue<M> measurement2) {
        super(measurement1, measurement2);
    }

    protected abstract M computeValue(M measurement, M measurement1);

}
