package net.femtoparsec.units.property.measurement;

import net.femtoparsec.units.named.measurement.Viscosity;
import net.femtoparsec.units.named.unit.ViscosityUnit;
import net.femtoparsec.units.property.NamedBindingFactory;
import net.femtoparsec.units.property.NamedStandardMeasurementBinding;
import net.femtoparsec.units.named.quantity.DynamicViscosityQuantity;
import javafx.beans.Observable;


/**
 * @author Bastien Aracil
 */
public class ViscosityBinding extends NamedStandardMeasurementBinding<DynamicViscosityQuantity, ViscosityUnit, Viscosity, ViscosityBinding> {

    public static final NamedBindingFactory<DynamicViscosityQuantity, ViscosityUnit, Viscosity, ViscosityBinding> FACTORY = ViscosityBinding::new;

    public ViscosityBinding(BindingComputer<Viscosity> computer, Observable... dependencies) {
        super(computer, FACTORY);
        super.bind(dependencies);
    }


}
