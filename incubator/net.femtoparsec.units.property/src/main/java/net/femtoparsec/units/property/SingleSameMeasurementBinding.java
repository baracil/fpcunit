package net.femtoparsec.units.property;

import net.femtoparsec.units.api.Measurement;
import net.femtoparsec.units.api.Unit;
import net.femtoparsec.units.api.Quantity;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;

/**
 * @author Bastien Aracil
 */
public abstract class SingleSameMeasurementBinding<Q extends Quantity<Q,U,M>, U extends Unit<Q,U,M>, M extends Measurement<Q,U, M>> extends SingleMeasurementBinding<Q,U,M,Q,U,M> {

    public SingleSameMeasurementBinding(ObservableValue<M> observableMeasurement, Observable... observables) {
        super(observableMeasurement, observables);
    }

    protected abstract M computeValue(M measurement);


}
