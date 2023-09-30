package net.femtoparsec.units.named.test;


import net.femtoparsec.units.named.measurement.FlowRate;
import net.femtoparsec.units.named.measurement.HydraulicResistivity;
import net.femtoparsec.units.named.measurement.Pressure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Bastien Aracil
 */
public class TestHydraulicResistivity {

    @Test
    public void testConversion() throws Exception {
        final Pressure pressure = Pressure.PASCAL.create(1e6);
        final FlowRate flowRate = FlowRate.CUBIC_METER_PER_SECOND.create(5e-8);

        final HydraulicResistivity r = HydraulicResistivity.PASCAL_SECOND_PER_CUBIC_METER.create(1e6/5e-8);

        double v1 = pressure.getValueInUnit(Pressure.BAR) / flowRate.getValueInUnit(FlowRate.MICROLITER_PER_MINUTE);
        double v2 = r.getValueInUnit(HydraulicResistivity.BAR_MINUTE_PER_MICROLITER);

        Assertions.assertEquals(v1/v2-1, 0, 1e-6);


    }
}
