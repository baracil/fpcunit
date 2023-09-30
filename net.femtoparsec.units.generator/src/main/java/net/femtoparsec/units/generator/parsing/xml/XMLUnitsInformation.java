package net.femtoparsec.units.generator.parsing.xml;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.ToString;
import net.femtoparsec.units.generator.parsing.XMLOperationDeserializer;

/**
 * @author Bastien Aracil
 */
@ToString
@Getter
//@JsonAlias("units-information")
public class XMLUnitsInformation {

    @JsonAlias("quantities")
    private XMLQuantity[] quantities;

    @JsonDeserialize(using = XMLOperationDeserializer.class)
    @JsonAlias("operations")
    private XMLOperation[] operations;

    @JsonAlias("methods")
    private XMLMethod[] methods;
}
