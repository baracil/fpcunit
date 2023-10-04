package net.femtoparsec.units.generator.parsing;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.femtoparsec.units.generator.parsing.xml.XMLAbstractUnit;
import net.femtoparsec.units.generator.parsing.xml.XMLQuantity;
import net.femtoparsec.units.generator.parsing.xml.XMLUnitsInformation;

import java.util.Arrays;
import java.util.stream.Stream;

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
    checkThatAllUnitNameDefined();
    Arrays.stream(information.getQuantities())
        .forEach(XMLQuantityInitializer::initialize);
  }

  private void checkThatAllUnitNameDefined() {
    final var names = Arrays.stream(information.getQuantities())
        .flatMap(this::listAllUnit)
        .filter(u -> u.getUnitName() == null)
        .map(XMLAbstractUnit::getInstanceName)
        .toList();
    if (names.isEmpty()) {
      return;
    }
    throw new IllegalStateException("No unit defined for : " + String.join(", ", names));
  }

  private Stream<XMLAbstractUnit> listAllUnit(XMLQuantity quantity) {
    final var units = quantity.getUnits();
    if (units == null) {
      return Stream.of(quantity.getSiUnit());
    }
    return Stream.concat(
        Stream.of(quantity.getSiUnit()),
        Arrays.stream(units)
    );
  }

}
