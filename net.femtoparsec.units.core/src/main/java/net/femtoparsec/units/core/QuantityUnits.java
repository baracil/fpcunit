package net.femtoparsec.units.core;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import net.femtoparsec.units.api.Measurement;
import net.femtoparsec.units.api.Quantity;
import net.femtoparsec.units.api.Unit;
import net.femtoparsec.units.api.UnknownUnitException;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * List all units for a specific quantity
 * @author Bastien Aracil
 */
public class QuantityUnits<Q extends Quantity<Q,U,M>, U extends Unit<Q,U,M>, M extends Measurement<Q,U,M>> {

    @Getter
    private final U siUnit;

    private final Map<String, U> unitByName;

    @Getter
    private final NavigableMap<Double, List<U>> unitsByFactor;

    @Builder
    public QuantityUnits(@Singular List<U> units) {
        this.siUnit = units.get(0).getReferenceSI();
        this.unitByName = units.stream().collect(Collectors.toMap(Unit::getName, Function.identity()));
        this.unitsByFactor = units.stream().collect(Collectors.groupingBy(
            u -> 1./u.getFactorToSI(),
            TreeMap::new,
            Collectors.toUnmodifiableList()));
    }

    public Optional<U> findUnit(String name) {
        return Optional.ofNullable(unitByName.get(name));
    }

    public U getUnit(String name) {
        return findUnit(name).orElseThrow(() -> new UnknownUnitException(name, siUnit.getQuantity()));
    }

}
