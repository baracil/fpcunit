package net.femtoparsec.units.property.measurement;

import net.femtoparsec.units.named.Units;
import net.femtoparsec.units.named.measurement.Diffusivity;
import net.femtoparsec.units.named.measurement.Time;
import net.femtoparsec.units.named.quantity.DiffusivityQuantity;
import net.femtoparsec.units.named.unit.DiffusivityUnit;
import net.femtoparsec.units.property.NamedMeasurementExpressionHelper;
import net.femtoparsec.units.property.NamedSimpleStandardMeasurementProperty;
import javafx.beans.value.ObservableValue;

/**
 * @author Bastien Aracil
 */
public class DiffusivityProperty extends NamedSimpleStandardMeasurementProperty<DiffusivityQuantity, DiffusivityUnit, Diffusivity, DiffusivityBinding> {

    public DiffusivityProperty() {
        super(DiffusivityBinding.FACTORY);
    }

    public DiffusivityProperty(Object bean, String name) {
        super(bean, name, DiffusivityBinding.FACTORY);
    }

    public DiffusivityProperty(Object bean, String name, Diffusivity initialValue) {
        super(bean, name, initialValue, DiffusivityBinding.FACTORY);
    }

    public DiffusivityProperty(Diffusivity initialValue) {
        super(initialValue, DiffusivityBinding.FACTORY);
    }

    public SurfaceBinding multiply(ObservableValue<Time> time) {
        return NamedMeasurementExpressionHelper.multiply(SurfaceBinding.FACTORY, this, time, Units.SQUARE_METER);
    }

}
