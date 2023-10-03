package net.femtoparsec.units.api._private;

import net.femtoparsec.units.api.Unit;
import net.femtoparsec.units.api.UnitSystem;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Bastien Aracil
 */
public class SimpleUnitSystem implements UnitSystem {

  private final Set<Unit<?>> units;

  public SimpleUnitSystem(Set<Unit<?>> units) {
    this.units = Set.copyOf(units);
  }

  @Override
  public Set<Unit<?>> getUnits() {
    return units;
  }

  @Override
  public <U extends Unit<?>> Set<U> getUnits(Class<U> unitType) {
    return this.units
        .stream()
        .filter(unitType::isInstance)
        .map(unitType::cast)
        .collect(Collectors.toSet());
  }

  @Override
  public Predicate<Unit<?>> systemPredicate() {
    return units::contains;
  }
}
