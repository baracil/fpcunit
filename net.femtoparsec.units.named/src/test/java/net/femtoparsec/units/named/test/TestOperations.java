package net.femtoparsec.units.named.test;

import net.femtoparsec.units.core.MeasurementOperations;
import net.femtoparsec.units.named.Units;
import net.femtoparsec.units.named.measurement.Length;
import net.femtoparsec.units.named.measurement.Surface;
import net.femtoparsec.units.named.measurement.Volume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Bastien Aracil
 */
public class TestOperations extends UnitTest {

    @Test
    public void testVolumeOverSurface() throws Exception {
        Volume v = Units.CUBIC_METER.create(1000);
        Surface s = Units.SQUARE_METER.create(100);

        Length length = v.divide(s);

        Assertions.assertEquals(10, length.getValueInSI(), 0.01);
    }

    @Test
    public void testIncompatibleUnits() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Length l1 = Units.METER.create(1);
            Length l2 = Units.METER.create(2);

            Volume v = MeasurementOperations.multiply(l1, l2, Units.CUBIC_METER);
        });
    }
}
