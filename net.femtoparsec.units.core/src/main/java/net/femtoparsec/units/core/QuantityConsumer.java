package net.femtoparsec.units.core;

import net.femtoparsec.units.api.Quantity;

/**
 * @author Bastien Aracil
 */
public interface QuantityConsumer {

    void accept(Quantity quantity);
}
