package net.femtoparsec.units.generator.bean;

import lombok.NonNull;
import lombok.Value;

/**
 * @author Bastien Aracil
 */
@Value
public class Cubic implements Operation {

    @NonNull Quantity reference;
    @NonNull Quantity result;

    @Override
    public String getMethodName() {
        return "Cubic";
    }

    @Override
    public Quantity getOther() {
        return null;
    }

    @Override
    public String toString() {
        return String.format("%s = %s^3",result.getName(), reference.getName());
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
