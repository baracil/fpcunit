package net.femtoparsec.units.property;

import net.femtoparsec.units.api.LinearMeasurement;
import net.femtoparsec.units.api.Unit;
import net.femtoparsec.units.api.Quantity;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.beans.value.WritableObjectValue;

/**
 * @author Bastien Aracil
 */
public abstract class StandardMeasurementProperty<Q extends Quantity<Q,U,M>,U extends Unit<Q,U,M>, M extends LinearMeasurement<Q,U,M>> extends ReadOnlyStandardMeasurementProperty<Q,U,M> implements Property<M>, WritableObjectValue<M> {

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
                "StandardMeasurementProperty [");
        if (bean != null) {
            result.append("bean: ").append(bean).append(", ");
        }
        if ((name != null) && (!name.isEmpty())) {
            result.append("name: ").append(name).append(", ");
        }
        result.append("value: ").append(get()).append("]");
        return result.toString();
    }

}
