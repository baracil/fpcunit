package net.femtoparsec.units.named.test;

import net.femtoparsec.units.named.Units;
import net.femtoparsec.units.named.measurement.FlowRate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Bastien Aracil
 */
public class TestFlowRate extends UnitTest {

    @Test
    public void testFlowRate() throws Exception {
        FlowRate flowRate = Units.MILLILITER_PER_MINUTE.create(1);

        Assertions.assertEquals(flowRate.getValueInUnit(FlowRate.MICROLITER_PER_HOUR), 1*1e3*60, 0.001);
        Assertions.assertEquals(flowRate.getValueInUnit(FlowRate.MILLILITER_PER_HOUR), 60, 0.001);
        Assertions.assertEquals(flowRate.getValueInUnit(FlowRate.MICROLITER_PER_MINUTE), 1*1e3, 0.001);

    }
}
