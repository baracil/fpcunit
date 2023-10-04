package net.femtoparsec.units.property.measurement;

import net.femtoparsec.units.named.Units;
import net.femtoparsec.units.named.measurement.Acceleration;
import net.femtoparsec.units.named.measurement.Time;
import net.femtoparsec.units.named.unit.AccelerationUnit;
import net.femtoparsec.units.property.NamedMeasurementExpressionHelper;
import net.femtoparsec.units.property.NamedSimpleStandardMeasurementProperty;
import net.femtoparsec.units.named.quantity.AccelerationQuantity;
import javafx.beans.value.ObservableValue;

/**
 * @author Bastien Aracil
 */
public class AccelerationProperty extends NamedSimpleStandardMeasurementProperty<AccelerationQuantity, AccelerationUnit, Acceleration, AccelerationBinding> {

    public AccelerationProperty() {
        super(AccelerationBinding.FACTORY);
    }

    public AccelerationProperty(Object bean, String name) {
        super(bean, name, AccelerationBinding.FACTORY);
    }

    public AccelerationProperty(Object bean, String name, Acceleration initialValue) {
        super(bean, name, initialValue, AccelerationBinding.FACTORY);
    }

    public AccelerationProperty(Acceleration initialValue) {
        super(initialValue, AccelerationBinding.FACTORY);
    }

    public SpeedBinding multiply(Time time) {
        return NamedMeasurementExpressionHelper.multiply(SpeedBinding.FACTORY, this, time, Units.METER_PER_SECOND);
    }

    public SpeedBinding multiply(ObservableValue<Time> time) {
        return NamedMeasurementExpressionHelper.multiply(SpeedBinding.FACTORY, this, time, Units.METER_PER_SECOND);
    }

}
