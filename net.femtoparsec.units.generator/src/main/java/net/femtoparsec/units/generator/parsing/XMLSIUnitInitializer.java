package net.femtoparsec.units.generator.parsing;

import net.femtoparsec.units.generator.parsing.xml.XMLQuantity;
import net.femtoparsec.units.generator.parsing.xml.XMLSIUnit;

/**
 * @author Bastien Aracil
 */
public class XMLSIUnitInitializer extends XMLAbstractUnitInitializer<XMLSIUnit> {

    public XMLSIUnitInitializer(XMLQuantity quantity, XMLSIUnit xmlUnit) {
        super(quantity, xmlUnit);
    }

    @Override
    public void initialize() {
        super.initialize();
        this.initializeName();
    }

    private void initializeName() {
        if (this.getXmlUnit().getUnitName() == null) {
            getXmlUnit().setUnitName(getQuantity().getDimension());
        }
    }

}
