package net.femtoparsec.units.property.measurement;

import net.femtoparsec.units.named.measurement.DeltaTemperature;
import net.femtoparsec.units.named.measurement.Temperature;
import net.femtoparsec.units.named.unit.TemperatureUnit;
import net.femtoparsec.units.property.NamedMeasurementExpressionHelper;
import net.femtoparsec.units.property.NamedSimpleMeasurementProperty;
import net.femtoparsec.units.named.quantity.TemperatureQuantity;
import javafx.beans.value.ObservableValue;

/**
 * @author Bastien Aracil
 */
public class TemperatureProperty extends NamedSimpleMeasurementProperty<TemperatureQuantity, TemperatureUnit, Temperature, TemperatureBinding> {

    public TemperatureProperty() {
        super(TemperatureBinding.FACTORY);
    }

    public TemperatureProperty(Object bean, String name) {
        super(bean, name, TemperatureBinding.FACTORY);
    }

    public TemperatureProperty(Object bean, String name, Temperature initialValue) {
        super(bean, name, initialValue, TemperatureBinding.FACTORY);
    }

    public TemperatureProperty(Temperature initialValue) {
        super(initialValue, TemperatureBinding.FACTORY);
    }

    public TemperatureBinding add(DeltaTemperature deltaTemperature) {
        return NamedMeasurementExpressionHelper.add(this, deltaTemperature);
    }

    public TemperatureBinding add(ObservableValue<DeltaTemperature> deltaTemperature) {
        return NamedMeasurementExpressionHelper.add(this, deltaTemperature);
    }

    public TemperatureBinding subtract(DeltaTemperature deltaTemperature) {
        return NamedMeasurementExpressionHelper.add(this, deltaTemperature);
    }

    public TemperatureBinding subtract(ObservableValue<DeltaTemperature> deltaTemperature) {
        return NamedMeasurementExpressionHelper.add(this, deltaTemperature);
    }

    public DeltaTemperatureBinding difference(Temperature temperature) {
        return NamedMeasurementExpressionHelper.difference(this, temperature);
    }

    public DeltaTemperatureBinding difference(ObservableValue<Temperature> temperature) {
        return NamedMeasurementExpressionHelper.difference(this, temperature);
    }
}
