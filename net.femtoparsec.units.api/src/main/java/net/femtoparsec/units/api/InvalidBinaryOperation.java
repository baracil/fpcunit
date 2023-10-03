package net.femtoparsec.units.api;

import lombok.Getter;

@Getter
public class InvalidBinaryOperation extends FpcUnitException {

  private final Dimension operand1;
  private final Dimension operand2;
  private final String operation;
  private final Dimension result;

  public InvalidBinaryOperation(Dimension operand1, Dimension operand2, String operation, Dimension result) {
    super("Incompatible dimension : (" + operand1.getEncoded() + " "+operation + " "+operand2.getEncoded()+") <> " + result.getEncoded());
    this.operand1 = operand1;
    this.operand2 = operand2;
    this.operation = operation;
    this.result = result;
  }
}
