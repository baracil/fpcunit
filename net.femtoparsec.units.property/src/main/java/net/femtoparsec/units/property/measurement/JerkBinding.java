package net.femtoparsec.units.property.measurement;

import net.femtoparsec.units.named.Units;
import net.femtoparsec.units.named.measurement.Jerk;
import net.femtoparsec.units.named.measurement.Time;
import net.femtoparsec.units.named.unit.JerkUnit;
import net.femtoparsec.units.property.NamedBindingFactory;
import net.femtoparsec.units.property.NamedMeasurementExpressionHelper;
import net.femtoparsec.units.property.NamedStandardMeasurementBinding;
import net.femtoparsec.units.named.quantity.JerkQuantity;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;

/**
 * @author Bastien Aracil
 */
public class JerkBinding extends NamedStandardMeasurementBinding<JerkQuantity, JerkUnit, Jerk, JerkBinding> {

    public static final NamedBindingFactory<JerkQuantity, JerkUnit, Jerk, JerkBinding> FACTORY = JerkBinding::new;

    public JerkBinding(BindingComputer<Jerk> computer, Observable... dependencies) {
        super(computer, FACTORY);
        this.bind(dependencies);
    }

    public AccelerationBinding multiply(Time time) {
        return NamedMeasurementExpressionHelper.multiply(AccelerationBinding.FACTORY, this, time, Units.METER_PER_SQUARE_SECOND);
    }

    public AccelerationBinding multiply(ObservableValue<Time> time) {
        return NamedMeasurementExpressionHelper.multiply(AccelerationBinding.FACTORY, this, time, Units.METER_PER_SQUARE_SECOND);
    }

}
