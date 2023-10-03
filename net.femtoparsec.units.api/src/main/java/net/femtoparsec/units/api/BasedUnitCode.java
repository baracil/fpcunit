package net.femtoparsec.units.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BasedUnitCode {
  MASS("kg",0),
  LENGTH("m",1),
  TIME("s",2),
  AMPERE("A",3),
  TEMPERATURE("K",4),
  DELTA_TEMPERATURE("dK",5),
  MOLE("mol",6),
  CANDELA("cd",7),
  ANGLE("rad",8),
  ;

  private final String code;
  private final int index;
}
