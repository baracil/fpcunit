package net.femtoparsec.units.generator.parsing;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.femtoparsec.units.generator.parsing.xml.XMLDeprecatedUnit;
import net.femtoparsec.units.generator.parsing.xml.XMLQuantity;
import net.femtoparsec.units.generator.parsing.xml.XMLUnit;
import net.femtoparsec.units.generator.parsing.xml.XMLValueConstant;

import java.util.Arrays;

/**
 * @author Bastien Aracil
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class XMLQuantityInitializer {

    public static void initialize(XMLQuantity xmlQuantity) {
        new XMLQuantityInitializer(xmlQuantity).initialize();
    }

    @NonNull
    private final XMLQuantity xmlQuantity;

    private void initialize() {
        this.initializeStandard();
        this.initializeNames();
        this.initializeChildren();
        new XMLSIUnitInitializer(xmlQuantity, xmlQuantity.getSiUnit()).initialize();
        Arrays.stream(xmlQuantity.getUnits())
                .map(u -> new XMLUnitInitializer(xmlQuantity, u))
                .forEach(XMLUnitInitializer::initialize);
    }

    private void initializeStandard() {
        if (xmlQuantity.getStandard() == null) {
            xmlQuantity.setStandard(Boolean.TRUE);
        }
    }

    private void initializeNames() {
        this.initializeQuantityName();
        this.initializeUnitName();
        this.initializeMeasurementName();
    }

    private void initializeQuantityName() {
        if (this.xmlQuantity.getQuantityName() == null) {
            this.xmlQuantity.setQuantityName(this.xmlQuantity.getName()+"Quantity");
        }
    }

    private void initializeUnitName() {
        if (this.xmlQuantity.getUnitName() == null) {
            this.xmlQuantity.setUnitName(this.xmlQuantity.getName()+"Unit");
        }
    }

    private void initializeMeasurementName() {
        if (this.xmlQuantity.getMeasurementName() == null) {
            this.xmlQuantity.setMeasurementName(this.xmlQuantity.getName());
        }
    }

    private void initializeChildren() {
        if (xmlQuantity.getUnits() == null) {
            xmlQuantity.setUnits(new XMLUnit[0]);
        }
        if (xmlQuantity.getConstants() == null) {
            xmlQuantity.setConstants(new XMLValueConstant[0]);
        }
        if (xmlQuantity.getDeprecatedUnits() == null) {
            xmlQuantity.setDeprecatedUnits(new XMLDeprecatedUnit[0]);
        }
    }


}
