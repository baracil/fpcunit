package net.femtoparsec.units.property.measurement;

import net.femtoparsec.units.named.Units;
import net.femtoparsec.units.named.measurement.FlowRate;
import net.femtoparsec.units.named.measurement.Surface;
import net.femtoparsec.units.named.unit.FlowRateUnit;
import net.femtoparsec.units.property.NamedMeasurementExpressionHelper;
import net.femtoparsec.units.property.NamedSimpleStandardMeasurementProperty;
import net.femtoparsec.units.named.quantity.VolumetricFlowRateQuantity;
import javafx.beans.value.ObservableValue;

/**
 * @author Bastien Aracil
 */
public class FlowRateProperty extends NamedSimpleStandardMeasurementProperty<VolumetricFlowRateQuantity, FlowRateUnit, FlowRate, FlowRateBinding> {

    public FlowRateProperty() {
        super(FlowRateBinding.FACTORY);
    }

    public FlowRateProperty(Object bean, String name) {
        super(bean, name, FlowRateBinding.FACTORY);
    }

    public FlowRateProperty(Object bean, String name, FlowRate initialValue) {
        super(bean, name, initialValue, FlowRateBinding.FACTORY);
    }

    public FlowRateProperty(FlowRate initialValue) {
        super(initialValue, FlowRateBinding.FACTORY);
    }

    public SpeedBinding multiply(Surface surface) {
        return NamedMeasurementExpressionHelper
                .multiply(SpeedBinding.FACTORY, this, surface, Units.METER_PER_SECOND);
    }

    public SpeedBinding multiply(ObservableValue<Surface> surface) {
        return NamedMeasurementExpressionHelper.multiply(SpeedBinding.FACTORY, this, surface, Units.METER_PER_SECOND);
    }

}
