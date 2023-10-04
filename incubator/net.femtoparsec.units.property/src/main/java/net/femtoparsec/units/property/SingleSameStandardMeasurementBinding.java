package net.femtoparsec.units.property;

import net.femtoparsec.units.api.LinearMeasurement;
import net.femtoparsec.units.api.Unit;
import net.femtoparsec.units.api.Quantity;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;

/**
 * @author Bastien Aracil
 */
public abstract class SingleSameStandardMeasurementBinding<Q extends Quantity<Q,U,M>, U extends Unit<Q,U,M>, M extends LinearMeasurement<Q,U,M>> extends SingleStandardMeasurementBinding<Q,U, M, Q, U, M> {

    public SingleSameStandardMeasurementBinding(ObservableValue<M> observableMeasurement, Observable... observables) {
        super(observableMeasurement, observables);
    }

    protected abstract M computeValue(M measurement);


}
