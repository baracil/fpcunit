package net.femtoparsec.units.generator.bean;

import java.util.function.Function;

/**
 * @author Bastien Aracil
 */
public interface OperationVisitor<T> {

    default Function<Operation,T> asFunction() {
        return o -> o.accept(this);
    }

    T visit(Inverse inverse);

    T visit(Square square);

    T visit(Cubic cubic);

    T visit(SquareRoot squareRoot);

    T visit(NSAdd nsAdd);

    T visit(Multiply multiply);

    T visit(Divide divide);

    T visit(NSDifference nsDifference);
}
