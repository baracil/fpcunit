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
public final class XMLSIUnit extends XMLAbstractUnit {
//  @JacksonXmlProperty(isAttribute = true)
//  @JsonAlias("unit-name")
//  private String unitName;
//
//  @JacksonXmlProperty(isAttribute = true)
//  @JsonAlias("instance-name")
//  private String instanceName;

}
