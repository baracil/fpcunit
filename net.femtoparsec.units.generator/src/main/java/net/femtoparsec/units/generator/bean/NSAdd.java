package net.femtoparsec.units.generator.bean;

import lombok.NonNull;
import lombok.Value;

/**
 * @author Bastien Aracil
 */
@Value
public class NSAdd implements Operation {

    @NonNull Quantity reference;
    @NonNull Quantity offset;

    @Override
    public String getMethodName() {
        return "Add";
    }

    @Override
    public Quantity getOther() {
        return offset;
    }

    @Override
    public @NonNull Quantity getResult() {
        return reference;
    }

    @Override
    public String toString() {
        return String.format("%s = %s+%s",reference.getName(), reference.getName(), offset.getName());
    }
    @Override
    public boolean isNonStandard() {
        return true;
    }

    public String getOperator() {
        return "+";
    }

    @Override
    public <T> T accept(OperationVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
