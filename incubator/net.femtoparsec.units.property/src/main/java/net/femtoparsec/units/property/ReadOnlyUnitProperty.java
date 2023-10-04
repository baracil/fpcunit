package net.femtoparsec.units.property;

import net.femtoparsec.units.api.Unit;
import javafx.beans.property.ReadOnlyProperty;

/**
 * @author Bastien Aracil
 */
public abstract class ReadOnlyUnitProperty<U extends Unit<?,U,?>> extends UnitExpression<U> implements ReadOnlyProperty<U> {
}
