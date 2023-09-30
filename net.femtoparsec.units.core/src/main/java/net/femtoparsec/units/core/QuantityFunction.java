package net.femtoparsec.units.core;

import net.femtoparsec.units.api.Quantity;

/**
 * @author Bastien Aracil
 */
public interface QuantityFunction<T> {

  T apply(Quantity<?, ?, ?> quantity);
}
