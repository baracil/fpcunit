package net.femtoparsec.units.property.measurement;

import net.femtoparsec.units.named.Units;
import net.femtoparsec.units.named.measurement.TemperatureSpeed;
import net.femtoparsec.units.named.measurement.Time;
import net.femtoparsec.units.named.unit.TemperatureSpeedUnit;
import net.femtoparsec.units.property.NamedBindingFactory;
import net.femtoparsec.units.property.NamedMeasurementExpressionHelper;
import net.femtoparsec.units.property.NamedStandardMeasurementBinding;
import net.femtoparsec.units.named.quantity.TemperatureSpeedQuantity;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;


/**
 * @author Bastien Aracil
 */
public class TemperatureSpeedBinding extends NamedStandardMeasurementBinding<TemperatureSpeedQuantity, TemperatureSpeedUnit, TemperatureSpeed, TemperatureSpeedBinding> {

    public static final NamedBindingFactory<TemperatureSpeedQuantity, TemperatureSpeedUnit, TemperatureSpeed, TemperatureSpeedBinding> FACTORY = TemperatureSpeedBinding::new;

    public TemperatureSpeedBinding(BindingComputer<TemperatureSpeed> computer, Observable... dependencies) {
        super(computer, FACTORY);
        super.bind(dependencies);
    }

    public DeltaTemperatureBinding multiply(Time time) {
        return NamedMeasurementExpressionHelper.multiply(DeltaTemperatureBinding.FACTORY, this, time, Units.DELTA_KELVIN);
    }

    public DeltaTemperatureBinding multiply(ObservableValue<Time> time) {
        return NamedMeasurementExpressionHelper.multiply(DeltaTemperatureBinding.FACTORY, this, time, Units.DELTA_KELVIN);
    }


}
