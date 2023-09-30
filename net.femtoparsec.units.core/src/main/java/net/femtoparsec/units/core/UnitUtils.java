package net.femtoparsec.units.core;

import net.femtoparsec.units.api.Measurement;
import net.femtoparsec.units.api.Quantity;
import net.femtoparsec.units.api.Unit;
import net.femtoparsec.units.api.UnitSystem;

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

  public static UnitSystem DEFAULT_UNIT_SYSTEM = null;

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

  /**
   * Try to adapt the unit (amongst the known units) of the measurement so that its absolute value is between 1 (inclusive) and 1000 (exclusive)
   *
   * @param measurement the measurement to adapt
   * @param <M>         the measurement type
   * @param <U>         the unit type of the measurement
   * @return the measurement in tha adapted unit
   */
  public static <Q extends Quantity<Q, U, M>, U extends Unit<Q, U, M>, M extends Measurement<Q, U, M>> M adaptUnit(M measurement) {
    return adaptUnit(measurement, (U) null);
  }

  /**
   * Same as {@link #adaptUnit(Measurement)} but only uses the unit in the provided unitSystem
   *
   * @param measurement the measurement to adapt
   * @param unitSystem  the unit system to search for the unit to use to adapt the measurement
   * @param <M>         the measurement type
   * @param <U>         the unit type of the measurement
   * @return the measurement in tha adapted unit
   */
  public static <Q extends Quantity<Q, U, M>, U extends Unit<Q, U, M>, M extends Measurement<Q, U, M>> M adaptUnit(M measurement, UnitSystem unitSystem) {
    return adaptUnit(measurement, null, unitSystem.systemPredicate());
  }

  /**
   *
   */
  public static <Q extends Quantity<Q, U, M>, U extends Unit<Q, U, M>, M extends Measurement<Q, U, M>> M adaptUnit(M measurement, Predicate<? super U> unitSelector) {
    return adaptUnit(measurement, null, unitSelector);
  }

  /**
   * Try to adapt the unit (amongst the known units) of the measurement so that its absolute value is between 1 (inclusive) and 1000 (exclusive)
   *
   * @param measurement the measurement to adapt
   * @param unitForZero the unit to use if the measurement value is zero (can be null)
   * @param <U>         the unit type
   * @param <M>         the measurement type
   * @return the measurement in tha adapted unit
   */
  public static <Q extends Quantity<Q, U, M>, U extends Unit<Q, U, M>, M extends Measurement<Q, U, M>> M adaptUnit(M measurement, U unitForZero) {
    if (measurement == null) {
      return null;
    }
    return findAdaptedUnit(measurement, unitForZero).map(measurement::convert).orElse(measurement);
  }

  public static <Q extends Quantity<Q, U, M>, U extends Unit<Q, U, M>, M extends Measurement<Q, U, M>> M adaptUnit(M measurement, U unitForZero, UnitSystem unitSystem) {
    if (measurement == null) {
      return null;
    }
    return findAdaptedUnit(measurement, unitForZero, unitSystem.systemPredicate()).map(measurement::convert).orElse(measurement);
  }

  public static <Q extends Quantity<Q, U, M>, U extends Unit<Q, U, M>, M extends Measurement<Q, U, M>> M adaptUnit(M measurement, U unitForZero, Predicate<? super U> unitSelector) {
    if (measurement == null) {
      return null;
    }
    return findAdaptedUnit(measurement, unitForZero, unitSelector).map(measurement::convert).orElse(measurement);
  }


  public static <Q extends Quantity<Q, U, M>, U extends Unit<Q, U, M>, M extends Measurement<Q, U, M>> Optional<U> findAdaptedUnit(M measurement) {
    return findAdaptedUnit(measurement, (U) null);
  }

  public static <Q extends Quantity<Q, U, M>, U extends Unit<Q, U, M>, M extends Measurement<Q, U, M>> Optional<U> findAdaptedUnit(M measurement, UnitSystem unitSystem) {
    return findAdaptedUnit(measurement, null, unitSystem.systemPredicate());
  }

  public static <Q extends Quantity<Q, U, M>, U extends Unit<Q, U, M>, M extends Measurement<Q, U, M>> Optional<U> findAdaptedUnit(M measurement, Predicate<? super U> unitSelector) {
    return findAdaptedUnit(measurement, null, unitSelector);
  }

  public static <Q extends Quantity<Q, U, M>, U extends Unit<Q, U, M>, M extends Measurement<Q, U, M>> Optional<U> findAdaptedUnit(M measurement, U unitForZero) {
    return findAdaptedUnit(measurement, unitForZero, getDefaultUnitSelector());
  }

  private static Predicate<Unit<?, ?, ?>> getDefaultUnitSelector() {
    final UnitSystem unitSystem = DEFAULT_UNIT_SYSTEM;
    if (unitSystem == null) {
      return u -> true;
    } else {
      return unitSystem.systemPredicate();
    }

  }

  public static <
      Q extends Quantity<Q, U, M>,
      U extends Unit<Q, U, M>,
      M extends Measurement<Q, U, M>>
  Optional<U> findAdaptedUnit(M measurement, U unitForZero, Predicate<? super U> unitSelector) {
    if (measurement == null) {
      return Optional.empty();
    } else if (isPrettyNumber(measurement.getValue()) && unitSelector.test(measurement.getUnit())) {
      return Optional.of(measurement.getUnit());
    }

    final var quantity = measurement.getQuantity();
    final var logV = Math.log10(Math.abs(measurement.getValueInSI()));

    if (!Double.isFinite(logV) && unitForZero != null) {
      return Optional.of(unitForZero);
    }

    final double f1 = Math.pow(10, -Math.floor(logV));

    final var units = quantity.getUnitsByFactor();

    {
      final Optional<U> match = getClosestUnit(f1, filter(units, unitSelector));

      if (match.isPresent()) {
        return match;
      }
    }
    {
      final Optional<U> match = getClosestUnit(f1, units);
      if (match.isPresent()) {
        return match;
      }
    }

    return Optional.of(measurement.getUnit());
  }

  private static <U extends Unit<?, ?, ?>> Optional<U> getClosestUnit(double factor, NavigableMap<Double, ? extends List<U>> units) {
    var ceilingEntry = units.ceilingEntry(factor);

    if (ceilingEntry != null) {
      return ceilingEntry.getValue().stream().findFirst();
    }

    var floorEntry = units.floorEntry(factor);

    if (floorEntry != null) {
      return floorEntry.getValue().stream().findFirst();
    }

    return Optional.empty();
  }

  private static <K extends Comparable<?>, T> NavigableMap<K, List<T>> filter(NavigableMap<K, List<T>> map, Predicate<? super T> selector) {

    final NavigableMap<K, List<T>> result = new TreeMap<>();

    for (Map.Entry<K, List<T>> entry : map.entrySet()) {
      final List<T> list = entry.getValue().stream().filter(selector).collect(Collectors.toList());
      if (!list.isEmpty()) {
        result.put(entry.getKey(), list);
      }
    }

    return result;
  }

  private static <T> List<T> filterList(List<T> source, Predicate<? super T> filter) {
    return source.stream().filter(filter).toList();
  }

  private static boolean isPrettyNumber(double value) {
    return (value >= 1 && value < 1000) || (value <= -1 && value > -1000);
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
