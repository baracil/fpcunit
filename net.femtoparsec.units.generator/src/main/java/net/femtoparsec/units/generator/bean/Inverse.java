package net.femtoparsec.units.generator.bean;

import lombok.NonNull;
import lombok.Value;

/**
 * @author Bastien Aracil
 */
@Value
public class Inverse implements Operation {

    @NonNull Quantity reference;
    @NonNull Quantity result;

    @Override
    public String toString() {
        return String.format("%s = 1/%s",result.getName(), reference.getName());
    }

    @Override
    public String getMethodName() {
        return "Inverse";
    }

    @Override
    public Quantity getOther() {
        return null;
    }

    @Override
    public boolean isNonStandard() {
        return false;
    }

    @Override
    public <T> T accept(OperationVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
