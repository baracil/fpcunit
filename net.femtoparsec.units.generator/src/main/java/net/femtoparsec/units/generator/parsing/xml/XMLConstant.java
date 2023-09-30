package net.femtoparsec.units.generator.parsing.xml;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author bastien.a
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({ @JsonSubTypes.Type(XMLValueConstant.class), @JsonSubTypes.Type(XMLExpressionConstant.class) })
public interface XMLConstant {

    <T> T accept(XMLConstantVisitor<T> visitor);

}
