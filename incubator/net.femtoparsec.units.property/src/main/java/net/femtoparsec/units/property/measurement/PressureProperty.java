package net.femtoparsec.units.property.measurement;

import net.femtoparsec.units.named.measurement.Pressure;
import net.femtoparsec.units.named.unit.PressureUnit;
import net.femtoparsec.units.property.NamedSimpleStandardMeasurementProperty;
import net.femtoparsec.units.named.quantity.PressureQuantity;

/**
 * @author Bastien Aracil
 */
public class PressureProperty extends NamedSimpleStandardMeasurementProperty<PressureQuantity, PressureUnit, Pressure, PressureBinding> {

    public PressureProperty() {
        super(PressureBinding.FACTORY);
    }

    public PressureProperty(Object bean, String name) {
        super(bean, name, PressureBinding.FACTORY);
    }

    public PressureProperty(Object bean, String name, Pressure initialValue) {
        super(bean, name, initialValue, PressureBinding.FACTORY);
    }

    public PressureProperty(Pressure initialValue) {
        super(initialValue, PressureBinding.FACTORY);
    }


}
