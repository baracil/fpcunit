package net.femtoparsec.units.core;

import net.femtoparsec.units.api.Measurement;
import net.femtoparsec.units.api.Quantity;
import net.femtoparsec.units.api.Unit;

public interface MeasurementFactory<Q extends Quantity, M extends Measurement<Q>> {

  M create(double value, Unit<Q> unit);

}
