package net.femtoparsec.units.generator.parsing.xml;

import java.util.function.Function;

/**
 * @author Bastien Aracil
 */
public interface XMLOperationVisitor<T> {

    default Function<XMLOperation, T> asFunction() {
        return o -> o.accept(this);
    }

    T visit(XMLSquare xmlSquare);
    T visit(XMLProduct xmlProduct);
    T visit(XMLNSAdd xmlnsAdd);
    T visit(XMLCubic xmlCubic);
    T visit(XMLInverse xmlInverse);
}
