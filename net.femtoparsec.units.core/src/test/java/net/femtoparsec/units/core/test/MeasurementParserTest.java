package net.femtoparsec.units.core.test;

import net.femtoparsec.units.core.MeasurementParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * @author bastien.a
 */
public class MeasurementParserTest {

  public static Stream<Arguments> samples() throws Exception {
    return Stream.of(
        Arguments.of("10 mm", 10.0, "mm"),
        Arguments.of("-10.3 m", -10.3, "m"),
        Arguments.of("1e2 cm", 100, "cm"),
        Arguments.of("10mm", 10.0, "mm"),
        Arguments.of("-10.3m", -10.3, "m"),
        Arguments.of("1e2cm", 100, "cm"),
        Arguments.of("1e2", 100, ""),
        Arguments.of("NaN °C", Double.NaN, "°C"),
        Arguments.of("Infinity °C", Double.POSITIVE_INFINITY, "°C"),
        Arguments.of("-Infinity °C", Double.NEGATIVE_INFINITY, "°C")
    );
  }

  @ParameterizedTest
  @MethodSource("samples")
  public void shouldHaveRightValue(String measurementAsString, double value, String unit) throws Exception {
    final var parsedMeasurement = MeasurementParser.parse(measurementAsString).orElse(null);
    Assertions.assertNotNull(parsedMeasurement);

    if (Double.isNaN(value)) {
      Assertions.assertTrue(Double.isNaN(parsedMeasurement.value()));
    } else {
      Assertions.assertEquals(parsedMeasurement.value(), value, 1e-6);
    }

  }

  @ParameterizedTest
  @MethodSource("samples")
  public void shouldHaveRightUnit(String measurementAsString, double value, String unit) throws Exception {
    final var parsedMeasurement = MeasurementParser.parse(measurementAsString).orElse(null);
    Assertions.assertNotNull(parsedMeasurement);
    Assertions.assertEquals(parsedMeasurement.unit(), unit);
  }
}
