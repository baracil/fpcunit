package net.femtoparsec.units.generator.parsing.converter;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.femtoparsec.units.generator.bean.Method;
import net.femtoparsec.units.generator.bean.Operation;
import net.femtoparsec.units.generator.bean.Quantity;
import net.femtoparsec.units.generator.parsing.xml.XMLUnitsInformation;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Bastien Aracil
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class InformationConverter {

  public static List<Quantity> convert(XMLUnitsInformation information) {
    return new InformationConverter(information).convert();
  }

  @NonNull
  private final XMLUnitsInformation information;

  private List<Quantity> quantities;
  private Map<String, Quantity> quantityByName;
  private Map<Quantity, List<Operation>> operations;
  private Map<Quantity, List<Method>> methods;

  public List<Quantity> convert() {
    this.convertQuantities();
    this.initializeQuantityByNameMap();
    this.convertOperations();
    this.convertMethods();
    this.assignOperations();
    this.assignMethods();

    return quantities;
  }


  private void convertQuantities() {
    this.quantities = Arrays.stream(information.getQuantities())
        .map(QuantityConverter::convert)
        .toList();
  }

  private void initializeQuantityByNameMap() {
    this.quantityByName = this.quantities.stream().collect(Collectors.toMap(Quantity::getName, Function.identity()));
  }

  private void convertOperations() {
    final var converter = new OperationConverter(quantityByName);
    this.operations = Arrays.stream(information.getOperations())
        .map(converter::convert)
        .flatMap(Collection::stream)
        .collect(Collectors.groupingBy(Operation::getReference));
  }

  private void convertMethods() {
    final var converter = new MethodConverter(quantityByName);

    this.methods = Arrays.stream(information.getMethods())
        .map(converter::convert)
        .collect(Collectors.groupingBy(Method::getQuantity));

  }


  private void assignOperations() {
    this.operations.forEach(Quantity::setOperations);
  }

  private void assignMethods() {
    this.methods.forEach(Quantity::setMethods);
  }

}
