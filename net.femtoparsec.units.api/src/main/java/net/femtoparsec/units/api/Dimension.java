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


  public static Dimension sum(Dimensioned[] dimensioneds) {
    return DimensionSupport.sum(dimensioneds);
  }

}
