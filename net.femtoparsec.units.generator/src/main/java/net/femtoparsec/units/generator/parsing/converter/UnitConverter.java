package net.femtoparsec.units.generator.parsing.converter;

import lombok.AccessLevel;
import net.femtoparsec.units.generator.bean.Unit;
import net.femtoparsec.units.generator.parsing.xml.XMLAbstractUnit;
import net.femtoparsec.units.generator.parsing.xml.XMLSIUnit;
import net.femtoparsec.units.generator.parsing.xml.XMLUnit;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

/**
 * @author Bastien Aracil
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UnitConverter {

    public static Unit convert(XMLAbstractUnit unit) {
        return new UnitConverter(unit).convert();
    }

    @NonNull
    private final XMLAbstractUnit unit;

    private Unit.Builder unitBuilder;

    private Unit convert() {
        this.initializeBuilder();
        this.initializeInstanceName();
        this.initializeUnitName();
        this.initializeSIFactorAndOffset();
        return unitBuilder.build();
    }

    private void initializeBuilder() {
        this.unitBuilder = Unit.builder();
    }

    private void initializeInstanceName() {
        this.unitBuilder.instanceName(unit.getInstanceName());
    }

    private void initializeUnitName() {
        this.unitBuilder.unitName(this.unit.getUnitName());

    }

    private void initializeSIFactorAndOffset() {
        if (unit instanceof XMLSIUnit) {
            this.unitBuilder.si(true);
            this.unitBuilder.factor("1");
            this.unitBuilder.offset("0");
        }
        else if (unit instanceof XMLUnit xmlUnit){
            this.unitBuilder.si(false);
            this.unitBuilder.factor(xmlUnit.getFactor());
            this.unitBuilder.offset(Objects.requireNonNullElse(xmlUnit.getOffset(),"0"));
        }
    }


}
