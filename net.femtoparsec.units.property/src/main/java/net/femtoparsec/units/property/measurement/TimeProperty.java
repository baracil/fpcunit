package net.femtoparsec.units.property.measurement;

import net.femtoparsec.units.named.Units;
import net.femtoparsec.units.named.measurement.Time;
import net.femtoparsec.units.named.unit.TimeUnit;
import net.femtoparsec.units.property.NamedMeasurementExpressionHelper;
import net.femtoparsec.units.property.NamedSimpleStandardMeasurementProperty;
import net.femtoparsec.units.named.quantity.TimeQuantity;

/**
 * @author Bastien Aracil
 */
public class TimeProperty extends NamedSimpleStandardMeasurementProperty<TimeQuantity, TimeUnit, Time, TimeBinding> {

    public TimeProperty() {
        super(TimeBinding.FACTORY);
    }

    public TimeProperty(Object bean, String name) {
        super(bean, name, TimeBinding.FACTORY);
    }

    public TimeProperty(Object bean, String name, Time initialValue) {
        super(bean, name, initialValue, TimeBinding.FACTORY);
    }

    public TimeProperty(Time initialValue) {
        super(initialValue, TimeBinding.FACTORY);
    }

    public FrequencyBinding inverse() {
        return NamedMeasurementExpressionHelper.inverse(FrequencyBinding.FACTORY, this, Units.HERTZ);
    }

}
