package net.femtoparsec.units.api;

/**
 * @author Bastien Aracil
 */
public interface MeasurementFactory<Q extends Quantity, M extends Measurement<Q>> {

    M create(double value, Unit<Q> unit);

}
