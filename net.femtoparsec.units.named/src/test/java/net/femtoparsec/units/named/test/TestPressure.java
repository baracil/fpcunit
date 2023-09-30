package net.femtoparsec.units.named.test;

import net.femtoparsec.units.named.measurement.Pressure;
import net.femtoparsec.units.named.quantity.PressureQuantity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Bastien Aracil
 */
public class TestPressure {

  @Test
  public void testFindBar() throws Exception {
    final var bar = PressureQuantity.INSTANCE.findUnit(Pressure.BAR.getName());
    Assertions.assertTrue(bar.isPresent());

  }
}
