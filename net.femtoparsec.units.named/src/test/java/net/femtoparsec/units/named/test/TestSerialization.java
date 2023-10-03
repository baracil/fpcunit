package net.femtoparsec.units.named.test;

import net.femtoparsec.units.api.Measurement;
import net.femtoparsec.units.api.Quantity;
import net.femtoparsec.units.api.Unit;
import net.femtoparsec.units.named.Quantities;
import net.femtoparsec.units.named.Units;
import net.femtoparsec.units.named.measurement.Length;
import net.femtoparsec.units.named.measurement.Temperature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * @author Bastien Aracil
 */
public class TestSerialization extends AbstractTestSerialization {

  public static Stream<Quantity> quantities() {
    return Quantities.apply(q -> q).stream();
  }

  public static Stream<Unit<?>> units() {
    return Stream.of(Units.MILLISECOND, Units.ANGSTROM);
  }

  public static Stream<Measurement<?>> measurements() {
    return Stream.of(
        Length.ZERO,
        Length.MICROMETER.create(12),
        Temperature.ABSOLUTE_ZERO
    );
  }

  @ParameterizedTest
  @MethodSource("measurements")
  public void testMeasurementSerialization(Measurement<?> m) throws Exception {
    final var ms = testSerialization(m);
    Assertions.assertEquals(ms.getUnit(), m.getUnit());
    final var other = m.getUnit().create(ms.getValue());
    Assertions.assertEquals(other, m);
  }


  @ParameterizedTest
  @MethodSource("units")
  public void testUnitsSerialization(Unit<?> unit) throws Exception {
    final var us = testSerialization(unit);
    Assertions.assertSame(unit, us);
  }

  @ParameterizedTest
  @MethodSource("quantities")
  public void testQuantitiesSerialization(Quantity quantity) throws Exception {
    final var sq = testSerialization(quantity);
    Assertions.assertSame(quantity, sq);
  }

}
