package net.femtoparsec.units.named.test;

import net.femtoparsec.units.named.measurement.Length;
import net.femtoparsec.units.named.measurement.QuarticMeter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Bastien Aracil
 */
public class TestVolume extends UnitTest {

    @Test
    public void testQuarticVolume() throws Exception {
        final Length l = Length.METER.create(2);
        final QuarticMeter c = l.cubic().multiply(l);

        Assertions.assertEquals(16, c.getValueInSI(), 0.1);

    }
}
