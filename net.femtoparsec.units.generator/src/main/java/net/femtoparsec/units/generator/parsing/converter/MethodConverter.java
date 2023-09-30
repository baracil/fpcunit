package net.femtoparsec.units.generator.parsing.converter;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.femtoparsec.units.generator.bean.Method;
import net.femtoparsec.units.generator.bean.Quantity;
import net.femtoparsec.units.generator.parsing.xml.XMLMethod;

import java.util.Map;

/**
 * @author Bastien Aracil
 */
@RequiredArgsConstructor
public class MethodConverter {

    @NonNull
    private final Map<String, Quantity> quantityByName;

    public Method convert(XMLMethod xmlMethod) {
        return new Method(getQuantity(xmlMethod.getQuantity()), xmlMethod.getCode());
    }

    @NonNull
    private Quantity getQuantity(String name) {
        final Quantity quantity = quantityByName.get(name);
        if (quantity == null) {
            throw new IllegalArgumentException("Unknown quantity name : "+name);
        }
        return quantity;
    }

}
