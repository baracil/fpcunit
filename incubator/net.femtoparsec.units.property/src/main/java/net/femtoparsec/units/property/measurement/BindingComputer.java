package net.femtoparsec.units.property.measurement;

import net.femtoparsec.units.api.Measurement;

/**
 * @author Bastien Aracil
 */
public interface BindingComputer<M extends Measurement<?,?,M>> {

    BindingComputer NULL_BINDING = () -> null;

    @SuppressWarnings("unchecked")
    static <M extends Measurement<?,?,M>> BindingComputer<M> nullBindingComputer() {
        return NULL_BINDING;
    }

    M compute();

}
