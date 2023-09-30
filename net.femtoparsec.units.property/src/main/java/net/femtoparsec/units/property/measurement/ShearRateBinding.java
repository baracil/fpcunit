package net.femtoparsec.units.property.measurement;

import net.femtoparsec.units.named.measurement.ShearRate;
import net.femtoparsec.units.named.unit.ShearRateUnit;
import net.femtoparsec.units.property.NamedBindingFactory;
import net.femtoparsec.units.property.NamedStandardMeasurementBinding;
import net.femtoparsec.units.named.quantity.ShearRateQuantity;
import javafx.beans.Observable;

/**
 * @author Bastien Aracil
 */
public class ShearRateBinding extends NamedStandardMeasurementBinding<ShearRateQuantity, ShearRateUnit, ShearRate, ShearRateBinding> {

    public static final NamedBindingFactory<ShearRateQuantity, ShearRateUnit, ShearRate, ShearRateBinding> FACTORY = ShearRateBinding::new;

    public ShearRateBinding(BindingComputer<ShearRate> computer, Observable... dependencies) {
        super(computer, FACTORY);
        this.bind(dependencies);
    }

}
