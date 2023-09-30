package net.femtoparsec.units.core;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author bastien.a
 */
@RequiredArgsConstructor
public class MeasurementParser {

  public static Optional<ParsedMeasurement> parse(@NonNull String measurementAsString) {
    return new MeasurementParser(measurementAsString).parse();
  }

  private static final Pattern PATTERN = Pattern.compile("([-+]?Infinity|NaN|[-+]?[0-9]+(\\.[0-9]*)?([eE][-+]?[0-9]+)?) *(.*)");

  @NonNull
  private final String measurementAsString;

  public Optional<ParsedMeasurement> parse() {
    final Matcher matcher;
    if (measurementAsString.startsWith(".")) {
      matcher = PATTERN.matcher("0" + measurementAsString);
    } else {
      matcher = PATTERN.matcher(measurementAsString);
    }

    if (!matcher.matches()) {
      return Optional.empty();
    }

    final String valueStr = matcher.group(1);
    final String unitName = Optional.ofNullable(matcher.group(4)).orElse("");

    return Optional.of(new ParsedMeasurement(Double.parseDouble(valueStr), unitName));
  }
}
