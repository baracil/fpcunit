package net.femtoparsec.units.named.test;

import net.femtoparsec.units.named.Units;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Bastien Aracil
 */
public class TestPercent extends UnitTest {

    @Test
    public void testPercent() throws Exception {
        final var v1 = Units.PERCENT_DIMENSION_LESS.create(100);

        final var v2 = v1.convert(Units.DIMENSION_LESS);

        Assertions.assertEquals(v2.getValue(), 1, 0.001);

    }
}
