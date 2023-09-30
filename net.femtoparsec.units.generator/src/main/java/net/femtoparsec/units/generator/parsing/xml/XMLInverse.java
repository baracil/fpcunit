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
//@JsonAlias("inverse")
public class XMLInverse implements XMLOperation {

    @JacksonXmlProperty(isAttribute = true)
    @JsonAlias("quantity")
    private String quantity;

    @JacksonXmlProperty(isAttribute = true)
    @JsonAlias("result")
    private String result;

    @Override
    public <T> T accept(XMLOperationVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
