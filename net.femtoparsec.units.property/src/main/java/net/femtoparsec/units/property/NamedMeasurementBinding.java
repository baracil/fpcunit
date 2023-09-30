package net.femtoparsec.units.property;

import net.femtoparsec.units.api.Measurement;
import net.femtoparsec.units.api.Unit;
import net.femtoparsec.units.api.Quantity;
import net.femtoparsec.units.property.measurement.BindingComputer;
import javafx.beans.value.ObservableValue;

/**
 * @author Bastien Aracil
 */
public abstract class NamedMeasurementBinding<Q extends Quantity<Q,U,M>,U extends Unit<Q,U,M>, M extends Measurement<Q,U,M>, B extends MeasurementBinding<Q,U,M>> extends MeasurementBinding<Q,U,M> {

    private final BindingComputer<M> computer;

    private final NamedBindingFactory<Q,U,M,B> factory;

    public NamedMeasurementBinding(BindingComputer<M> computer, NamedBindingFactory<Q,U,M,B> factory) {
        this.computer = computer;
        this.factory = factory;
    }


    @Override
    protected M computeValue() {
        return this.computer.compute();
    }

    @Override
    public B convert(U unit) {
        return NamedMeasurementExpressionHelper.convert(this.factory, this, unit);
    }

    @Override
    public B max(ObservableValue<M> other) {
        return NamedMeasurementExpressionHelper.max(this.factory, this, other);
    }

    @Override
    public B min(ObservableValue<M> other) {
        return NamedMeasurementExpressionHelper.min(this.factory, this, other);
    }


}
