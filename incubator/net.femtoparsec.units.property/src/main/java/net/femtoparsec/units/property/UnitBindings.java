package net.femtoparsec.units.property;

import net.femtoparsec.units.api.Unit;
import javafx.beans.binding.StringBinding;
import javafx.beans.value.ObservableValue;

/**
 * @author Bastien Aracil
 */
public class UnitBindings {

    public static StringBinding asPrettyString(ObservableValue<? extends Unit<?,?,?>> unit) {
        if (unit == null) {
            return new StringBinding() {
                @Override
                protected String computeValue() {
                    return null;
                }
            };
        }
        else {
            return new StringBinding() {
                {
                    super.bind(unit);
                }
                @Override
                protected String computeValue() {
                    final Unit<?,?,?> unitValue = unit.getValue();
                    return unitValue == null ? null : unitValue.getPrettyName();
                }
            };
        }
    }
}
