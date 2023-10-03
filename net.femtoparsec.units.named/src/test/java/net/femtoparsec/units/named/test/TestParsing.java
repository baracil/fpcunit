package net.femtoparsec.units.named.test;

import net.femtoparsec.units.named.measurement.Length;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * @author Bastien Aracil
 */
public class TestParsing extends UnitTest {

  public static Stream<Arguments> samples() {
    return Stream.of(
        Arguments.of("0.88 µm", Length.MICROMETER.create(0.88)),
        Arguments.of(".88 µm", Length.MICROMETER.create(0.88)),
        Arguments.of("12.07 µm", Length.MICROMETER.create(12.07)),
        Arguments.of("12.07 mm", Length.MILLIMETER.create(12.07)),
        Arguments.of("-12.07 mm", Length.MILLIMETER.create(-12.07)),
        Arguments.of("0.88  µm", Length.MICROMETER.create(0.88)),
        Arguments.of(".88  µm", Length.MICROMETER.create(0.88)),
        Arguments.of("12.07  µm", Length.MICROMETER.create(12.07)),
        Arguments.of("12.07  mm", Length.MILLIMETER.create(12.07)),
        Arguments.of("-12.07  mm", Length.MILLIMETER.create(-12.07)),
        Arguments.of("0.88µm", Length.MICROMETER.create(0.88)),
        Arguments.of(".88µm", Length.MICROMETER.create(0.88)),
        Arguments.of("12.07µm", Length.MICROMETER.create(12.07)),
        Arguments.of("12.07mm", Length.MILLIMETER.create(12.07)),
        Arguments.of("-12.07mm", Length.MILLIMETER.create(-12.07)));

}

  @ParameterizedTest
  @MethodSource("samples")
  public void shouldParseCorrectly(String valueOf, Length expected) throws Exception {
    final Length length = Length.parse(valueOf);

    Assertions.assertEquals(length.getValue(), expected.getValueInUnit(length.getUnit()), 1e-6);
  }

}
