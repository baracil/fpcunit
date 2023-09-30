package net.femtoparsec.units.api;

/**
 * @author Bastien Aracil
 */
public interface MeasurementFactory<Q extends Quantity<Q,U,M>, U extends Unit<Q,U,M>, M extends Measurement<Q,U,M>> {

    M create(double value, U unit);

}
