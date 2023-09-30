package net.femtoparsec.units.named.test;


import net.femtoparsec.units.named.measurement.DimensionLess;
import net.femtoparsec.units.named.quantity.DimensionLessQuantity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * @author Bastien Aracil
 */
public class TestMeasurementUtils extends UnitTest {


  @Test
  public void testDimensionLess() throws Exception {
    Optional<DimensionLess> measurement = DimensionLess.safeParse("10");

    Assertions.assertTrue(measurement.isPresent());
    Assertions.assertEquals(10.0, measurement.get().getValueInSI(), 0.001);

  }

  @Test
  public void testPercentDimensionLess() throws Exception {
    Optional<DimensionLess> measurement = DimensionLess.safeParse("10 %");

    Assertions.assertTrue(measurement.isPresent());
    Assertions.assertEquals(.1, measurement.get().getValueInSI(), 0.001);

  }

  @Test
  public void testPercentDimensionLessWithoutSpace() throws Exception {
    Optional<DimensionLess> measurement = DimensionLessQuantity.INSTANCE.safeParseMeasurement("10%");

    Assertions.assertTrue(measurement.isPresent());
    Assertions.assertEquals(.1, measurement.get().getValueInSI(), 0.001);
  }
}
