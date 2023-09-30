package net.femtoparsec.units.api;

import net.femtoparsec.units.api._private.SimpleUnitSystemBuilder;

import java.util.Set;
import java.util.function.Predicate;

/**
 * @author Bastien Aracil
 */
public interface UnitSystem {

  Predicate<Unit<?, ?, ?>> systemPredicate();

  Set<Unit<?, ?, ?>> getUnits();

  <U extends Unit<?, ?, ?>> Set<U> getUnits(Class<U> unitType);

  static Builder builder() {
    return new SimpleUnitSystemBuilder();
  }

  interface Builder {
    UnitSystem build();

    Builder addUnit(Unit<?, ?, ?> unit);

    Builder addSystem(UnitSystem system);
  }

}
