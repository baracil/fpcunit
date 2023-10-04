package net.femtoparsec.units.property.measurement;

import net.femtoparsec.units.named.Units;
import net.femtoparsec.units.named.measurement.ReciprocalVolume;
import net.femtoparsec.units.named.measurement.ShearRate;
import net.femtoparsec.units.named.measurement.Viscosity;
import net.femtoparsec.units.named.measurement.Volume;
import net.femtoparsec.units.named.unit.ShearRateUnit;
import net.femtoparsec.units.property.NamedMeasurementExpressionHelper;
import net.femtoparsec.units.property.NamedSimpleStandardMeasurementProperty;
import net.femtoparsec.units.named.quantity.ShearRateQuantity;
import javafx.beans.value.ObservableValue;

/**
 * @author Bastien Aracil
 */
public class ShearRateProperty extends NamedSimpleStandardMeasurementProperty<ShearRateQuantity, ShearRateUnit, ShearRate, ShearRateBinding> {

    public ShearRateProperty() {
        super(ShearRateBinding.FACTORY);
    }

    public ShearRateProperty(Object bean, String name) {
        super(bean, name, ShearRateBinding.FACTORY);
    }

    public ShearRateProperty(Object bean, String name, ShearRate initialValue) {
        super(bean, name, initialValue, ShearRateBinding.FACTORY);
    }

    public ShearRateProperty(ShearRate initialValue) {
        super(initialValue, ShearRateBinding.FACTORY);
    }


    public PressureBinding multiply(Viscosity viscosity) {
        return NamedMeasurementExpressionHelper.multiply(PressureBinding.FACTORY, this, viscosity, Units.PASCAL);
    }

    public FlowRateBinding multiply(Volume volume) {
        return NamedMeasurementExpressionHelper.multiply(FlowRateBinding.FACTORY, this, volume, Units.CUBIC_METER_PER_SECOND);
    }

    public FlowRateBinding divide(ReciprocalVolume reciprocalVolume) {
        return NamedMeasurementExpressionHelper.divide(FlowRateBinding.FACTORY, this, reciprocalVolume, Units.CUBIC_METER_PER_SECOND);
    }


    public PressureBinding multiplyViscosity(ObservableValue<Viscosity> time) {
        return NamedMeasurementExpressionHelper.multiply(PressureBinding.FACTORY, this, time, Units.PASCAL);
    }

    public FlowRateBinding multiplyVolume(ObservableValue<Volume> time) {
        return NamedMeasurementExpressionHelper.multiply(FlowRateBinding.FACTORY, this, time, Units.CUBIC_METER_PER_SECOND);
    }

    public FlowRateBinding divideReciprocalVolume(ObservableValue<ReciprocalVolume> reciprocalVolume) {
        return NamedMeasurementExpressionHelper.divide(FlowRateBinding.FACTORY, this, reciprocalVolume, Units.CUBIC_METER_PER_SECOND);
    }



}
