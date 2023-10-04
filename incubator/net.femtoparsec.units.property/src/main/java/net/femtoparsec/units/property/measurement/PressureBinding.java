package net.femtoparsec.units.property.measurement;

import net.femtoparsec.units.named.Units;
import net.femtoparsec.units.named.measurement.*;
import net.femtoparsec.units.named.unit.PressureUnit;
import net.femtoparsec.units.property.NamedBindingFactory;
import net.femtoparsec.units.property.NamedMeasurementExpressionHelper;
import net.femtoparsec.units.property.NamedStandardMeasurementBinding;
import net.femtoparsec.units.named.quantity.PressureQuantity;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;

/**
 * @author Bastien Aracil
 */
public class PressureBinding extends NamedStandardMeasurementBinding<PressureQuantity, PressureUnit, Pressure, PressureBinding> {

    public static final NamedBindingFactory<PressureQuantity, PressureUnit, Pressure, PressureBinding> FACTORY = PressureBinding::new;

    public PressureBinding(BindingComputer<Pressure> computer, Observable... dependencies) {
        super(computer, FACTORY);
        this.bind(dependencies);
    }

    public SurfaceBinding multiply(ObservableValue<Length> length) {
        return NamedMeasurementExpressionHelper.multiply(SurfaceBinding.FACTORY, this, length, Units.SQUARE_METER);
    }

    public SurfaceBinding square() {
        return NamedMeasurementExpressionHelper.square(SurfaceBinding.FACTORY, this, Units.SQUARE_METER);
    }

    public ViscosityBinding divideShearRate(ObservableValue<ShearRate> shearRate) {
        return NamedMeasurementExpressionHelper.divide(ViscosityBinding.FACTORY, this, shearRate, Viscosity.PASCAL_SECOND);
    }

    public ShearRateBinding divideViscosity(ObservableValue<Viscosity> viscosity) {
        return NamedMeasurementExpressionHelper.divide(ShearRateBinding.FACTORY, this, viscosity, ShearRate.SHEAR_RATE);
    }

    public FlowRateBinding divideHydraulicResistivity(ObservableValue<HydraulicResistivity> hydraulicResistivity) {
        return NamedMeasurementExpressionHelper.divide(FlowRateBinding.FACTORY, this, hydraulicResistivity, FlowRate.CUBIC_METER_PER_SECOND);
    }
}
