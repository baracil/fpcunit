package net.femtoparsec.units.api;

import java.io.Serializable;
import java.util.Optional;


/**
 * A physical quantity.
 *
 * @author Bastien Aracil
 */
public interface NamedQuantity<M> extends Quantity {

  M createWithSI(double siValue);

}
