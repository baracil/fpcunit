package net.femtoparsec.units.core;

import net.femtoparsec.units.api.Measurement;
import net.femtoparsec.units.api.NamedQuantity;

/**
 * @author Bastien Aracil
 */
public interface NamedQuantityFunction<T> {

  <Q extends NamedQuantity<M>, M extends Measurement<?>> T apply(Class<Q> quantityType, Q quantity);

}
