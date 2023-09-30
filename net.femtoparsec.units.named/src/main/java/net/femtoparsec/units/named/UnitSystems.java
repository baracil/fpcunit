package net.femtoparsec.units.named;

import net.femtoparsec.units.api.UnitSystem;

/**
 * @author Bastien Aracil
 */
public class UnitSystems {

  public static final UnitSystem SI;
  public static final UnitSystem SI_FACTOR;

  static {
    SI = UnitSystem.builder()
        .addUnit(Units.SECOND)
        .addUnit(Units.METER)
        .addUnit(Units.METER_PER_SECOND)
        .addUnit(Units.METER_PER_SQUARE_SECOND)
        .addUnit(Units.PASCAL)
        .addUnit(Units.CUBIC_METER_PER_SECOND)
        .addUnit(Units.PASCAL_SECOND)
        .addUnit(Units.KILOGRAM)
        .addUnit(Units.HERTZ)
        .addUnit(Units.SHEAR_RATE)
        .addUnit(Units.SQUARE_METER)
        .addUnit(Units.PASCAL_PER_METER)
        .addUnit(Units.RECIPROCAL_QUARTIC_METER)
        .addUnit(Units.QUARTIC_METER)
        .addUnit(Units.JOULE)
        .addUnit(Units.RECIPROCAL_CUBIC_METER)
        .addUnit(Units.PASCAL_SECOND_PER_CUBIC_METER)
        .addUnit(Units.DIMENSION_LESS)
        .addUnit(Units.KELVIN)
        .addUnit(Units.DELTA_KELVIN)
        .build();

    SI_FACTOR = UnitSystem.builder()
        .addSystem(SI)
//        .addUnit(Units.MILLIPASCAL_SECOND)
//        .addUnit(Units.PERCENT_DIMENSION_LESS)
        .build();


  }

  private UnitSystems() {
  }
}
