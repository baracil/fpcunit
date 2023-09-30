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
//@JsonAlias("method")
public class XMLMethod {

    @JacksonXmlProperty(isAttribute = true)
    @JsonAlias("quantity")
    private String quantity;

    private String code;


}
