package net.femtoparsec.units.generator.bean;

import lombok.NonNull;
import lombok.Value;

/**
 * @author Bastien Aracil
 */
@Value
public class Multiply implements Operation {

    @NonNull Quantity reference;
    @NonNull Quantity factor;
    @NonNull Quantity result;

    @Override
    public String toString() {
        return String.format("%s = %s * %s",result.getName(), reference.getName(), factor.getName());
    }

    @Override
    public String getMethodName() {
        return "Multiply";
    }

    @Override
    public Quantity getOther() {
        return factor;
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
