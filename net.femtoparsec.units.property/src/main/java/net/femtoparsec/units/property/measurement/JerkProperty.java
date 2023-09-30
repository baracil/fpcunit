package net.femtoparsec.units.property.measurement;

import net.femtoparsec.units.named.Units;
import net.femtoparsec.units.named.measurement.Jerk;
import net.femtoparsec.units.named.measurement.Time;
import net.femtoparsec.units.named.unit.JerkUnit;
import net.femtoparsec.units.property.NamedMeasurementExpressionHelper;
import net.femtoparsec.units.property.NamedSimpleStandardMeasurementProperty;
import net.femtoparsec.units.named.quantity.JerkQuantity;
import javafx.beans.value.ObservableValue;

/**
 * @author Bastien Aracil
 */
public class JerkProperty extends NamedSimpleStandardMeasurementProperty<JerkQuantity, JerkUnit, Jerk, JerkBinding> {

    public JerkProperty() {
        super(JerkBinding.FACTORY);
    }

    public JerkProperty(Object bean, String name) {
        super(bean, name, JerkBinding.FACTORY);
    }

    public JerkProperty(Object bean, String name, Jerk initialValue) {
        super(bean, name, initialValue, JerkBinding.FACTORY);
    }

    public JerkProperty(Jerk initialValue) {
        super(initialValue, JerkBinding.FACTORY);
    }

    public AccelerationBinding multiply(Time time) {
        return NamedMeasurementExpressionHelper.multiply(AccelerationBinding.FACTORY, this, time, Units.METER_PER_SQUARE_SECOND);
    }

    public AccelerationBinding multiply(ObservableValue<Time> time) {
        return NamedMeasurementExpressionHelper.multiply(AccelerationBinding.FACTORY, this, time, Units.METER_PER_SQUARE_SECOND);
    }

}
