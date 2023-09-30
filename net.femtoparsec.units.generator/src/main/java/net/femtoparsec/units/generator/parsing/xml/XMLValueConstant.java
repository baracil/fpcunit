package net.femtoparsec.units.generator.parsing.xml;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Bastien Aracil
 */
@ToString
@Getter
//@JsonAlias("value")
public class XMLValueConstant implements XMLConstant {

    @JacksonXmlProperty(isAttribute = true)
    @JsonAlias("name")
    private String name;

    @JacksonXmlProperty(isAttribute = true)
    @JsonAlias("unit-name")
    private String unitName;

    @JacksonXmlProperty(isAttribute = true)
    @JsonAlias("value")
    private String value;

    @Override
    public <T> T accept(XMLConstantVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
