package net.femtoparsec.units.property;

import net.femtoparsec.units.api.Unit;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.beans.value.WritableObjectValue;

/**
 * @author Bastien Aracil
 */
public abstract class UnitProperty<U extends Unit<?,U,?>> extends ReadOnlyUnitProperty<U> implements Property<U>, WritableObjectValue<U> {

    @Override
    public void setValue(U value) {
        set(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bindBidirectional(Property<U> other) {
        Bindings.bindBidirectional(this, other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unbindBidirectional(Property<U> other) {
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
                "UnitProperty [");
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
