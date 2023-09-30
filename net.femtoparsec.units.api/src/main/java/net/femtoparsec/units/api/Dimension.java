package net.femtoparsec.units.api;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.function.IntUnaryOperator;

/**
 * @author Bastien Aracil
 */
@EqualsAndHashCode(of = "encoded")
public final class Dimension {

  @Getter(AccessLevel.PACKAGE)
  private final int[] powers;
  @Getter
  private final String encoded;

  public Dimension(String encoded) {
    this(DimensionSupport.decode(encoded));
  }

  Dimension(int[] powers) {
    this.powers = powers;
    this.encoded = DimensionSupport.encode(this.powers);
  }


  public int getPower(BasedUnitCode unitCode) {
    return this.powers[unitCode.getIndex()];
  }

  public Dimension scale(int factor) {
    return operateOnPowers(idx -> this.powers[idx]*factor);
  }

  public Dimension add(Dimension other) {
    return operateOnPowers(idx -> this.powers[idx]+other.powers[idx]);
  }

  public Dimension subtract(Dimension other) {
    return operateOnPowers(idx -> this.powers[idx]-other.powers[idx]);
  }

  private Dimension operateOnPowers(IntUnaryOperator operator) {
    final int[] powers = this.powers.clone();
    for (int i = 0; i < powers.length; i++) {
      powers[i] = operator.applyAsInt(i);
    }
    return new Dimension(powers);
  }

  public Dimension negate() {
    return scale(-1);
  }

  @Override
  public String toString() {
    return "Dimension{" + encoded + "}";
  }




//  /**
//   * Decode a dimension from its string representation using base S.I. units see {@link BasedUnitCode} for the list of available base units.
//   * @param encodedDimension the encode dimension like "kg.m^2.s^-3.A^-1"
//   * @return the dimension for the provided encoded value
//   */
//  public static Dimension decode(String encodedDimension) {
//    final int[] powers = new int[BASED_UNIT_CODES.size()];
//    Arrays.fill(powers, 0);
//
//    if (!encodedDimension.isEmpty()) {
//      final String[] tokens = encodedDimension.split("\\.");
//      for (String token : tokens) {
//        final Matcher matcher = UNIT_PATTERN.matcher(token);
//        if (!matcher.matches()) {
//          throw new IllegalArgumentException("Invalid dimension code ! Cannot parse token : " + token);
//        }
//        final var unitCode = CODE_BY_NAME.get(matcher.group(1));
//        final var strPower = matcher.group(3);
//        final int power = strPower == null ? 1 :Integer.parseInt(strPower);
//
//        powers[unitCode.getIndex()] += power;
//      }
//    }
//    return new Dimension(powers);
//  }
//
//  private static String encode(int[] powers) {
//    final StringBuilder sb = new StringBuilder();
//    String sep = "";
//
//    for (int i = 0; i < powers.length; i++) {
//      final var power = powers[i];
//      if (power == 0) {
//        continue;
//      }
//      final var unit = BASED_UNIT_CODES.get(i);
//      sb.append(sep);
//      if (power == 1) {
//        sb.append(unit);
//      } else {
//        sb.append(String.format("%s^%d", unit, powers[i]));
//      }
//      sep = ".";
//    }
//    return sb.toString();
//  }
//
//
//  public static Dimension add(Dimensioned[] dimensioneds) {
//    final int size = BASED_UNIT_CODES.size();
//    final int[] powers = new int[size];
//    Arrays.fill(powers, 0);
//
//    for (Dimensioned dimensioned : dimensioneds) {
//      int[] otherPowers = dimensioned.getDimension().powers;
//      for (int i = 0; i < size; i++) {
//        powers[i] += otherPowers[i];
//      }
//    }
//    return new Dimension(powers);
//  }

  public static Dimension sum(Dimensioned[] dimensioneds) {
    return DimensionSupport.sum(dimensioneds);
  }

}
