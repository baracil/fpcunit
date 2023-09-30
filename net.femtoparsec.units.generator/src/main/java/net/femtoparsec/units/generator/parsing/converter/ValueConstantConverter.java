package net.femtoparsec.units.generator.parsing.converter;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import net.femtoparsec.units.generator.bean.Unit;
import net.femtoparsec.units.generator.bean.ValueConstant;
import net.femtoparsec.units.generator.parsing.xml.XMLValueConstant;

import java.util.Map;

/**
 * @author Bastien Aracil
 */
@RequiredArgsConstructor
public class ValueConstantConverter {

  private final Map<String, Unit> unitByInstanceName;

  public ValueConstant convert(XMLValueConstant constant) {
    return new Execution(constant).convert();
  }


  @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
  private class Execution {

    private final XMLValueConstant constant;

    private Unit unit;
    public ValueConstant.Builder constantBuilder;

    private ValueConstant convert() {
      this.findUnit();
      this.checkUnit();
      this.initializeBuilder();
      this.setupBuilder();

      return constantBuilder.build();
    }

    private void findUnit() {
      this.unit = unitByInstanceName.get(constant.getUnitName());
    }

    private void checkUnit() {
      if (this.unit == null) {
        throw new IllegalArgumentException("Unknown unit with name : " + constant.getUnitName());
      }
    }

    private void initializeBuilder() {
      this.constantBuilder = ValueConstant.builder();
    }

    private void setupBuilder() {
      this.constantBuilder
          .name(constant.getName())
          .unit(unit)
          .value(constant.getValue());
    }
  }

}
