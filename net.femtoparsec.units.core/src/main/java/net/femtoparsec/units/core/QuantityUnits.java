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
public class QuantityUnits<Q extends Quantity, U extends Unit<Q>> {

    @Getter
    private final U siUnit;

    private final Map<String, U> unitByName;

    @Getter
    private final NavigableMap<Double, List<U>> unitsByFactor;

    @Builder
    public QuantityUnits(@Singular List<U> units) {
        if (units.isEmpty()) {
            throw new IllegalArgumentException("No units provided");
        }
        final var quantity = units.get(0).getQuantity();

        this.siUnit = units.stream()
            .filter(Unit::isSI)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("No SI unit for quantity : "+quantity));

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
