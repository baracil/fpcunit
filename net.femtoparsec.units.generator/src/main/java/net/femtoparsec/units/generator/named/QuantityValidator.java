package net.femtoparsec.units.generator.named;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.femtoparsec.units.generator.bean.Operation;
import net.femtoparsec.units.generator.bean.Quantity;
import net.femtoparsec.units.generator.bean.Unit;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Bastien Aracil
 */
@RequiredArgsConstructor
public class QuantityValidator {

    @NonNull
    private final Quantity quantity;

    private final Map<String, Unit> unitByName = new HashMap<>();

    public void validate() {
        this.addUnit(quantity.getSiUnit());
        this.quantity.getUnits().forEach(this::addUnit);

        final Function<Operation,Void> validator = new OperationValidator().asFunction();

        this.quantity.getOperations().forEach(validator::apply);
    }

    private void addUnit(Unit unit) {
        if (unitByName.containsKey(unit.getUnitName())) {
            throw new IllegalStateException("Duplicate unit name "+unit.getUnitName()+" for quantity "+quantity.getName());
        }
        unitByName.put(unit.getUnitName(), unit);
    }

}
