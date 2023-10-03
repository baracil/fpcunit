package net.femtoparsec.units.api._private;

import net.femtoparsec.units.api.Unit;
import net.femtoparsec.units.api.UnitSystem;

import java.util.HashSet;
import java.util.Set;

public class SimpleUnitSystemBuilder implements UnitSystem.Builder {

  private final Set<Unit<?>> units = new HashSet<>();

  @Override
  public UnitSystem build() {
    return new SimpleUnitSystem(units);
  }

  @Override
  public UnitSystem.Builder addUnit(Unit<?> unit) {
    this.units.add(unit);
    return this;
  }

  @Override
  public UnitSystem.Builder addSystem(UnitSystem system) {
    this.units.addAll(system.getUnits());
    return this;
  }
}
