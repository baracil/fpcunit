package net.femtoparsec.units.property;

import net.femtoparsec.units.api.LinearMeasurement;
import net.femtoparsec.units.api.Unit;
import net.femtoparsec.units.api.Quantity;
import net.femtoparsec.units.property.measurement.BindingComputer;
import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.value.ObservableValue;

/**
 * @author Bastien Aracil
 */
public abstract class NamedStandardMeasurementBinding<Q extends Quantity<Q,U,M>, U extends Unit<Q,U, M>,M extends LinearMeasurement<Q,U,M>, B extends StandardMeasurementBinding<Q,U,M>> extends StandardMeasurementBinding<Q,U,M> {

    private final BindingComputer<M> computer;
    
    private final NamedBindingFactory<Q,U,M,B> factory;

    public NamedStandardMeasurementBinding(BindingComputer<M> computer, NamedBindingFactory<Q,U,M,B> factory) {
        this.computer = computer;
        this.factory = factory;
    }


    @Override
    public B inAdaptedUnit() {
        return NamedMeasurementExpressionHelper.inAdaptedUnit(this.factory, this);
    }

    @Override
    public B inAdaptedUnit(U unitForZero) {
        return NamedMeasurementExpressionHelper.inAdaptedUnit(this.factory, this, unitForZero);
    }

    @Override
    public B inAdaptedUnit(ObservableValue<U> unitForZero) {
        return NamedMeasurementExpressionHelper.inAdaptedUnit(this.factory, this, unitForZero);
    }

    @Override
    protected M computeValue() {
        return this.computer.compute();
    }

    @Override
    public B add(M other) {
        return NamedMeasurementExpressionHelper.add(this.factory, this, other);
    }

    @Override
    public B abs() {
        return NamedMeasurementExpressionHelper.abs(this.factory, this);
    }

    @Override
    public B add(ObservableValue<M> other) {
        return NamedMeasurementExpressionHelper.add(this.factory, this, other);
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

    @Override
    public B scale(double factor) {
        return NamedMeasurementExpressionHelper.scale(this.factory, this, factor);
    }

    @Override
    public B scale(ObservableDoubleValue factor) {
        return NamedMeasurementExpressionHelper.scale(this.factory, this, factor);
    }

    @Override
    public B subtract(M other) {
        return NamedMeasurementExpressionHelper.subtract(this.factory, this, other);
    }

    @Override
    public B subtract(ObservableValue<M> other) {
        return NamedMeasurementExpressionHelper.subtract(this.factory, this, other);
    }

    @Override
    public B negate() {
        return NamedMeasurementExpressionHelper.negate(this.factory, this);
    }


}
