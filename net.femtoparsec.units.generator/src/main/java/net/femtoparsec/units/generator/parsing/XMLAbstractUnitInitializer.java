package net.femtoparsec.units.generator.parsing;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.femtoparsec.units.generator.parsing.xml.XMLAbstractUnit;
import net.femtoparsec.units.generator.parsing.xml.XMLQuantity;

/**
 * @author Bastien Aracil
 */
@Getter
@RequiredArgsConstructor
public class XMLAbstractUnitInitializer<U extends XMLAbstractUnit> {

    @NonNull
    private final XMLQuantity quantity;

    @NonNull
    private final U xmlUnit;

    public void initialize() {
        this.initializeQuantity();
    }

    private void initializeQuantity() {
        this.xmlUnit.setQuantity(quantity);
    }

}
