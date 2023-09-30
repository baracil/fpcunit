package net.femtoparsec.units.generator.bean;

import lombok.NonNull;
import lombok.Value;

/**
 * @author Bastien Aracil
 */
@Value
public class Divide implements Operation {

    @NonNull Quantity reference;
    @NonNull Quantity divisor;
    @NonNull Quantity result;

    @Override
    public String toString() {
        return String.format("%s = %s / %s",result.getName(), reference.getName(), divisor.getName());
    }

    @Override
    public String getMethodName() {
        return "Divide";
    }

    @Override
    public boolean isNonStandard() {
        return false;
    }

    @Override
    public Quantity getOther() {
        return divisor;
    }

    @Override
    public <T> T accept(OperationVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
