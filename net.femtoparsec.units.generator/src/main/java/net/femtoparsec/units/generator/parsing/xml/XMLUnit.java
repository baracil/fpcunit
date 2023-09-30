package net.femtoparsec.units.generator.parsing.xml;

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
//@JsonAlias("unit")
public final class XMLUnit extends XMLAbstractUnit {

    @JacksonXmlProperty(isAttribute = true)
    private String factor;

    @JacksonXmlProperty(isAttribute = true)
    private String offset;
}
