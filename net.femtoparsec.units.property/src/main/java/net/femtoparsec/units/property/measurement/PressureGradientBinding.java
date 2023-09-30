package net.femtoparsec.units.property.measurement;

import net.femtoparsec.units.named.Units;
import net.femtoparsec.units.named.measurement.Length;
import net.femtoparsec.units.named.measurement.PressureGradient;
import net.femtoparsec.units.named.quantity.PressureGradientQuantity;
import net.femtoparsec.units.named.unit.PressureGradientUnit;
import net.femtoparsec.units.property.NamedBindingFactory;
import net.femtoparsec.units.property.NamedMeasurementExpressionHelper;
import net.femtoparsec.units.property.NamedStandardMeasurementBinding;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;

/**
 * @author Bastien Aracil
 */
public class PressureGradientBinding extends NamedStandardMeasurementBinding<PressureGradientQuantity, PressureGradientUnit, PressureGradient, PressureGradientBinding> {

    public static final NamedBindingFactory<PressureGradientQuantity, PressureGradientUnit, PressureGradient, PressureGradientBinding> FACTORY = PressureGradientBinding::new;

    public PressureGradientBinding(BindingComputer<PressureGradient> computer, Observable... dependencies) {
        super(computer, FACTORY);
        this.bind(dependencies);
    }

    public PressureBinding multiply(ObservableValue<Length> length) {
        return NamedMeasurementExpressionHelper.multiply(PressureBinding.FACTORY, this, length, Units.PASCAL);
    }

}
