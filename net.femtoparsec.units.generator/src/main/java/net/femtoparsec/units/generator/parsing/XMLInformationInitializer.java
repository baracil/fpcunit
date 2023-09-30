package net.femtoparsec.units.generator.parsing;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.femtoparsec.units.generator.parsing.xml.XMLUnitsInformation;

import java.util.Arrays;

/**
 * @author Bastien Aracil
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class XMLInformationInitializer {

  public static void initialize(XMLUnitsInformation information) {
    new XMLInformationInitializer(information).initialize();
  }

  @NonNull
  private final XMLUnitsInformation information;

  public void initialize() {
    Arrays.stream(information.getQuantities())
        .forEach(XMLQuantityInitializer::initialize);
  }

}
