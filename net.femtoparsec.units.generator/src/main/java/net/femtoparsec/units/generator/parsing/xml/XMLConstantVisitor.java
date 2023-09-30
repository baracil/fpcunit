package net.femtoparsec.units.generator.parsing.xml;

/**
 * @author bastien.a
 */
public interface XMLConstantVisitor<T> {

    T visit(XMLValueConstant constant);

    T visit(XMLExpressionConstant constant);
}
