package net.femtoparsec.units.generator.parsing.xml;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.ToString;

/**
 * @author bastien.a
 */
@ToString
@Getter
//@JsonAlias("expression")
public class XMLExpressionConstant implements XMLConstant{

    @JacksonXmlProperty(isAttribute = true)
    @JsonAlias("name")
    private String name;

    @JacksonXmlProperty(isAttribute = true)
    @JsonAlias("expression")
    private String expression;

    @Override
    public <T> T accept(XMLConstantVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
