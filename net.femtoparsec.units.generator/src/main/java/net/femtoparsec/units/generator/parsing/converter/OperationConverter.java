package net.femtoparsec.units.generator.parsing.converter;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.femtoparsec.units.generator.bean.*;
import net.femtoparsec.units.generator.parsing.xml.*;

import java.util.List;
import java.util.Map;

/**
 * @author Bastien Aracil
 */
@RequiredArgsConstructor
public class OperationConverter {

    @NonNull
    private final Map<String, Quantity> quantityByName;
    private final OperationVisitor operationVisitor = new OperationVisitor();

    public List<Operation> convert(XMLOperation xmlOperation) {
        return xmlOperation.accept(operationVisitor);
    }

    @NonNull
    private Quantity getQuantity(String name) {
        final Quantity quantity = quantityByName.get(name);
        if (quantity == null) {
            throw new IllegalArgumentException("Unknown quantity name : "+name);
        }
        return quantity;
    }

    private class OperationVisitor implements XMLOperationVisitor<List<Operation>> {


        @Override
        public List<Operation> visit(XMLCubic xmlCubic) {
            final Quantity reference = getQuantity(xmlCubic.getQuantity());
            final Quantity result = getQuantity(xmlCubic.getResult());
            return List.of(new Cubic(reference, result));
        }

        @Override
        public List<Operation> visit(XMLSquare xmlSquare) {
            final Quantity reference = getQuantity(xmlSquare.getQuantity());
            final Quantity result = getQuantity(xmlSquare.getResult());
            return List.of(
                    new Square(reference, result),
                    new SquareRoot(result, reference));
        }

        @Override
        public List<Operation> visit(XMLProduct xmlProduct) {
            final var quantity1 = getQuantity(xmlProduct.getQuantity1());
            final var quantity2 = getQuantity(xmlProduct.getQuantity2());
            final var result = quantityByName.get(xmlProduct.getResult());

            if (quantity1.equals(quantity2)) {
                return List.of(
                        new Multiply(quantity1, quantity2, result),
                        new Divide(result, quantity1, quantity2)
                );
            }
            else {
                return List.of(
                        new Multiply(quantity1, quantity2, result),
                        new Multiply(quantity2, quantity1, result),
                        new Divide(result, quantity1, quantity2),
                        new Divide(result, quantity2, quantity1)
                );
            }
        }

        @Override
        public List<Operation> visit(XMLNSAdd xmlnsAdd) {
            final var nonStandardQuantity = getQuantity(xmlnsAdd.getNonStandardQuantity());
            final var standardQuantity = getQuantity(xmlnsAdd.getStandardQuantity());

            if (nonStandardQuantity.isStandard()) {
                throw new IllegalArgumentException("NSAdd operation is only valid for non standard quantity, i.e. Temperature");
            }

            return List.of(
                    new NSAdd(nonStandardQuantity, standardQuantity),
                    new NSDifference(nonStandardQuantity, standardQuantity)
            );
        }

        @Override
        public List<Operation> visit(XMLInverse xmlInverse) {
            final var quantity = getQuantity(xmlInverse.getQuantity());
            final var result = getQuantity(xmlInverse.getResult());

            return List.of(
                    new Inverse(quantity, result),
                    new Inverse(result, quantity)
            );
        }
    }

}
