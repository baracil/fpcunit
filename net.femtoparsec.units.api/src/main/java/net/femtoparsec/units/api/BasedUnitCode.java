package net.femtoparsec.units.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BasedUnitCode {
  MASS("M",0),
  LENGTH("L",1),
  TIME("T",2),
  AMPERE("I",3),
  TEMPERATURE("K",4),
  DELTA_TEMPERATURE("dK",5),
  MOLE("N",6),
  CANDELA("J",7),
  ANGLE("°",8),
  ;

  private final String code;
  private final int index;
}
