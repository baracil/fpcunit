package net.femtoparsec.units.generator.parsing;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import net.femtoparsec.units.generator.QuantityReader;
import net.femtoparsec.units.generator.bean.Quantity;
import net.femtoparsec.units.generator.parsing.converter.InformationConverter;
import net.femtoparsec.units.generator.parsing.xml.XMLUnitsInformation;

import java.io.IOException;
import java.util.List;

/**
 * @author Bastien Aracil
 */
@RequiredArgsConstructor
public class JacksonQuantityReader implements QuantityReader {


    public List<Quantity> read() throws IOException {
        final var unitsInformation = this.readUnitsXMLFile();
        XMLInformationInitializer.initialize(unitsInformation);
        return InformationConverter.convert(unitsInformation);
    }


    private XMLUnitsInformation readUnitsXMLFile() throws IOException {
        final var xml = new XmlMapper();
        final var resource = JacksonQuantityReader.class.getResource("/net/femtoparsec/units/generator/units.xml");
        return xml.readValue(resource,XMLUnitsInformation.class);
    }

}
