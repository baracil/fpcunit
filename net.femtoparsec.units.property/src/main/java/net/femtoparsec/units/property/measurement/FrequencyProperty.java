package net.femtoparsec.units.property.measurement;

import net.femtoparsec.units.named.Units;
import net.femtoparsec.units.named.measurement.Frequency;
import net.femtoparsec.units.named.unit.FrequencyUnit;
import net.femtoparsec.units.property.NamedMeasurementExpressionHelper;
import net.femtoparsec.units.property.NamedSimpleStandardMeasurementProperty;
import net.femtoparsec.units.named.quantity.FrequencyQuantity;

/**
 * @author Bastien Aracil
 */
public class FrequencyProperty extends NamedSimpleStandardMeasurementProperty<FrequencyQuantity, FrequencyUnit, Frequency, FrequencyBinding> {

    public FrequencyProperty() {
        super(FrequencyBinding.FACTORY);
    }

    public FrequencyProperty(Object bean, String name) {
        super(bean, name, FrequencyBinding.FACTORY);
    }

    public FrequencyProperty(Object bean, String name, Frequency initialValue) {
        super(bean, name, initialValue, FrequencyBinding.FACTORY);
    }

    public FrequencyProperty(Frequency initialValue) {
        super(initialValue, FrequencyBinding.FACTORY);
    }

    public TimeBinding inverse() {
        return NamedMeasurementExpressionHelper.inverse(TimeBinding.FACTORY, this, Units.SECOND);
    }

}
