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
//@JsonAlias("nsadd")
public class XMLNSAdd implements XMLOperation {

    @JacksonXmlProperty(isAttribute = true)
    @JsonAlias("non-standard-quantity")
    private String nonStandardQuantity;

    @JacksonXmlProperty(isAttribute = true)
    @JsonAlias("standard-quantity")
    private String standardQuantity;

    @Override
    public <T> T accept(XMLOperationVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
