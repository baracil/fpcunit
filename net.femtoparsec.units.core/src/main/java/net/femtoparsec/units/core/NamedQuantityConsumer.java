package net.femtoparsec.units.core;

import net.femtoparsec.units.api.Measurement;
import net.femtoparsec.units.api.NamedQuantity;

/**
 * @author Bastien Aracil
 */
public interface NamedQuantityConsumer {

  <Q extends NamedQuantity<M>, M extends Measurement<?>> void accept(Class<Q> quantityType, Q quantity);
}
