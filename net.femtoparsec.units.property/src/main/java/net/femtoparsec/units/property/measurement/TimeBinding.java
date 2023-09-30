package net.femtoparsec.units.property.measurement;

import net.femtoparsec.units.named.Units;
import net.femtoparsec.units.named.measurement.Time;
import net.femtoparsec.units.named.unit.TimeUnit;
import net.femtoparsec.units.property.NamedBindingFactory;
import net.femtoparsec.units.property.NamedMeasurementExpressionHelper;
import net.femtoparsec.units.property.NamedStandardMeasurementBinding;
import net.femtoparsec.units.named.quantity.TimeQuantity;
import javafx.beans.Observable;

/**
 * @author Bastien Aracil
 */
public class TimeBinding extends NamedStandardMeasurementBinding<TimeQuantity, TimeUnit, Time, TimeBinding> {

    public static final NamedBindingFactory<TimeQuantity, TimeUnit, Time, TimeBinding> FACTORY = TimeBinding::new;

    public TimeBinding(BindingComputer<Time> computer, Observable... dependencies) {
        super(computer, FACTORY);
        this.bind(dependencies);
    }

    public FrequencyBinding inverse() {
        return NamedMeasurementExpressionHelper.inverse(FrequencyBinding.FACTORY, this, Units.HERTZ);
    }
}
