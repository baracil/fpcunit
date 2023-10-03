package net.femtoparsec.units.core;

import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * @author Bastien Aracil
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class UnitUtils {

  private static final String MINUS_SUPERSCRIPT = "⁻";

  private static final String ZERO_SUPERSCRIPT = "⁰";

  private static final Map<Integer, String> EXPONENTS;

  private static final Map<String, String> CACHE = new HashMap<>();

  private static final String BASE_PATTERN = "(?<%s>d?°[CRF]|[Åµa-zA-Z%%‰Ω]+|°)(\\^(?<%s>-?[1-9]\\d*))?";

  private static final Pattern UNIT_MATCHER = Pattern.compile("(%s)(?<remain>(\\.%s)*)".formatted(BASE_PATTERN.formatted("unit", "power"), BASE_PATTERN.formatted("unit2", "power2")));

  static {
    EXPONENTS = Map.of(
        0, "⁰",
        1, "¹",
        2, "²",
        3, "³",
        4, "⁴",
        5, "⁵",
        6, "⁶",
        7, "⁷",
        8, "⁸",
        9, "⁹");
  }


  private static <T> List<T> filterList(List<T> source, Predicate<? super T> filter) {
    return source.stream().filter(filter).toList();
  }

  public static String format(int power) {
    if (power == 1) {
      return "";
    }
    if (power == 0) {
      return ZERO_SUPERSCRIPT;
    }

    final boolean negative = power < 0;
    power = Math.abs(power);
    if (power < 10) {
      return (negative ? MINUS_SUPERSCRIPT : "") + EXPONENTS.get(power);
    }

    int mask = 10;
    while (mask <= power) {
      mask *= 10;
    }

    StringBuilder sb = new StringBuilder();
    if (negative) {
      sb.append(MINUS_SUPERSCRIPT);
    }

    boolean first = true;
    while (mask > 0) {
      final int d = (power / mask) % 10;
      if (d != 0 || !first) {
        sb.append(EXPONENTS.get(d));
        first = false;
      }
      mask /= 10;
    }

    return sb.toString();
  }

  public static StringBuilder format(StringBuilder sb, String name, int power) {
    if (power == 0) {
      return sb;
    }
    return sb.append(name).append(format(power));
  }

  public static String format(String name, int power) {
    if (power == 0) {
      return "";
    }
    return name + format(power);
  }

  public static String format(String unitName) {
    String result = CACHE.get(unitName);
    if (result == null) {
      result = formatNoCache(unitName);
      CACHE.put(unitName, result);
    }
    return result;
  }


  public static String formatNoCache(String unitName) {
    final Map<String, Integer> powerMap = buildPowerMap(unitName);
    return powerMap.entrySet()
        .stream()
        .map(e -> format(e.getKey(), e.getValue()))
        .collect(Collectors.joining("."));
  }

  public static Map<String, Integer> buildPowerMap(String unitName) {
    final Map<String, Integer> powerMap = new LinkedHashMap<>();

    if (!unitName.isEmpty()) {
      String matching = unitName;
      do {
        final Matcher matcher = UNIT_MATCHER.matcher(matching);
        if (!matcher.matches()) {
          throw new IllegalArgumentException("Invalid unit name : " + unitName);
        }
        final String name = matcher.group("unit");
        final String powerStr = matcher.group("power");
        final int power = powerStr == null ? 1 : Integer.parseInt(powerStr);

        powerMap.merge(name, power, Integer::sum);


        final var remain = matcher.group("remain");
        matching = remain.substring(remain.isEmpty() ? 0 : 1);

      } while (!matching.isEmpty());

      //remove elements with a power of 0
      powerMap.entrySet().removeIf(power -> power.getValue().equals(0));
    }
    return powerMap;
  }

}
