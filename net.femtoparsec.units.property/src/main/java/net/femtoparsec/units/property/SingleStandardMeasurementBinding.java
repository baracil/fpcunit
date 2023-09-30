package net.femtoparsec.units.property;

import net.femtoparsec.units.api.Measurement;
import net.femtoparsec.units.api.LinearMeasurement;
import net.femtoparsec.units.api.Unit;
import net.femtoparsec.units.api.Quantity;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;

/**
 * @author Bastien Aracil
 */
public abstract class SingleStandardMeasurementBinding<Q1 extends Quantity<Q1,U1,M1>, U1 extends Unit<Q1,U1,M1>, M1 extends Measurement<Q1,U1,M1>, Q2 extends Quantity<Q2,U2,M2>, U2 extends Unit<Q2,U2,M2>, M2 extends LinearMeasurement<Q2,U2,M2>> extends StandardMeasurementBinding<Q2,U2,M2> {

    private final ObservableValue<M1> observableMeasurement;

    public SingleStandardMeasurementBinding(ObservableValue<M1> observableMeasurement, Observable... observables) {
        this.observableMeasurement = observableMeasurement;
        if (observables == null || observables.length == 0) {
            super.bind(observableMeasurement);
        } else {
            Observable[] o = new Observable[observables.length+1];
            System.arraycopy(observables, 0, o, 0, observables.length);
            o[o.length-1] = observableMeasurement;
            super.bind(o);
        }
    }

    @Override
    protected M2 computeValue() {
        final M1 measurement = observableMeasurement == null ? null : observableMeasurement.getValue();
        return computeValue(measurement);
    }

    protected abstract M2 computeValue(M1 measurement);


}
