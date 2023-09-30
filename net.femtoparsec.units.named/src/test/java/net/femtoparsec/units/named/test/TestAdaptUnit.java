package net.femtoparsec.units.named.test;

import net.femtoparsec.units.core.UnitUtils;
import net.femtoparsec.units.named.Units;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Bastien Aracil
 */
public class TestAdaptUnit extends UnitTest {

    @Test
    public void testDecrease() throws Exception {
        var m = Units.MILLIMETER.create(2000000);
        var m1 = UnitUtils.adaptUnit(m);
        Assertions.assertEquals(Units.KILOMETER, m1.getUnit());
    }

    @Test
    public void testIncrease() throws Exception {
        var m = Units.METER.create(0.02);
        var m1 = UnitUtils.adaptUnit(m);
        Assertions.assertEquals(Units.CENTIMETER, m1.getUnit());
    }

    @Test
    public void testTime() throws Exception {
        var m = Units.NANOMETER.create(0.1);
        var m1 = UnitUtils.adaptUnit(m);
        Assertions.assertEquals(Units.ANGSTROM, m1.getUnit());

    }
}
