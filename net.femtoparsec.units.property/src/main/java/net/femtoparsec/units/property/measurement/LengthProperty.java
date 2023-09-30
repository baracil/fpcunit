package net.femtoparsec.units.property.measurement;

import net.femtoparsec.units.named.Units;
import net.femtoparsec.units.named.measurement.Length;
import net.femtoparsec.units.named.unit.LengthUnit;
import net.femtoparsec.units.property.NamedMeasurementExpressionHelper;
import net.femtoparsec.units.property.NamedSimpleStandardMeasurementProperty;
import net.femtoparsec.units.named.quantity.LengthQuantity;
import javafx.beans.value.ObservableValue;

/**
 * @author Bastien Aracil
 */
public class LengthProperty extends NamedSimpleStandardMeasurementProperty<LengthQuantity, LengthUnit, Length, LengthBinding> {

    public LengthProperty() {
        super(LengthBinding.FACTORY);
    }

    public LengthProperty(Object bean, String name) {
        super(bean, name, LengthBinding.FACTORY);
    }

    public LengthProperty(Object bean, String name, Length initialValue) {
        super(bean, name, initialValue, LengthBinding.FACTORY);
    }

    public LengthProperty(Length initialValue) {
        super(initialValue, LengthBinding.FACTORY);
    }

    public SurfaceBinding multiply(ObservableValue<Length> length) {
        if (length == this) {
            return square();
        }
        return NamedMeasurementExpressionHelper.multiply(SurfaceBinding.FACTORY, this, length, Units.SQUARE_METER);
    }

    public SurfaceBinding square() {
        return NamedMeasurementExpressionHelper.square(SurfaceBinding.FACTORY, this, Units.SQUARE_METER);
    }
}
