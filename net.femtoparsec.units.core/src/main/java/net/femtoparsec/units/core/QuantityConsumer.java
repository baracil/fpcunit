package net.femtoparsec.units.core;

import net.femtoparsec.units.api.Measurement;
import net.femtoparsec.units.api.Quantity;
import net.femtoparsec.units.api.Unit;

/**
 * @author Bastien Aracil
 */
public interface QuantityConsumer {

    void accept(Quantity<?,?,?> quantity);
}
