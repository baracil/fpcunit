package net.femtoparsec.units.property.measurement;

import net.femtoparsec.units.named.Units;
import net.femtoparsec.units.named.measurement.Acceleration;
import net.femtoparsec.units.named.measurement.Time;
import net.femtoparsec.units.named.unit.AccelerationUnit;
import net.femtoparsec.units.property.NamedBindingFactory;
import net.femtoparsec.units.property.NamedMeasurementExpressionHelper;
import net.femtoparsec.units.property.NamedStandardMeasurementBinding;
import net.femtoparsec.units.named.quantity.AccelerationQuantity;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;

/**
 * @author Bastien Aracil
 */
public class AccelerationBinding extends NamedStandardMeasurementBinding<AccelerationQuantity, AccelerationUnit, Acceleration, AccelerationBinding> {

    public static final NamedBindingFactory<AccelerationQuantity, AccelerationUnit, Acceleration, AccelerationBinding> FACTORY = AccelerationBinding::new;

    public AccelerationBinding(BindingComputer<Acceleration> computer, Observable... dependencies) {
        super(computer, FACTORY);
        this.bind(dependencies);
    }

    public SpeedBinding multiply(Time time) {
        return NamedMeasurementExpressionHelper.multiply(SpeedBinding.FACTORY, this, time, Units.METER_PER_SECOND);
    }

    public SpeedBinding multiply(ObservableValue<Time> time) {
        return NamedMeasurementExpressionHelper.multiply(SpeedBinding.FACTORY, this, time, Units.METER_PER_SECOND);
    }

}
