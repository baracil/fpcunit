package net.femtoparsec.units.generator.parsing.xml;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Bastien Aracil
 */
@ToString(callSuper = true)
@Getter
@Setter
//@JsonAlias("deprecated-unit")
public class XMLDeprecatedUnit {

    @JacksonXmlProperty(isAttribute = true)
    @JsonAlias("instance-name")
    private String instanceName;

    @JacksonXmlProperty(isAttribute = true)
    @JsonAlias("reference-unit-name")
    private String referenceUnitName;
}
