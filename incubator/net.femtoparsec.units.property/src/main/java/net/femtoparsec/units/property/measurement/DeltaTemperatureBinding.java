package net.femtoparsec.units.property.measurement;

import net.femtoparsec.units.named.measurement.DeltaTemperature;
import net.femtoparsec.units.named.unit.DeltaTemperatureUnit;
import net.femtoparsec.units.property.NamedBindingFactory;
import net.femtoparsec.units.property.NamedStandardMeasurementBinding;
import net.femtoparsec.units.named.quantity.DeltaTemperatureQuantity;
import javafx.beans.Observable;

/**
 * @author Bastien Aracil
 */
public class DeltaTemperatureBinding extends NamedStandardMeasurementBinding<DeltaTemperatureQuantity, DeltaTemperatureUnit, DeltaTemperature, DeltaTemperatureBinding> {

    public static final NamedBindingFactory<DeltaTemperatureQuantity, DeltaTemperatureUnit, DeltaTemperature, DeltaTemperatureBinding> FACTORY = DeltaTemperatureBinding::new;

    public DeltaTemperatureBinding(BindingComputer<DeltaTemperature> computer, Observable... dependencies) {
        super(computer, FACTORY);
        this.bind(dependencies);
    }

}
