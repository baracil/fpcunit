package net.femtoparsec.units.property;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.value.ObservableObjectValue;
import javafx.beans.value.ObservableValue;
import net.femtoparsec.units.api.*;
import net.femtoparsec.units.core.UnitUtils;

import java.util.function.Function;

/**
 * @author Bastien Aracil
 */
public class MeasurementBindings {

    public static <Q extends Quantity<Q,U,M>,U extends Unit<Q,U,M>,M extends Measurement<Q,U, M>> DoubleProperty inUnit(Property<M> measurement, U unit) {
        return new MeasurementValueBinding<>(measurement, unit);
    }

    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U,M>,M extends Measurement<Q,U,M>> StringBinding asPrettyString(ObservableValue<M> measurement) {
        return asPrettyString(measurement, "%e");
    }

    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U,M>,M extends Measurement<Q,U,M>> StringBinding asPrettyString(ObservableValue<M> measurement, String format) {
        return new StringBinding() {
            {
                super.bind(measurement);
            }
            @Override
            protected String computeValue() {
                final M measurementValue = measurement.getValue();
                if (measurementValue == null) {
                    return null;
                }
                return measurementValue.toPrettyString(format);
            }
        };
    }

    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U,M>,M extends Measurement<Q,U,M>> StringBinding asPrettyString(ObservableValue<M> measurement, Function<Double, String> converter) {
        return new StringBinding() {
            {
                super.bind(measurement);
            }
            @Override
            protected String computeValue() {
                final M measurementValue = measurement.getValue();
                if (measurementValue == null) {
                    return null;
                }
                return measurementValue.toPrettyString(converter);
            }
        };
    }

    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U,M>,M extends Measurement<Q,U,M>> StringBinding asPrettyString(ObservableValue<M> measurement, ObservableObjectValue<Function<Double,String>> converter) {
        return new StringBinding() {
            {
                super.bind(measurement, converter);
            }
            @Override
            protected String computeValue() {
                final Function<Double, String> converterValue = converter.getValue();
                final M measurementValue = measurement.getValue();
                if (measurementValue == null || converterValue == null) {
                    return null;
                }
                return measurementValue.toPrettyString(converterValue);
            }
        };
    }

    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U,M>,M extends Measurement<Q,U,M>> ObjectBinding<Double> inSI(ObservableValue<M> measurement) {
        if (measurement == null) {
            return NULL_DOUBLE_OBJECT_BINDING;
        }
        else {
            return new SingleMeasurementDoubleValueBinding<M>(measurement) {
                @Override
                protected Double computeValue(M measurement) {
                    return measurement.getValueInSI();
                }
            };
        }
    }

    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U,M>,M extends Measurement<Q,U,M>> DoubleBinding inSI(ObservableValue<M> measurement, double defaultValue) {
        if (measurement == null) {
            return constantDoubleBinding(defaultValue);
        }
        else {
            return new SingleMeasurementValueBinding<M>(measurement, defaultValue) {
                @Override
                protected double computeValue(M measurement) {
                    return measurement.getValueInSI();
                }
            };
        }
    }

    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U,M>,M extends Measurement<Q,U,M>> ObjectBinding<Double> inUnit(ObservableValue<M> measurement, U unit) {
        if (measurement == null || unit == null) {
            return NULL_DOUBLE_OBJECT_BINDING;
        }
        else {
            return new SingleMeasurementDoubleValueBinding<M>(measurement) {
                @Override
                protected Double computeValue(M measurement) {
                    return measurement.getValueInUnit(unit);
                }
            };
        }
    }

    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U,M>,M extends Measurement<Q,U,M>> DoubleBinding inUnit(ObservableValue<M> measurement, U unit, double defaultValue) {
        if (measurement == null || unit == null) {
            return constantDoubleBinding(defaultValue);
        }
        else {
            return new SingleMeasurementValueBinding<M>(measurement, defaultValue) {
                @Override
                protected double computeValue(M measurement) {
                    return measurement.getValueInUnit(unit);
                }
            };
        }
    }

    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U,M>,M extends Measurement<Q,U,M>> ObjectBinding<Double> inUnit(ObservableValue<M> measurement, ObservableObjectValue<U> unit) {
        if (measurement == null || unit == null) {
            return NULL_DOUBLE_OBJECT_BINDING;
        }
        else {
            return new ObjectBinding<Double>() {
                {
                    super.bind(measurement, unit);
                }

                @Override
                protected Double computeValue() {
                    final M m = measurement.getValue();
                    final U u = unit.get();
                    return m == null || u == null ? null : m.getValueInUnit(u);
                }
            };
        }
    }

    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U,M>,M extends Measurement<Q,U,M>> DoubleBinding inUnit(ObservableValue<M> measurement, ObservableObjectValue<U> unit, double defaultValue) {
        if (measurement == null || unit == null) {
            return constantDoubleBinding(defaultValue);
        }
        else {
            return new DoubleBinding() {
                {
                    super.bind(measurement, unit);
                }

                @Override
                protected double computeValue() {
                    final M m = measurement.getValue();
                    final U u = unit.get();
                    return m == null || u == null ? defaultValue : m.getValueInUnit(u);
                }
            };
        }
    }

    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U,M>,M extends Measurement<Q,U,M>> MeasurementBinding<Q,U,M> inAdaptedUnit(ObservableValue<M> measurement) {
        if (measurement == null) {
            return nullMeasurementBinding();
        }
        else {
            return new SingleSameMeasurementBinding<Q,U,M>(measurement) {
                @Override
                protected M computeValue(M measurement) {
                    return UnitUtils.adaptUnit(measurement);
                }
            };
        }
    }

    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U, M>,M extends Measurement<Q,U,M>> MeasurementBinding<Q,U,M> inAdaptedUnit(ObservableValue<M> measurement, U unitForZero) {
        if (measurement == null) {
            return nullMeasurementBinding();
        }
        else {
            return new SingleSameMeasurementBinding<Q,U,M>(measurement) {
                @Override
                protected M computeValue(M measurement) {
                    return UnitUtils.adaptUnit(measurement, unitForZero);
                }
            };
        }
    }

    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U, M>,M extends Measurement<Q,U,M>> MeasurementBinding<Q,U,M> inAdaptedUnit(ObservableValue<M> measurement, UnitSystem unitSystem) {
        if (measurement == null) {
            return nullMeasurementBinding();
        }
        else {
            return new SingleSameMeasurementBinding<Q,U,M>(measurement) {
                @Override
                protected M computeValue(M measurement) {
                    return UnitUtils.adaptUnit(measurement, unitSystem);
                }
            };
        }
    }

    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U, M>,M extends Measurement<Q,U,M>> MeasurementBinding<Q,U,M> inAdaptedUnit(ObservableValue<M> measurement, ObservableValue<U> unitForZero) {
        if (unitForZero == null) {
            return inAdaptedUnit(measurement);
        }
        else if (measurement == null) {
            return nullMeasurementBinding();
        }
        else {
            return new SingleSameMeasurementBinding<Q,U,M>(measurement, unitForZero) {
                @Override
                protected M computeValue(M measurement) {
                    return UnitUtils.adaptUnit(measurement, unitForZero.getValue());
                }
            };
        }
    }

    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U, M>,M extends LinearMeasurement<Q,U,M>> StandardMeasurementBinding<Q,U,M> inAdaptedUnitStandard(ObservableValue<M> measurement) {
        if (measurement == null) {
            return nullStandardMeasurementBinding();
        }
        else {
            return new SingleSameStandardMeasurementBinding<Q,U,M>(measurement) {
                @Override
                protected M computeValue(M measurement) {
                    return UnitUtils.adaptUnit(measurement);
                }
            };
        }
    }

    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U, M>,M extends LinearMeasurement<Q,U,M>> StandardMeasurementBinding<Q,U,M> inAdaptedUnitStandard(ObservableValue<M> measurement, U unitForZero) {
        if (measurement == null) {
            return nullStandardMeasurementBinding();
        }
        else {
            return new SingleSameStandardMeasurementBinding<Q,U,M>(measurement) {
                @Override
                protected M computeValue(M measurement) {
                    return UnitUtils.adaptUnit(measurement, unitForZero);
                }
            };
        }
    }

    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U, M>,M extends LinearMeasurement<Q,U,M>> StandardMeasurementBinding<Q,U,M> inAdaptedUnitStandard(ObservableValue<M> measurement, ObservableValue<U> unitForZero) {
        if (unitForZero == null) {
            return inAdaptedUnitStandard(measurement);
        }
        else if (measurement == null) {
            return nullStandardMeasurementBinding();
        }
        else {
            return new SingleSameStandardMeasurementBinding<Q,U,M>(measurement, unitForZero) {
                @Override
                protected M computeValue(M measurement) {
                    return UnitUtils.adaptUnit(measurement, unitForZero.getValue());
                }
            };
        }
    }

    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U, M>,M extends LinearMeasurement<Q,U,M>> StandardMeasurementBinding<Q,U,M> inAdaptedUnitStandard(ObservableValue<M> measurement, UnitSystem unitSystem) {
        if (unitSystem == null) {
            return inAdaptedUnitStandard(measurement);
        }
        else if (measurement == null) {
            return nullStandardMeasurementBinding();
        }
        else {
            return new SingleSameStandardMeasurementBinding<Q,U,M>(measurement) {
                @Override
                protected M computeValue(M measurement) {
                    return UnitUtils.adaptUnit(measurement, unitSystem);
                }
            };
        }
    }


    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U, M>,M extends LinearMeasurement<Q,U,M>> StandardMeasurementBinding<Q,U,M> add(ObservableValue<M> m1, M m2) {
        if (m2 == null || m1 == null) {
            return nullStandardMeasurementBinding();
        }
        else {
            return new SingleSameStandardMeasurementBinding<Q,U,M>(m1) {
                @Override
                protected M computeValue(M measurement) {
                    return measurement.add(m2);
                }
            };
        }
    }

    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U, M>,M extends LinearMeasurement<Q,U,M>> StandardMeasurementBinding<Q,U,M> add(ObservableValue<M> m1, ObservableValue<M> m2) {
        if (m2 == null || m1 == null) {
            return nullStandardMeasurementBinding();
        }
        else {
            return new DoubleSameStandardMeasurementBinding<Q,U,M>(m1, m2) {
                @Override
                protected M computeValue(M measurement, M measurement1) {
                    return measurement == null || measurement1 == null ? null : measurement.add(measurement1);
                }
            };
        }
    }

    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U, M>,M extends LinearMeasurement<Q,U,M>> StandardMeasurementBinding<Q,U,M> subtract(ObservableValue<M> m1, ObservableValue<M> m2) {
        if (m1 == null || m2 == null) {
            return nullStandardMeasurementBinding();
        }
        else {
            return new DoubleSameStandardMeasurementBinding<Q,U,M>(m1,m2) {
                @Override
                protected M computeValue(M measurement1, M measurement2) {
                    return measurement1 == null || measurement2 == null ? null : measurement1.subtract(measurement2);
                }
            };
        }
    }

    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U, M>,M extends LinearMeasurement<Q,U,M>> StandardMeasurementBinding<Q,U,M> negate(ObservableValue<M> measurement) {
        if (measurement == null) {
            return nullStandardMeasurementBinding();
        }
        else {
            return new SingleSameStandardMeasurementBinding<Q,U,M>(measurement) {
                @Override
                protected M computeValue(M measurement) {
                    return measurement == null ? null : measurement.negate();
                }
            };
        }
    }


    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U, M>,M extends LinearMeasurement<Q,U,M>> StandardMeasurementBinding<Q,U,M> scale(ObservableValue<M> measurement, double factor) {
        if (measurement == null) {
            return nullStandardMeasurementBinding();
        }
        else {
            return new SingleSameStandardMeasurementBinding<Q,U,M>(measurement) {
                @Override
                protected M computeValue(M measurement) {
                    return measurement == null ? null : measurement.scale(factor);
                }
            };
        }
    }

    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U, M>,M extends LinearMeasurement<Q,U,M>> StandardMeasurementBinding<Q,U,M> scale(ObservableValue<M> measurement, ObservableDoubleValue factor) {
        if (measurement == null || factor == null) {
            return nullStandardMeasurementBinding();
        }
        else {
            return new StandardMeasurementBinding<Q,U,M>() {
                {
                    super.bind(measurement, factor);
                }
                @Override
                protected M computeValue() {
                    final M m = measurement.getValue();
                    return m == null ? null : m.scale(factor.doubleValue());
                }
            };
        }
    }

    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U, M>,M extends LinearMeasurement<Q,U,M>> StandardMeasurementBinding<Q,U,M> abs(ObservableValue<M> measurement) {
        if (measurement == null) {
            return nullStandardMeasurementBinding();
        }
        else {
            return new SingleSameStandardMeasurementBinding<Q,U,M>(measurement) {
                @Override
                protected M computeValue(M measurement) {
                    return measurement.abs();
                }
            };
        }
    }

    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U, M>,M extends LinearMeasurement<Q,U,M>> ObjectBinding<Double> divide(ObservableValue<M> measurement1, M measurement2) {
        if (measurement1 == null || measurement2 == null) {
            return NULL_DOUBLE_OBJECT_BINDING;
        }
        else {
            return new SingleMeasurementDoubleValueBinding<M>(measurement1) {
                @Override
                protected Double computeValue(M measurement) {
                    return measurement.divide(measurement2);
                }
            };
        }
    }

    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U, M>,M extends LinearMeasurement<Q,U,M>> ObjectBinding<Double> divide(ObservableValue<M> measurement1, ObservableValue<M> measurement2) {
        if (measurement1 == null || measurement2 == null) {
            return NULL_DOUBLE_OBJECT_BINDING;
        }
        else {
            return new ObjectBinding<Double>() {
                {
                    super.bind(measurement1, measurement2);
                }
                @Override
                protected Double computeValue() {
                    final M m1 = measurement1.getValue();
                    final M m2 = measurement2.getValue();
                    return m1 == null || m2 == null ? null : m1.divide(m2);
                }
            };
        }
    }

    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U, M>,M extends LinearMeasurement<Q,U,M>> DoubleBinding divide(ObservableValue<M> measurement1, M measurement2, double defaultValue) {
        if (measurement1 == null || measurement2 == null) {
            return constantDoubleBinding(defaultValue);
        }
        else {
            return new SingleMeasurementValueBinding<M>(measurement1, defaultValue) {
                @Override
                protected double computeValue(M measurement) {
                    return measurement.divide(measurement2);
                }
            };
        }
    }

    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U, M>,M extends LinearMeasurement<Q,U,M>> DoubleBinding divide(ObservableValue<M> measurement1, ObservableValue<M> measurement2, double defaultValue) {
        if (measurement1 == null || measurement2 == null) {
            return constantDoubleBinding(defaultValue);
        }
        else {
            return new DoubleBinding() {
                {
                    super.bind(measurement1, measurement2);
                }

                @Override
                protected double computeValue() {
                    final M m1 = measurement1.getValue();
                    final M m2 = measurement2.getValue();
                    return m1 == null || m2 == null ? defaultValue : m1.divide(m2);
                }
            };
        }
    }

    private static final StandardMeasurementBinding NULL_STANDARD_MEASUREMENT_CONSTANT = new StandardMeasurementBinding() {
        @Override
        protected LinearMeasurement computeValue() {
            return null;
        }
    };

    private static final MeasurementBinding NULL_MEASUREMENT_CONSTANT = new MeasurementBinding() {
        @Override
        protected Measurement computeValue() {
            return null;
        }
    };

    private static final ObjectBinding<Double> NULL_DOUBLE_OBJECT_BINDING = new ObjectBinding<Double>() {
        @Override
        protected Double computeValue() {
            return null;
        }
    };
    private static DoubleBinding constantDoubleBinding(double constant) {
        return new DoubleBinding() {
            @Override
            protected double computeValue() {
                return constant;
            }
        };
    }

    @SuppressWarnings("unchecked")
    private static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U, M>,M extends LinearMeasurement<Q,U,M>> StandardMeasurementBinding<Q,U, M> nullStandardMeasurementBinding() {
        return NULL_STANDARD_MEASUREMENT_CONSTANT;
    }

    @SuppressWarnings("unchecked")
    private static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U,M>,M extends Measurement<Q,U,M>> MeasurementBinding<Q,U,M> nullMeasurementBinding() {
        return NULL_MEASUREMENT_CONSTANT;
    }

}
