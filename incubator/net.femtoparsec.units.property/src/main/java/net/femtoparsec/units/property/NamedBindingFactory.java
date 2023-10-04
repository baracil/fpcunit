package net.femtoparsec.units.property;

import net.femtoparsec.units.api.Measurement;
import net.femtoparsec.units.api.Unit;
import net.femtoparsec.units.api.Quantity;
import net.femtoparsec.units.property.measurement.BindingComputer;
import javafx.beans.Observable;


/**
 * @author Bastien Aracil
 */
public interface NamedBindingFactory<Q extends Quantity<Q,U,M>, U extends Unit<Q,U,M>,M extends Measurement<Q,U,M>, B> {

    B create(BindingComputer<M> computer, Observable... dependencies);

}
