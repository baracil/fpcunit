package net.femtoparsec.units.generator.parsing.xml;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Bastien Aracil
 */
@Getter @Setter
public sealed abstract class XMLAbstractUnit permits XMLSIUnit, XMLUnit {

    @JsonIgnore
    private XMLQuantity quantity;

    @JacksonXmlProperty(isAttribute = true)
    @JsonAlias("unit-name")
    private String unitName;

    @JacksonXmlProperty(isAttribute = true)
    @JsonAlias("instance-name")
    private String instanceName;


}
