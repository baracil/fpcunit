package net.femtoparsec.units.generator.named;

import net.femtoparsec.units.api.Dimension;
import net.femtoparsec.units.generator.bean.*;

/**
 * @author Bastien Aracil
 */
public class OperationValidator implements OperationVisitor<Void> {

    @Override
    public Void visit(Inverse inverse) {
        final Dimension result = inverse.getReference().getDimension().negate();
        if (!result.equals(inverse.getResult().getDimension())) {
            throw new IllegalArgumentException("Invalid operation (dimensions do not match) : "+inverse);
        }
        return null;
    }

    @Override
    public Void visit(Square square) {
        final Dimension result = square.getReference().getDimension().scale(2);
        if (!result.equals(square.getResult().getDimension())) {
            throw new IllegalArgumentException("Invalid operation (dimensions do not match) : "+square);
        }
        return null;
    }

    @Override
    public Void visit(Cubic cubic) {
        final Dimension result = cubic.getReference().getDimension().scale(3);
        if (!result.equals(cubic.getResult().getDimension())) {
            throw new IllegalArgumentException("Invalid operation (dimensions do not match) : "+cubic);
        }
        return null;
    }

    @Override
    public Void visit(SquareRoot squareRoot) {
        final Dimension result = squareRoot.getResult().getDimension().scale(2);
        if (!result.equals(squareRoot.getReference().getDimension())) {
            throw new IllegalArgumentException("Invalid operation (dimensions do not match) : "+squareRoot);
        }
        return null;
    }

    @Override
    public Void visit(NSAdd nsAdd) {
        return null;
    }

    @Override
    public Void visit(Multiply multiply) {
        final Dimension result = multiply.getReference().getDimension().add(multiply.getOther().getDimension());
        if (!result.equals(multiply.getResult().getDimension())) {
            throw new IllegalArgumentException("Invalid operation (dimensions do not match) : "+multiply);
        }
        return null;
    }

    @Override
    public Void visit(Divide divide) {
        final Dimension result = divide.getReference().getDimension().add(divide.getOther().getDimension().negate());
        if (!result.equals(divide.getResult().getDimension())) {
            throw new IllegalArgumentException("Invalid operation (dimensions do not match) : "+divide);
        }
        return null;
    }

    @Override
    public Void visit(NSDifference nsDifference) {
        return null;
    }
}
