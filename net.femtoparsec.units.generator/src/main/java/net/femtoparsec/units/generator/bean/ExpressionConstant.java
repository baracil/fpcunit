package net.femtoparsec.units.generator.bean;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

/**
 * @author Bastien Aracil
 */
@Value
@Builder(builderClassName = "Builder")
public class ExpressionConstant implements Constant {

    @NonNull String name;
    @NonNull String expression;

    @Override
    public boolean isValue() {
        return false;
    }

    @Override
    public boolean isExpression() {
        return true;
    }
}
