package net.femtoparsec.units.property.measurement;

import net.femtoparsec.units.named.measurement.DeltaTemperature;
import net.femtoparsec.units.named.measurement.Temperature;
import net.femtoparsec.units.named.unit.TemperatureUnit;
import net.femtoparsec.units.property.NamedBindingFactory;
import net.femtoparsec.units.property.NamedMeasurementBinding;
import net.femtoparsec.units.property.NamedMeasurementExpressionHelper;
import net.femtoparsec.units.named.quantity.TemperatureQuantity;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;


/**
 * @author Bastien Aracil
 */
public class TemperatureBinding extends NamedMeasurementBinding<TemperatureQuantity, TemperatureUnit, Temperature, TemperatureBinding> {

    public static final NamedBindingFactory<TemperatureQuantity, TemperatureUnit, Temperature, TemperatureBinding> FACTORY = TemperatureBinding::new;

    public TemperatureBinding(BindingComputer<Temperature> computer, Observable... dependencies) {
        super(computer, FACTORY);
        super.bind(dependencies);
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
