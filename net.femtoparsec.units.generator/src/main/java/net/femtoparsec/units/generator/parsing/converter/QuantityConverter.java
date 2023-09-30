package net.femtoparsec.units.generator.parsing.converter;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.femtoparsec.units.generator.parsing.xml.XMLConstantVisitor;
import net.femtoparsec.units.generator.parsing.xml.XMLExpressionConstant;
import net.femtoparsec.units.generator.parsing.xml.XMLValueConstant;
import net.femtoparsec.units.api.Dimension;
import net.femtoparsec.units.generator.bean.Constant;
import net.femtoparsec.units.generator.bean.Quantity;
import net.femtoparsec.units.generator.bean.Unit;
import net.femtoparsec.units.generator.parsing.xml.XMLQuantity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Bastien Aracil
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class QuantityConverter {

  public static Quantity convert(XMLQuantity quantity) {
    return new QuantityConverter(quantity).convert();
  }

  private final XMLQuantity quantity;

  private Dimension dimension;
  private Unit siUnit;
  private List<Unit> units;
  private List<Constant> constants;
  private Map<String, Unit> unitByInstanceName;

  private Quantity.Builder quantityBuilder;

  public Quantity convert() {
    this.convertDimension();
    this.initializeSIUnit();
    this.initializeUnits();
    this.initializeUnitByNameMap();
    this.initializeConstants();

    this.initializeBuilder();
    this.copyValuesInBuilder();

    return quantityBuilder.build();
  }

  private void convertDimension() {
    this.dimension = new Dimension(quantity.getDimension());
  }

  private void initializeSIUnit() {
    this.siUnit = UnitConverter.convert(quantity.getSiUnit());
  }


  private void initializeBuilder() {
    this.quantityBuilder = Quantity.builder();
  }

  private void initializeUnits() {
    this.units = Arrays.stream(quantity.getUnits())
        .map(UnitConverter::convert)
        .toList();
  }


  private void copyValuesInBuilder() {
    this.quantityBuilder
        .name(quantity.getName())
        .quantityName(quantity.getQuantityName())
        .unitName(quantity.getUnitName())
        .measurementName(quantity.getMeasurementName())
        .standard(quantity.getStandard())
        .dimension(dimension)
        .siUnit(siUnit)
        .units(units)
        .constants(constants)
        .methods(List.of())
        .operations(List.of());

  }


  private void initializeUnitByNameMap() {
    this.unitByInstanceName = Stream.concat(
        Stream.of(siUnit),
        units.stream()
    ).collect(Collectors.toMap(Unit::getInstanceName, Function.identity()));
  }


  private void initializeConstants() {
    final var converter = new ConstantConverter(new ValueConstantConverter(unitByInstanceName));

    this.constants = Arrays.stream(quantity.getConstants())
        .map(c -> c.accept(converter))
        .toList();
  }

  @RequiredArgsConstructor
  private static class ConstantConverter implements XMLConstantVisitor<Constant> {

    @NonNull
    private final ValueConstantConverter valueConstantConverter;
    private final ExpressionConstantConverter expressionConstantConverter = new ExpressionConstantConverter();

    @Override
    public Constant visit(XMLValueConstant constant) {
      return valueConstantConverter.convert(constant);
    }

    @Override
    public Constant visit(XMLExpressionConstant constant) {
      return expressionConstantConverter.convert(constant);
    }
  }
}
