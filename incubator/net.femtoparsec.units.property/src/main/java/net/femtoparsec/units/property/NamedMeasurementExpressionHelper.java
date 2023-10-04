package net.femtoparsec.units.property;

import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.value.ObservableValue;
import net.femtoparsec.units.api.LinearMeasurement;
import net.femtoparsec.units.api.Measurement;
import net.femtoparsec.units.api.Quantity;
import net.femtoparsec.units.api.Unit;
import net.femtoparsec.units.core.MeasurementOperations;
import net.femtoparsec.units.core.UnitUtils;
import net.femtoparsec.units.named.measurement.DeltaTemperature;
import net.femtoparsec.units.named.measurement.Temperature;
import net.femtoparsec.units.property.measurement.BindingComputer;
import net.femtoparsec.units.property.measurement.DeltaTemperatureBinding;
import net.femtoparsec.units.property.measurement.TemperatureBinding;

/**
 * @author Bastien Aracil
 */
public class NamedMeasurementExpressionHelper {

    public static <
            M1 extends LinearMeasurement<?,?,M1>,
            M2 extends LinearMeasurement<?,?,M2>,
            Q extends Quantity<Q,U,M>,
            U extends Unit<Q,U,M>,
            M extends LinearMeasurement<Q,U,M>,
            B>
    B multiply(NamedBindingFactory<Q,U,M,B> factory, ObservableValue<M1> m1, ObservableValue<M2> m2, U resultUnit) {
        if (m1 == null || m2 == null) {
            return factory.create(BindingComputer.<M>nullBindingComputer());
        }
        else {
            final BindingComputer<M> computer = () -> {
                final M1 m1Value = m1.getValue();
                final M2 m2Value = m2.getValue();
                return m1Value == null || m2Value == null ? null : MeasurementOperations
                        .multiply(m1Value, m2Value, resultUnit);
            };
            return factory.create(computer, m1, m2);
        }

    }

    public static <
            M1 extends LinearMeasurement<?,?,M1>,
            M2 extends LinearMeasurement<?,?,M2>,
            Q extends Quantity<Q,U,M>,
            U extends Unit<Q,U,M>,
            M extends LinearMeasurement<Q,U,M>,
            B>
    B multiply(NamedBindingFactory<Q,U,M,B> factory, ObservableValue<M1> m1, M2 m2, U resultUnit) {
        if (m1 == null || m2 == null) {
            return factory.create(BindingComputer.<M>nullBindingComputer());
        }
        else {
            final BindingComputer<M> computer = () -> {
                final M1 m1Value = m1.getValue();
                return m1Value == null ? null : MeasurementOperations.multiply(m1Value, m2, resultUnit);
            };
            return factory.create(computer, m1);
        }

    }

    public static <
            M1 extends LinearMeasurement<?,?,M1>,
            Q extends Quantity<Q,U,M>,
            U extends Unit<Q,U,M>,
            M extends LinearMeasurement<Q,U,M>,
            B>
    B inverse(NamedBindingFactory<Q,U,M,B> factory, ObservableValue<M1> m1, U resultUnit) {
        if (m1 == null) {
            return factory.create(BindingComputer.<M>nullBindingComputer());
        }
        else {
            final BindingComputer<M> computer = () -> {
                final M1 m1Value = m1.getValue();
                return m1Value == null ? null : MeasurementOperations.inverse(m1Value, resultUnit);
            };
            return factory.create(computer, m1);
        }
    }

    public static <
            M1 extends LinearMeasurement<?,?,M1>,
            Q2 extends Quantity<Q2,U2,M2>,
            U2 extends Unit<Q2,U2, M2>,
            M2 extends LinearMeasurement<Q2,U2,M2>,
            B>
    B square(NamedBindingFactory<Q2,U2,M2,B> factory, ObservableValue<M1> m1, U2 resultUnit) {
        if (m1 == null) {
            return factory.create(BindingComputer.<M2>nullBindingComputer());
        }
        else {
            final BindingComputer<M2> computer = () -> {
                final M1 m1Value = m1.getValue();
                return m1Value == null ? null : MeasurementOperations.multiply(m1Value, m1Value, resultUnit);
            };
            return factory.create(computer, m1);
        }

    }



    public static <
            M1 extends LinearMeasurement<?,?,M1>,
            M2 extends LinearMeasurement<?,?,M2>,
            Q3 extends Quantity<Q3,U3,M3>,
            U3 extends Unit<Q3,U3,M3>,
            M3 extends LinearMeasurement<Q3,U3,M3>,
            B>
    B divide(NamedBindingFactory<Q3,U3,M3,B> factory, ObservableValue<M1> m1, ObservableValue<M2> m2, U3 resultUnit) {
        if (m1 == null || m2 == null) {
            return factory.create(BindingComputer.<M3>nullBindingComputer());
        }
        else {
            final BindingComputer<M3> computer = () -> {
                final M1 m1Value = m1.getValue();
                final M2 m2Value = m2.getValue();
                return m1Value == null || m2Value == null ? null : MeasurementOperations.divide(m1Value, m2Value, resultUnit);
            };
            return factory.create(computer, m1, m2);
        }

    }

    public static <
            M1 extends LinearMeasurement<?,?,M1>,
            M2 extends LinearMeasurement<?,?,M2>,
            Q3 extends Quantity<Q3,U3,M3>,
            U3 extends Unit<Q3,U3,M3>,
            M3 extends LinearMeasurement<Q3,U3,M3>,
            B>
    B divide(NamedBindingFactory<Q3,U3,M3,B> factory, ObservableValue<M1> m1, M2 m2, U3 resultUnit) {
        if (m1 == null || m2 == null) {
            return factory.create(BindingComputer.<M3>nullBindingComputer());
        }
        else {
            final BindingComputer<M3> computer = () -> {
                final M1 m1Value = m1.getValue();
                return m1Value == null ? null : MeasurementOperations.divide(m1Value, m2, resultUnit);
            };
            return factory.create(computer, m1);
        }

    }

    public static<Q extends Quantity<Q,U,M>, U extends Unit<Q,U, M>,M extends LinearMeasurement<Q,U,M>, B> B abs(NamedBindingFactory<Q,U,M,B> factory, ObservableValue<M> measurement) {
        if (measurement == null) {
            return factory.create(BindingComputer.<M>nullBindingComputer());
        } else {
            final BindingComputer<M> computer = () -> {
                final M m = measurement.getValue();
                return m == null ? null : m.abs();
            };
            return factory.create(computer, measurement);
        }
    }

    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U, M>,M extends LinearMeasurement<Q,U,M>, B> B negate(NamedBindingFactory<Q,U,M,B> factory, ObservableValue<M> measurement) {
        if (measurement == null) {
            return factory.create(BindingComputer.<M>nullBindingComputer());
        } else {
            final BindingComputer<M> computer = () -> {
                final M m = measurement.getValue();
                return m == null ? null : m.negate();
            };
            return factory.create(computer, measurement);
        }
    }

    public static DeltaTemperatureBinding difference(Temperature temperature1, ObservableValue<Temperature> temperature2) {
        if (temperature1 == null || temperature2 == null) {
            return new DeltaTemperatureBinding(BindingComputer.<DeltaTemperature>nullBindingComputer());
        }
        else {
            final BindingComputer<DeltaTemperature> computer = () -> {
                final Temperature t2 = temperature2.getValue();
                return t2 == null ? null : temperature1.subtract(t2);
            };
            return new DeltaTemperatureBinding(computer, temperature2);
        }
    }

    public static DeltaTemperatureBinding difference(ObservableValue<Temperature> temperature1, ObservableValue<Temperature> temperature2) {
        if (temperature1 == null || temperature2 == null) {
            return new DeltaTemperatureBinding(BindingComputer.<DeltaTemperature>nullBindingComputer());
        }
        else {
            final BindingComputer<DeltaTemperature> computer = () -> {
                final Temperature t1 = temperature1.getValue();
                final Temperature t2 = temperature2.getValue();
                return t1 == null || t2 == null ? null : t1.subtract(t2);
            };
            return new DeltaTemperatureBinding(computer, temperature1, temperature2);
        }
    }

    public static DeltaTemperatureBinding difference(ObservableValue<Temperature> temperature1, Temperature temperature2) {
        if (temperature1 == null || temperature2 == null) {
            return new DeltaTemperatureBinding(BindingComputer.<DeltaTemperature>nullBindingComputer());
        }
        else {
            final BindingComputer<DeltaTemperature> computer = () -> {
                final Temperature t1 = temperature1.getValue();
                return t1 == null ? null : t1.subtract(temperature2);
            };
            return new DeltaTemperatureBinding(computer, temperature1);
        }
    }

    public static TemperatureBinding add(ObservableValue<Temperature> temperature, DeltaTemperature delta) {
        if (temperature == null || delta == null) {
            return new TemperatureBinding(BindingComputer.<Temperature>nullBindingComputer());
        }
        else {
            final BindingComputer<Temperature> computer = () -> {
                final Temperature t = temperature.getValue();
                return t == null ? null : t.add(delta);
            };
            return new TemperatureBinding(computer, temperature);
        }
    }

    public static TemperatureBinding add(Temperature temperature, ObservableValue<DeltaTemperature> delta) {
        if (temperature == null || delta == null) {
            return new TemperatureBinding(BindingComputer.<Temperature>nullBindingComputer());
        }
        else {
            final BindingComputer<Temperature> computer = () -> {
                final DeltaTemperature dt = delta.getValue();
                return dt == null ? null : temperature.add(dt);
            };
            return new TemperatureBinding(computer, delta);
        }
    }

    public static TemperatureBinding add(ObservableValue<Temperature> temperature, ObservableValue<DeltaTemperature> delta) {
        if (temperature == null || delta == null) {
            return new TemperatureBinding(BindingComputer.<Temperature>nullBindingComputer());
        }
        else {
            final BindingComputer<Temperature> computer = () -> {
                final Temperature t = temperature.getValue();
                final DeltaTemperature dt = delta.getValue();
                return t==null || dt == null ? null : t.add(dt);
            };
            return new TemperatureBinding(computer, temperature, delta);
        }
    }

    public static TemperatureBinding subtract(ObservableValue<Temperature> temperature, DeltaTemperature delta) {
        if (temperature == null || delta == null) {
            return new TemperatureBinding(BindingComputer.<Temperature>nullBindingComputer());
        }
        else {
            final BindingComputer<Temperature> computer = () -> {
                final Temperature t = temperature.getValue();
                return t == null ? null : t.subtract(delta);
            };
            return new TemperatureBinding(computer, temperature);
        }
    }

    public static TemperatureBinding subtract(Temperature temperature, ObservableValue<DeltaTemperature> delta) {
        if (temperature == null || delta == null) {
            return new TemperatureBinding(BindingComputer.<Temperature>nullBindingComputer());
        }
        else {
            final BindingComputer<Temperature> computer = () -> {
                final DeltaTemperature dt = delta.getValue();
                return dt == null ? null : temperature.subtract(dt);
            };
            return new TemperatureBinding(computer, delta);
        }
    }

    public static TemperatureBinding subtract(ObservableValue<Temperature> temperature, ObservableValue<DeltaTemperature> delta) {
        if (temperature == null || delta == null) {
            return new TemperatureBinding(BindingComputer.<Temperature>nullBindingComputer());
        }
        else {
            final BindingComputer<Temperature> computer = () -> {
                final Temperature t = temperature.getValue();
                final DeltaTemperature dt = delta.getValue();
                return t==null || dt == null ? null : t.subtract(dt);
            };
            return new TemperatureBinding(computer, temperature, delta);
        }
    }

    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U,M>,M extends LinearMeasurement<Q,U,M>, B> B add(NamedBindingFactory<Q,U,M,B> factory, ObservableValue<M> m1, M m2) {
        if (m1 == null || m2 == null) {
            return factory.create(BindingComputer.<M>nullBindingComputer());
        }
        else {
            final BindingComputer<M> computer = () -> {
                final M m1Value = m1.getValue();
                return m1Value == null ? null : m1Value.add(m2);
            };
            return factory.create(computer, m1);
        }
    }


    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U, M>,M extends LinearMeasurement<Q,U,M>, B> B add(NamedBindingFactory<Q,U,M,B> factory, ObservableValue<M> m1, ObservableValue<M> m2) {
        final BindingComputer<M> computer;
        if (m1 == null || m2 == null) {
            return factory.create(BindingComputer.<M>nullBindingComputer());
        }
        else {
            computer = () -> {
                final M m1Value = m1.getValue();
                final M m2Value = m2.getValue();
                return m1Value == null  || m2Value == null ? null : m1Value.add(m2Value);
            };
            return factory.create(computer, m1, m2);
        }
    }

    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U, M>,M extends LinearMeasurement<Q,U,M>, B> B subtract(NamedBindingFactory<Q,U,M,B> factory, ObservableValue<M> m1, M m2) {
        if (m1 == null || m2 == null) {
            return factory.create(BindingComputer.<M>nullBindingComputer());
        }
        else {
            final BindingComputer<M> computer = () -> {
                final M m1Value = m1.getValue();
                return m1Value == null ? null : m1Value.subtract(m2);
            };
            return factory.create(computer, m1);
        }
    }


    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U, M>,M extends LinearMeasurement<Q,U,M>, B> B subtract(NamedBindingFactory<Q,U,M,B> factory, ObservableValue<M> m1, ObservableValue<M> m2) {
        final BindingComputer<M> computer;
        if (m1 == null || m2 == null) {
            return factory.create(BindingComputer.<M>nullBindingComputer());
        }
        else {
            computer = () -> {
                final M m1Value = m1.getValue();
                final M m2Value = m2.getValue();
                return m1Value == null  || m2Value == null ? null : m1Value.subtract(m2Value);
            };
            return factory.create(computer, m1, m2);
        }
    }

    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U, M>,M extends Measurement<Q,U,M>, B> B convert(NamedBindingFactory<Q,U,M,B> factory, ObservableValue<M> m1, U unit) {
        if (unit == null || m1 == null) {
            return factory.create(BindingComputer.<M>nullBindingComputer());
        }
        else   {
            final BindingComputer<M> computer = () -> {
                final M m = m1.getValue();
                return m == null ? null : m.convert(unit);
            };
            return factory.create(computer, m1);
        }
    }

    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U, M>,M extends Measurement<Q,U,M>, B> B max(NamedBindingFactory<Q,U,M,B> factory, ObservableValue<M> m1, ObservableValue<M> m2) {
        final BindingComputer<M> computer;
        if (m1 == null || m2 == null) {
            return factory.create(BindingComputer.<M>nullBindingComputer());
        }
        else {
            computer = () -> {
                final M m1Value = m1.getValue();
                final M m2Value = m2.getValue();
                return m1Value == null || m2Value == null ? null : m1Value.max(m2Value);
            };
            return factory.create(computer,m1,m2);
        }
    }

    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U, M>,M extends Measurement<Q,U,M>, B> B min(NamedBindingFactory<Q,U,M,B> factory, ObservableValue<M> m1, ObservableValue<M> m2) {
        final BindingComputer<M> computer;
        if (m1 == null || m2 == null) {
            return factory.create(BindingComputer.<M>nullBindingComputer());
        }
        else {
            computer = () -> {
                final M m1Value = m1.getValue();
                final M m2Value = m2.getValue();
                return m1Value == null || m2Value == null ? null : m1Value.min(m2Value);
            };
            return factory.create(computer,m1,m2);
        }
    }

    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U, M>,M extends LinearMeasurement<Q,U,M>, B> B scale(NamedBindingFactory<Q,U,M,B> factory, ObservableValue<M> m1, double factor) {
        final BindingComputer<M> computer;
        if (m1 == null) {
            return factory.create(BindingComputer.<M>nullBindingComputer());
        }
        else {
            computer = () -> {
                final M m1Value = m1.getValue();
                return m1Value == null ? null : m1Value.scale(factor);
            };
            return factory.create(computer,m1);
        }
    }

    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U, M>,M extends LinearMeasurement<Q,U,M>, B> B scale(NamedBindingFactory<Q,U,M,B> factory, ObservableValue<M> m1, ObservableDoubleValue factor) {
        final BindingComputer<M> computer;
        if (m1 == null || factor == null) {
            return factory.create(BindingComputer.<M>nullBindingComputer());
        }
        else {
            computer = () -> {
                final M m1Value = m1.getValue();
                return m1Value == null ? null : m1Value.scale(factor.doubleValue());
            };
            return factory.create(computer,m1, factor);
        }
    }


    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U, M>,M extends LinearMeasurement<Q,U,M>, B> B inAdaptedUnit(NamedBindingFactory<Q,U,M,B> factory, ObservableValue<M> m1) {
        final BindingComputer<M> computer;
        if (m1 == null) {
            return factory.create(BindingComputer.<M>nullBindingComputer());
        }
        else {
            computer = () -> UnitUtils.adaptUnit(m1.getValue());
            return factory.create(computer, m1);
        }
    }
    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U, M>,M extends LinearMeasurement<Q,U,M>, B> B inAdaptedUnit(NamedBindingFactory<Q,U,M,B> factory, ObservableValue<M> m1, U unitForZero) {
        final BindingComputer<M> computer;
        if (m1 == null) {
            return factory.create(BindingComputer.<M>nullBindingComputer());
        }
        else {
            computer = () -> UnitUtils.adaptUnit(m1.getValue(), unitForZero);
            return factory.create(computer, m1);
        }
    }
    public static <Q extends Quantity<Q,U,M>, U extends Unit<Q,U, M>,M extends LinearMeasurement<Q,U,M>, B> B inAdaptedUnit(NamedBindingFactory<Q,U,M,B> factory, ObservableValue<M> m1, ObservableValue<U> unitForZero) {
        if (unitForZero == null) {
            return inAdaptedUnit(factory, m1);
        }
        final BindingComputer<M> computer;
        if (m1 == null) {
            return factory.create(BindingComputer.<M>nullBindingComputer());
        }
        else {
            computer = () -> UnitUtils.adaptUnit(m1.getValue(), unitForZero.getValue());
            return factory.create(computer, m1, unitForZero);
        }
    }

}
