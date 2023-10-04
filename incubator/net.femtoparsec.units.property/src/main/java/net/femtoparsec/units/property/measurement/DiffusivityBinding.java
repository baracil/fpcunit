package net.femtoparsec.units.property.measurement;

import net.femtoparsec.units.named.Units;
import net.femtoparsec.units.named.measurement.Diffusivity;
import net.femtoparsec.units.named.measurement.Time;
import net.femtoparsec.units.named.quantity.DiffusivityQuantity;
import net.femtoparsec.units.named.unit.DiffusivityUnit;
import net.femtoparsec.units.property.NamedBindingFactory;
import net.femtoparsec.units.property.NamedMeasurementExpressionHelper;
import net.femtoparsec.units.property.NamedStandardMeasurementBinding;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;


/**
 * @author Bastien Aracil
 */
public class DiffusivityBinding extends NamedStandardMeasurementBinding<DiffusivityQuantity, DiffusivityUnit, Diffusivity, DiffusivityBinding> {

    public static final NamedBindingFactory<DiffusivityQuantity, DiffusivityUnit, Diffusivity, DiffusivityBinding> FACTORY = DiffusivityBinding::new;

    public DiffusivityBinding(BindingComputer<Diffusivity> computer, Observable... dependencies) {
        super(computer, FACTORY);
        super.bind(dependencies);
    }

    public SurfaceBinding multiply(Time time) {
        return NamedMeasurementExpressionHelper.multiply(SurfaceBinding.FACTORY, this, time, Units.SQUARE_METER);
    }

    public SurfaceBinding multiply(ObservableValue<Time> time) {
        return NamedMeasurementExpressionHelper.multiply(SurfaceBinding.FACTORY, this, time, Units.SQUARE_METER);
    }

}
