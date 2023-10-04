package net.femtoparsec.units.property.measurement;

import net.femtoparsec.units.named.Units;
import net.femtoparsec.units.named.measurement.Speed;
import net.femtoparsec.units.named.measurement.Time;
import net.femtoparsec.units.named.unit.VelocityUnit;
import net.femtoparsec.units.property.NamedBindingFactory;
import net.femtoparsec.units.property.NamedMeasurementExpressionHelper;
import net.femtoparsec.units.property.NamedStandardMeasurementBinding;
import net.femtoparsec.units.named.quantity.VelocityQuantity;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;

/**
 * @author Bastien Aracil
 */
public class SpeedBinding extends NamedStandardMeasurementBinding<VelocityQuantity, VelocityUnit, Speed, SpeedBinding> {

    public static final NamedBindingFactory<VelocityQuantity, VelocityUnit, Speed, SpeedBinding> FACTORY = SpeedBinding::new;

    public SpeedBinding(BindingComputer<Speed> computer, Observable... dependencies) {
        super(computer, FACTORY);
        this.bind(dependencies);
    }

    public LengthBinding multiply(Time time) {
        return NamedMeasurementExpressionHelper.multiply(LengthBinding.FACTORY, this, time, Units.METER);
    }

    public LengthBinding multiply(ObservableValue<Time> time) {
        return NamedMeasurementExpressionHelper.multiply(LengthBinding.FACTORY, this, time, Units.METER);
    }

}
