package net.femtoparsec.units.property.measurement;

import net.femtoparsec.units.named.Units;
import net.femtoparsec.units.named.measurement.Length;
import net.femtoparsec.units.named.unit.LengthUnit;
import net.femtoparsec.units.property.NamedBindingFactory;
import net.femtoparsec.units.property.NamedMeasurementExpressionHelper;
import net.femtoparsec.units.property.NamedStandardMeasurementBinding;
import net.femtoparsec.units.named.quantity.LengthQuantity;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;

/**
 * @author Bastien Aracil
 */
public class LengthBinding extends NamedStandardMeasurementBinding<LengthQuantity, LengthUnit, Length, LengthBinding> {

    public static final NamedBindingFactory<LengthQuantity, LengthUnit, Length, LengthBinding> FACTORY = LengthBinding::new;

    public LengthBinding(BindingComputer<Length> computer, Observable... dependencies) {
        super(computer, FACTORY);
        this.bind(dependencies);
    }

    public SurfaceBinding multiply(ObservableValue<Length> length) {
        return NamedMeasurementExpressionHelper.multiply(SurfaceBinding.FACTORY, this, length, Units.SQUARE_METER);
    }

    public SurfaceBinding square() {
        return NamedMeasurementExpressionHelper.square(SurfaceBinding.FACTORY, this, Units.SQUARE_METER);
    }

}
