package net.femtoparsec.units.api;

import lombok.Getter;

@Getter
public class InvalidUnaryOperation extends FpcUnitException {

  private final Dimension operand;
  private final String operation;
  private final Dimension result;

  public InvalidUnaryOperation(Dimension operand, String operation, Dimension result) {
    super("Incompatible dimension : (" + operand.getEncoded() + ")"+operation+" <> " + result.getEncoded());
    this.operand = operand;
    this.operation = operation;
    this.result = result;
  }
}
