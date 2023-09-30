package net.femtoparsec.units.property.measurement;

import net.femtoparsec.units.named.Units;
import net.femtoparsec.units.named.measurement.FlowRate;
import net.femtoparsec.units.named.measurement.Surface;
import net.femtoparsec.units.named.unit.FlowRateUnit;
import net.femtoparsec.units.property.NamedBindingFactory;
import net.femtoparsec.units.property.NamedMeasurementExpressionHelper;
import net.femtoparsec.units.property.NamedStandardMeasurementBinding;
import net.femtoparsec.units.named.quantity.VolumetricFlowRateQuantity;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;


/**
 * @author Bastien Aracil
 */
public class FlowRateBinding extends NamedStandardMeasurementBinding<VolumetricFlowRateQuantity, FlowRateUnit, FlowRate, FlowRateBinding> {

    public static final NamedBindingFactory<VolumetricFlowRateQuantity, FlowRateUnit, FlowRate, FlowRateBinding> FACTORY = FlowRateBinding::new;

    public FlowRateBinding(BindingComputer<FlowRate> computer, Observable... dependencies) {
        super(computer, FACTORY);
        super.bind(dependencies);
    }

    public SpeedBinding multiply(Surface surface) {
        return NamedMeasurementExpressionHelper.multiply(SpeedBinding.FACTORY, this, surface, Units.METER_PER_SECOND);
    }

    public SpeedBinding multiply(ObservableValue<Surface> surface) {
        return NamedMeasurementExpressionHelper.multiply(SpeedBinding.FACTORY, this, surface, Units.METER_PER_SECOND);
    }

}
