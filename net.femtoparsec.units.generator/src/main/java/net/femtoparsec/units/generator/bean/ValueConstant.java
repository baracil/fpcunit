package net.femtoparsec.units.generator.bean;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

/**
 * @author Bastien Aracil
 */
@Value
@Builder(builderClassName = "Builder")
public class ValueConstant implements Constant {

    @NonNull String name;
    @NonNull Unit unit;
    @NonNull String value;

    @Override
    public boolean isValue() {
        return true;
    }

    @Override
    public boolean isExpression() {
        return false;
    }
}
