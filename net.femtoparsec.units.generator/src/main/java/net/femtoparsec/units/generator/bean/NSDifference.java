package net.femtoparsec.units.generator.bean;

import lombok.NonNull;
import lombok.Value;

/**
 * @author Bastien Aracil
 */
@Value
public class NSDifference implements Operation {

    @NonNull Quantity reference;
    @NonNull Quantity result;

    @Override
    public String toString() {
        return String.format("%s = %s - %s",result.getName(), reference.getName(), reference.getName());
    }

    @Override
    public String getMethodName() {
        return "Subtract";
    }

    @Override
    public boolean isNonStandard() {
        return true;
    }

    public String getOperator() {
        return "-";
    }

    @Override
    public Quantity getOther() {
        return reference;
    }

    @Override
    public <T> T accept(OperationVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
