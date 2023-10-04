package net.femtoparsec.units.property.measurement;

import net.femtoparsec.units.named.Units;
import net.femtoparsec.units.named.measurement.Frequency;
import net.femtoparsec.units.named.unit.FrequencyUnit;
import net.femtoparsec.units.property.NamedBindingFactory;
import net.femtoparsec.units.property.NamedMeasurementExpressionHelper;
import net.femtoparsec.units.property.NamedStandardMeasurementBinding;
import net.femtoparsec.units.named.quantity.FrequencyQuantity;
import javafx.beans.Observable;


/**
 * @author Bastien Aracil
 */
public class FrequencyBinding extends NamedStandardMeasurementBinding<FrequencyQuantity, FrequencyUnit, Frequency, FrequencyBinding> {

    public static final NamedBindingFactory<FrequencyQuantity, FrequencyUnit, Frequency, FrequencyBinding> FACTORY = FrequencyBinding::new;

    public FrequencyBinding(BindingComputer<Frequency> computer, Observable... dependencies) {
        super(computer, FACTORY);
        super.bind(dependencies);
    }

    public TimeBinding inverse() {
        return NamedMeasurementExpressionHelper.inverse(TimeBinding.FACTORY, this, Units.SECOND);
    }


}
