package net.femtoparsec.units.property;

import net.femtoparsec.units.api.Measurement;
import net.femtoparsec.units.api.Unit;
import net.femtoparsec.units.api.Quantity;
import javafx.beans.property.ReadOnlyProperty;

/**
 * @author Bastien Aracil
 */
public abstract class ReadOnlyMeasurementProperty<Q extends Quantity<Q,U,M>,U extends Unit<Q,U,M>, M extends Measurement<Q,U,M>> extends MeasurementExpression<Q,U,M> implements ReadOnlyProperty<M> {

    /**
     * Returns a string representation of this {@code ReadOnlyStringProperty} object.
     * @return a string representation of this {@code ReadOnlyStringProperty} object.
     */
    @Override
    public String toString() {
        final Object bean = getBean();
        final String name = getName();
        final StringBuilder result = new StringBuilder(
                "ReadOnlyMeasurementProperty [");
        if (bean != null) {
            result.append("bean: ").append(bean).append(", ");
        }
        if ((name != null) && !name.isEmpty()) {
            result.append("name: ").append(name).append(", ");
        }
        result.append("value: ").append(get()).append("]");
        return result.toString();
    }

}
