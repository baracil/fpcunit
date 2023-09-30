package net.femtoparsec.units.core;

import lombok.NonNull;
import lombok.Value;

import java.util.function.Function;

/**
 * @author bastien.a
 */
public record ParsedMeasurement(double value, String unit) {

}
