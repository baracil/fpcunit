package net.femtoparsec.units.generator.parsing.xml;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Bastien Aracil
 */
@Getter
@ToString
//@JsonAlias("product")
public class XMLProduct implements XMLOperation {

    @JacksonXmlProperty(isAttribute = true)
    @JsonAlias("quantity1")
    private String quantity1;

    @JacksonXmlProperty(isAttribute = true)
    @JsonAlias("quantity2")
    private String quantity2;

    @JacksonXmlProperty(isAttribute = true)
    @JsonAlias("result")
    private String result;

    @Override
    public <T> T accept(XMLOperationVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
