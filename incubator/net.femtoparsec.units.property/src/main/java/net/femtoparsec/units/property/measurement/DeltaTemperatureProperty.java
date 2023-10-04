package net.femtoparsec.units.property.measurement;

import net.femtoparsec.units.named.measurement.DeltaTemperature;
import net.femtoparsec.units.named.measurement.TemperatureSpeed;
import net.femtoparsec.units.named.measurement.Time;
import net.femtoparsec.units.named.unit.DeltaTemperatureUnit;
import net.femtoparsec.units.property.NamedMeasurementExpressionHelper;
import net.femtoparsec.units.property.NamedSimpleStandardMeasurementProperty;
import net.femtoparsec.units.named.quantity.DeltaTemperatureQuantity;
import javafx.beans.value.ObservableValue;

/**
 * @author Bastien Aracil
 */
public class DeltaTemperatureProperty extends NamedSimpleStandardMeasurementProperty<DeltaTemperatureQuantity, DeltaTemperatureUnit, DeltaTemperature, DeltaTemperatureBinding> {

    public DeltaTemperatureProperty() {
        super(DeltaTemperatureBinding.FACTORY);
    }

    public DeltaTemperatureProperty(Object bean, String name) {
        super(bean, name, DeltaTemperatureBinding.FACTORY);
    }

    public DeltaTemperatureProperty(Object bean, String name, DeltaTemperature initialValue) {
        super(bean, name, initialValue, DeltaTemperatureBinding.FACTORY);
    }

    public DeltaTemperatureProperty(DeltaTemperature initialValue) {
        super(initialValue, DeltaTemperatureBinding.FACTORY);
    }


    public TemperatureSpeedBinding divideByTime(Time time) {
        return NamedMeasurementExpressionHelper.divide(TemperatureSpeedBinding.FACTORY, this, time, TemperatureSpeed.KELVIN_PER_SECOND);
    }

    public TemperatureSpeedBinding divideByTime(ObservableValue<Time> time) {
        return NamedMeasurementExpressionHelper.divide(TemperatureSpeedBinding.FACTORY, this, time, TemperatureSpeed.KELVIN_PER_SECOND);
    }

    public TimeBinding divideByTemperatureSpeed(TemperatureSpeed temperatureSpeed) {
        return NamedMeasurementExpressionHelper.divide(TimeBinding.FACTORY, this, temperatureSpeed, Time.SECOND);
    }

    public TimeBinding divideByTemperatureSpeed(ObservableValue<TemperatureSpeed> temperatureSpeed) {
        return NamedMeasurementExpressionHelper.divide(TimeBinding.FACTORY, this, temperatureSpeed, Time.SECOND);
    }
}
