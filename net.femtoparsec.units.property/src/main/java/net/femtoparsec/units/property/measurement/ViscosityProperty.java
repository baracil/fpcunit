package net.femtoparsec.units.property.measurement;

import net.femtoparsec.units.named.measurement.Viscosity;
import net.femtoparsec.units.named.unit.ViscosityUnit;
import net.femtoparsec.units.property.NamedSimpleStandardMeasurementProperty;
import net.femtoparsec.units.named.quantity.DynamicViscosityQuantity;

/**
 * @author Bastien Aracil
 */
public class ViscosityProperty extends NamedSimpleStandardMeasurementProperty<DynamicViscosityQuantity, ViscosityUnit, Viscosity, ViscosityBinding> {

    public ViscosityProperty() {
        super(ViscosityBinding.FACTORY);
    }

    public ViscosityProperty(Object bean, String name) {
        super(bean, name, ViscosityBinding.FACTORY);
    }

    public ViscosityProperty(Object bean, String name, Viscosity initialValue) {
        super(bean, name, initialValue, ViscosityBinding.FACTORY);
    }

    public ViscosityProperty(Viscosity initialValue) {
        super(initialValue, ViscosityBinding.FACTORY);
    }


}
