package net.femtoparsec.units.generator.parsing.xml;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Bastien Aracil
 */
@ToString
@Getter
@Setter
public class XMLQuantity {

    @JacksonXmlProperty(isAttribute = true)
    private String dimension;

    @JacksonXmlProperty(isAttribute = true)
    private String name;

    @JacksonXmlProperty(isAttribute = true)
    @JsonAlias("quantity-name")
    private String quantityName;

    @JacksonXmlProperty(isAttribute = true)
    @JsonAlias("unit-name")
    private String unitName;

    @JacksonXmlProperty(isAttribute = true)
    @JsonAlias("measurement-name")
    private String measurementName;

    @JacksonXmlProperty(isAttribute = true)
    @JsonAlias("standard")
    private Boolean standard;

    @JsonAlias("si-unit")
    private XMLSIUnit siUnit;

    @JsonAlias("units")
    private XMLUnit[] units;

    @JsonAlias("deprecated")
    private XMLDeprecatedUnit[] deprecatedUnits;

    @JsonAlias("constants")
    private XMLConstant[] constants;


}
