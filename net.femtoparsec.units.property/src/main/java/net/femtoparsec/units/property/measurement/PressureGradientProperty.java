package net.femtoparsec.units.property.measurement;

import net.femtoparsec.units.named.measurement.Length;
import net.femtoparsec.units.named.measurement.Pressure;
import net.femtoparsec.units.named.measurement.PressureGradient;
import net.femtoparsec.units.named.quantity.PressureGradientQuantity;
import net.femtoparsec.units.named.unit.PressureGradientUnit;
import net.femtoparsec.units.property.NamedMeasurementExpressionHelper;
import net.femtoparsec.units.property.NamedSimpleStandardMeasurementProperty;
import javafx.beans.value.ObservableValue;

/**
 * @author Bastien Aracil
 */
public class PressureGradientProperty extends NamedSimpleStandardMeasurementProperty<PressureGradientQuantity, PressureGradientUnit, PressureGradient, PressureGradientBinding> {

    public PressureGradientProperty() {
        super(PressureGradientBinding.FACTORY);
    }

    public PressureGradientProperty(Object bean, String name) {
        super(bean, name, PressureGradientBinding.FACTORY);
    }

    public PressureGradientProperty(Object bean, String name, PressureGradient initialValue) {
        super(bean, name, initialValue, PressureGradientBinding.FACTORY);
    }

    public PressureGradientProperty(PressureGradient initialValue) {
        super(initialValue, PressureGradientBinding.FACTORY);
    }

    public PressureBinding multiply(ObservableValue<Length> length) {
        return NamedMeasurementExpressionHelper.multiply(PressureBinding.FACTORY, this, length, Pressure.PASCAL);
    }

}
