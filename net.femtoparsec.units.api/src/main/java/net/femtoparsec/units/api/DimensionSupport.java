package net.femtoparsec.units.api;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class DimensionSupport {


  public static String encode(int[] powers) {
    return BASED_UNIT_CODES.stream()
        .filter(unit -> powers[unit.getIndex()] != 0)
        .map(unit -> {
          final var power = powers[unit.getIndex()];
          return power == 1 ? unit.getCode() : "%s^%d".formatted(unit.getCode(), power);
        }).collect(Collectors.joining("."));
  }

  public static int[] decode(String encodedDimension) {
    final int[] powers = new int[BASED_UNIT_CODES.size()];
    Arrays.fill(powers, 0);

    if (!encodedDimension.isEmpty()) {
      final String[] tokens = encodedDimension.split("\\.");
      for (String token : tokens) {
        final Matcher matcher = UNIT_PATTERN.matcher(token);
        if (!matcher.matches()) {
          throw new IllegalArgumentException("Invalid dimension code ! Cannot parse token : " + token);
        }
        final var unitCode = CODE_BY_NAME.get(matcher.group(1));
        final var strPower = matcher.group(3);
        final int power = strPower == null ? 1 : Integer.parseInt(strPower);

        powers[unitCode.getIndex()] += power;
      }
    }
    return powers;
  }

  public static Dimension sum(Dimensioned[] dimensioneds) {
    final var result = new int[BASED_UNIT_CODES.size()];
    Arrays.fill(result,0);


    for (Dimensioned dimensioned : dimensioneds) {
      var powers = dimensioned.getDimension().getPowers();
      for (int j = 0; j < result.length; j++) {
        result[j] += powers[j];
      }
    }
    return new Dimension(result);
  }

  private static final List<BasedUnitCode> BASED_UNIT_CODES;
  private static final Map<String, BasedUnitCode> CODE_BY_NAME;
  private static final Pattern UNIT_PATTERN;

  static {
    {
      BASED_UNIT_CODES = Arrays.stream(BasedUnitCode.values())
          .sorted(Comparator.comparingInt(BasedUnitCode::getIndex))
          .toList();

      CODE_BY_NAME = BASED_UNIT_CODES.stream().collect(Collectors.toMap(BasedUnitCode::getCode, Function.identity()));

      final String pattern = BASED_UNIT_CODES.stream()
          .map(BasedUnitCode::getCode)
          .collect(Collectors.joining("|", "(", ")(\\^(-?[0-9]+))?"));

      UNIT_PATTERN = Pattern.compile(pattern);
    }
  }

}
