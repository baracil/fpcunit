package net.femtoparsec.units.property;

import net.femtoparsec.units.api.LinearMeasurement;
import net.femtoparsec.units.api.Unit;
import net.femtoparsec.units.api.Quantity;
import javafx.beans.value.ObservableValue;

/**
 * @author Bastien Aracil
 */
public abstract class DoubleSameStandardMeasurementBinding<Q extends Quantity<Q,U,M>, U extends Unit<Q,U,M>, M extends LinearMeasurement<Q,U,M>> extends DoubleStandardMeasurementBinding<M,M, Q,U, M> {

    public DoubleSameStandardMeasurementBinding(ObservableValue<M> measurement1, ObservableValue<M> measurement2) {
        super(measurement1, measurement2);
    }

    protected abstract M computeValue(M measurement, M measurement1);

}
