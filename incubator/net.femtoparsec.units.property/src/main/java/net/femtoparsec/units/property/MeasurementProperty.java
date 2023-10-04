package net.femtoparsec.units.property;

import net.femtoparsec.units.api.Measurement;
import net.femtoparsec.units.api.Unit;
import net.femtoparsec.units.api.Quantity;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.beans.value.WritableObjectValue;

/**
 * @author Bastien Aracil
 */
public abstract class MeasurementProperty<Q extends Quantity<Q,U,M>,U extends Unit<Q,U,M>, M extends Measurement<Q,U,M>> extends ReadOnlyMeasurementProperty<Q,U,M> implements Property<M>, WritableObjectValue<M> {

    @Override
    public void setValue(M value) {
        set(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bindBidirectional(Property<M> other) {
        Bindings.bindBidirectional(this, other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unbindBidirectional(Property<M> other) {
        Bindings.unbindBidirectional(this, other);
    }

    /**
     * Returns a string representation of this {@code StringProperty} object.
     * @return a string representation of this {@code StringProperty} object.
     */
    @Override
    public String toString() {
        final Object bean = getBean();
        final String name = getName();
        final StringBuilder result = new StringBuilder(
                "MeasurementProperty [");
        if (bean != null) {
            result.append("bean: ").append(bean).append(", ");
        }
        if ((name != null) && (!name.equals(""))) {
            result.append("name: ").append(name).append(", ");
        }
        result.append("value: ").append(get()).append("]");
        return result.toString();
    }

}
