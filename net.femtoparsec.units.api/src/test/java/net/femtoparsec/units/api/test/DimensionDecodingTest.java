package net.femtoparsec.units.api.test;

import net.femtoparsec.units.api.BasedUnitCode;
import net.femtoparsec.units.api.Dimension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static net.femtoparsec.units.api.BasedUnitCode.*;

public class DimensionDecodingTest {

  public static Stream<Arguments> samples() {
    return Stream.of(
        Arguments.of("kg^2.m^-1", MASS, 2),
        Arguments.of("kg^2.m^-1", LENGTH, -1),
        Arguments.of("kg^2.m^-1", TIME, 0),
        Arguments.of("kg^2.m^-2.kg^2.m^-1", MASS, 4),
        Arguments.of("kg^2.m^-2.kg^2.m^-1", LENGTH, -3),
        Arguments.of("m.s^-2", LENGTH, 1),
        Arguments.of("m.s^-2", TIME, -2),
        Arguments.of("A^0.cd", CANDELA, 1),
        Arguments.of("A^0.cd", AMPERE, 0)
    );
  }

  @ParameterizedTest
  @MethodSource("samples")
  public void shouldHaveRightFactor(String encodedDimension, BasedUnitCode unit, int expectedFactor) {
    final var dimension = new Dimension(encodedDimension);
    final var actualFactor = dimension.getPower(unit);
    Assertions.assertEquals(expectedFactor, actualFactor);
  }

}
