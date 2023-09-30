package net.femtoparsec.units.named.test;

import net.femtoparsec.units.core.UnitUtils;
import net.femtoparsec.units.named.Units;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Bastien Aracil
 */
public class TestUnitUtils  extends UnitTest {

    @Test
    public void testUnitFormat1() throws Exception {
        String result = UnitUtils.format("m^-2.Pa.mol^-50");
        Assertions.assertEquals("m⁻².Pa.mol⁻⁵⁰", result);
    }

    @Test
    public void testUnitFormat2() throws Exception {
        String result = UnitUtils.format("m^-2.Pa.mol^-50.m");
        Assertions.assertEquals("m⁻¹.Pa.mol⁻⁵⁰", result);
    }


    @Test
    public void testPressureFormat() throws Exception {
        String result = Units.PASCAL.getPrettyName();
        Assertions.assertEquals("Pa", result);
    }
}
