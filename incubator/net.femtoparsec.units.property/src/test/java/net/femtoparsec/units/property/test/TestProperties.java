package net.femtoparsec.units.property.test;

import javafx.beans.property.DoubleProperty;
import net.femtoparsec.units.named.Units;
import net.femtoparsec.units.property.MeasurementBindings;
import net.femtoparsec.units.property.measurement.FlowRateBinding;
import net.femtoparsec.units.property.measurement.FlowRateProperty;
import org.junit.jupiter.api.Test;

/**
 * @author Bastien Aracil
 */
public class TestProperties {

    @Test
    public void testFlowRateAdd() throws Exception {
        FlowRateProperty p1 = new FlowRateProperty(Units.CUBIC_METER_PER_SECOND.measurement(12));
        FlowRateProperty p2 = new FlowRateProperty(Units.CUBIC_METER_PER_SECOND.measurement(-12));

        FlowRateBinding p = p1.add(p2);

        System.out.println(p.getValue());

        p1.set(p1.get().negate());

        System.out.println(p.getValue());

    }

    @Test
    public void testDoubleBinding() throws Exception {
        FlowRateProperty p1 = new FlowRateProperty(Units.MILLILITER_PER_HOUR.measurement(12));
        DoubleProperty v = MeasurementBindings.inUnit(p1, Units.MICROLITER_PER_HOUR);

        System.out.println(v.get());

        v.set(24);
        System.out.println(p1);

    }
}
