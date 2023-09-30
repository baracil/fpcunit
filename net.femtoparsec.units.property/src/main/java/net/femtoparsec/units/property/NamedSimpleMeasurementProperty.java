package net.femtoparsec.units.property;

import net.femtoparsec.units.api.Measurement;
import net.femtoparsec.units.api.Unit;
import net.femtoparsec.units.api.Quantity;
import javafx.beans.value.ObservableValue;

/**
 * @author Bastien Aracil
 */
public class NamedSimpleMeasurementProperty<Q extends Quantity<Q,U,M>, U extends Unit<Q,U,M>, M extends Measurement<Q,U,M>, B extends NamedMeasurementBinding<Q,U,M,B>> extends SimpleMeasurementProperty<Q,U,M> {

    private final NamedBindingFactory<Q,U,M,B> factory;

    public NamedSimpleMeasurementProperty(NamedBindingFactory<Q,U,M,B> factory) {
        this.factory = factory;
    }

    public NamedSimpleMeasurementProperty(Object bean, String name, NamedBindingFactory<Q,U,M,B> factory) {
        super(bean, name);
        this.factory = factory;
    }

    public NamedSimpleMeasurementProperty(Object bean, String name, M initialValue, NamedBindingFactory<Q,U,M,B> factory) {
        super(bean, name, initialValue);
        this.factory = factory;
    }

    public NamedSimpleMeasurementProperty(M initialValue, NamedBindingFactory<Q,U,M,B> factory) {
        super(initialValue);
        this.factory = factory;
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
