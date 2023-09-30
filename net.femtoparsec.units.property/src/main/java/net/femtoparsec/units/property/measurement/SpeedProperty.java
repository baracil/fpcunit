package net.femtoparsec.units.property.measurement;

import net.femtoparsec.units.named.Units;
import net.femtoparsec.units.named.measurement.Speed;
import net.femtoparsec.units.named.measurement.Time;
import net.femtoparsec.units.named.unit.VelocityUnit;
import net.femtoparsec.units.property.NamedMeasurementExpressionHelper;
import net.femtoparsec.units.property.NamedSimpleStandardMeasurementProperty;
import net.femtoparsec.units.named.quantity.VelocityQuantity;
import javafx.beans.value.ObservableValue;

/**
 * @author Bastien Aracil
 */
public class SpeedProperty extends NamedSimpleStandardMeasurementProperty<VelocityQuantity, VelocityUnit, Speed, SpeedBinding> {

    public SpeedProperty() {
        super(SpeedBinding.FACTORY);
    }

    public SpeedProperty(Object bean, String name) {
        super(bean, name, SpeedBinding.FACTORY);
    }

    public SpeedProperty(Object bean, String name, Speed initialValue) {
        super(bean, name, initialValue, SpeedBinding.FACTORY);
    }

    public SpeedProperty(Speed initialValue) {
        super(initialValue, SpeedBinding.FACTORY);
    }

    public LengthBinding multiply(Time time) {
        return NamedMeasurementExpressionHelper.multiply(LengthBinding.FACTORY, this, time, Units.METER);
    }

    public LengthBinding multiply(ObservableValue<Time> time) {
        return NamedMeasurementExpressionHelper.multiply(LengthBinding.FACTORY, this, time, Units.METER);
    }

}
