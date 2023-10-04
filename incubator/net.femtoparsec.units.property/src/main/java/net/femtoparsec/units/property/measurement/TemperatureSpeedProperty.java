package net.femtoparsec.units.property.measurement;

import net.femtoparsec.units.named.measurement.DeltaTemperature;
import net.femtoparsec.units.named.measurement.TemperatureSpeed;
import net.femtoparsec.units.named.measurement.Time;
import net.femtoparsec.units.named.unit.TemperatureSpeedUnit;
import net.femtoparsec.units.property.NamedMeasurementExpressionHelper;
import net.femtoparsec.units.property.NamedSimpleStandardMeasurementProperty;
import net.femtoparsec.units.named.quantity.TemperatureSpeedQuantity;
import javafx.beans.value.ObservableValue;

/**
 * @author Bastien Aracil
 */
public class TemperatureSpeedProperty extends NamedSimpleStandardMeasurementProperty<TemperatureSpeedQuantity, TemperatureSpeedUnit, TemperatureSpeed, TemperatureSpeedBinding> {

    public TemperatureSpeedProperty() {
        super(TemperatureSpeedBinding.FACTORY);
    }

    public TemperatureSpeedProperty(Object bean, String name) {
        super(bean, name, TemperatureSpeedBinding.FACTORY);
    }

    public TemperatureSpeedProperty(Object bean, String name, TemperatureSpeed initialValue) {
        super(bean, name, initialValue, TemperatureSpeedBinding.FACTORY);
    }

    public TemperatureSpeedProperty(TemperatureSpeed initialValue) {
        super(initialValue, TemperatureSpeedBinding.FACTORY);
    }

    public DeltaTemperatureBinding multiply(Time time) {
        return NamedMeasurementExpressionHelper.multiply(DeltaTemperatureBinding.FACTORY, this, time, DeltaTemperature.DELTA_KELVIN);
    }

    public DeltaTemperatureBinding multiply(ObservableValue<Time> time) {
        return NamedMeasurementExpressionHelper.multiply(DeltaTemperatureBinding.FACTORY, this, time, DeltaTemperature.DELTA_KELVIN);
    }

}
