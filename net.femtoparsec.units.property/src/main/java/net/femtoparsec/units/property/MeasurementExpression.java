package net.femtoparsec.units.property;

import net.femtoparsec.units.api.Measurement;
import net.femtoparsec.units.api.Unit;
import net.femtoparsec.units.api.UnitSystem;
import net.femtoparsec.units.api.Quantity;
import net.femtoparsec.units.property.helper.StringFormatter;
import javafx.beans.binding.*;
import javafx.beans.value.ObservableObjectValue;
import javafx.beans.value.ObservableValue;

import java.util.Locale;
import java.util.function.Function;

/**
 * @author Bastien Aracil
 */
public abstract class MeasurementExpression<Q extends Quantity<Q,U,M>, U extends Unit<Q,U,M>,M extends Measurement<Q,U,M>> implements ObservableObjectValue<M> {

    @Override
    public M getValue() {
        return this.get();
    }

    public DoubleBinding inSI(double defaultValue) {
        return MeasurementBindings.inSI(this, defaultValue);
    }

    public ObjectBinding<Double> inSI() {
        return MeasurementBindings.inSI(this);
    }

    public DoubleBinding inUnit(U unit, double defaultValue) {
        return MeasurementBindings.inUnit(this, unit, defaultValue);
    }

    public MeasurementBinding<Q,U,M> inAdaptedUnit() {
        return MeasurementBindings.inAdaptedUnit(this);
    }
    public MeasurementBinding<Q,U,M> inAdaptedUnit(U unitForZero) {
        return MeasurementBindings.inAdaptedUnit(this, unitForZero);
    }
    public MeasurementBinding<Q,U,M> inAdaptedUnit(UnitSystem unitSystem) {
        return MeasurementBindings.inAdaptedUnit(this, unitSystem);
    }
    public MeasurementBinding<Q,U,M> inAdaptedUnit(ObservableValue<U> unitForZero) {
        return MeasurementBindings.inAdaptedUnit(this, unitForZero);
    }

    public ObjectBinding<Double> inUnit(U unit) {
        return MeasurementBindings.inUnit(this, unit);
    }

    public DoubleBinding inUnit(ObservableObjectValue<U> unit, double defaultValue) {
        return MeasurementBindings.inUnit(this, unit, defaultValue);
    }

    public ObjectBinding<Double> inUnit(ObservableObjectValue<U> unit) {
        return MeasurementBindings.inUnit(this, unit);
    }

    public MeasurementBinding<Q,U,M> convert(U unit) {
        return new SingleSameMeasurementBinding<Q,U,M>(this) {
            @Override
            protected M computeValue(M measurement) {
                return measurement == null ? null : measurement.convert(unit);
            }
        };
    }

    public MeasurementBinding<Q,U,M> max(ObservableValue<M> other) {
        return new DoubleSameMeasurementBinding<Q,U,M>(this, other) {
            @Override
            protected M computeValue(M measurement, M measurement1) {
                return measurement == null || measurement1 == null ? null : measurement.max(measurement1);
            }
        };
    }

    public MeasurementBinding<Q,U,M> min(ObservableValue<M> other) {
        return new DoubleSameMeasurementBinding<Q,U,M>(this, other) {
            @Override
            protected M computeValue(M measurement, M measurement1) {
                return measurement == null || measurement1 == null ? null : measurement.min(measurement1);
            }
        };
    }

    /**
     * Creates a new {@code BooleanExpression} that holds {@code true} if this and
     * another {@link javafx.beans.value.ObservableObjectValue} are equal.
     *
     * @param other
     *            the other {@code ObservableObjectValue}
     * @return the new {@code BooleanExpression}
     * @throws NullPointerException
     *             if {@code other} is {@code null}
     */
    public BooleanBinding isEqualTo(final ObservableObjectValue<?> other) {
        return Bindings.equal(this, other);
    }

    /**
     * Creates a new {@code BooleanExpression} that holds {@code true} if this
     * {@code ObjectExpression} is equal to a constant value.
     *
     * @param other
     *            the constant value
     * @return the new {@code BooleanExpression}
     */
    public BooleanBinding isEqualTo(final Object other) {
        return Bindings.equal(this, other);
    }

    /**
     * Creates a new {@code BooleanExpression} that holds {@code true} if this and
     * another {@link javafx.beans.value.ObservableObjectValue} are not equal.
     *
     * @param other
     *            the other {@code ObservableObjectValue}
     * @return the new {@code BooleanExpression}
     * @throws NullPointerException
     *             if {@code other} is {@code null}
     */
    public BooleanBinding isNotEqualTo(final ObservableObjectValue<?> other) {
        return Bindings.notEqual(this, other);
    }

    /**
     * Creates a new {@code BooleanExpression} that holds {@code true} if this
     * {@code ObjectExpression} is not equal to a constant value.
     *
     * @param other
     *            the constant value
     * @return the new {@code BooleanExpression}
     */
    public BooleanBinding isNotEqualTo(final Object other) {
        return Bindings.notEqual(this, other);
    }

    /**
     * Creates a new {@link BooleanBinding} that holds {@code true} if this
     * {@code ObjectExpression} is {@code null}.
     *
     * @return the new {@code BooleanBinding}
     */
    public BooleanBinding isNull() {
        return Bindings.isNull(this);
    }

    /**
     * Creates a new {@link BooleanBinding} that holds {@code true} if this
     * {@code ObjectExpression} is not {@code null}.
     *
     * @return the new {@code BooleanBinding}
     */
    public BooleanBinding isNotNull() {
        return Bindings.isNotNull(this);
    }

    /**
     * Creates a {@link javafx.beans.binding.StringBinding} that holds the value
     * of this {@code ObjectExpression} turned into a {@code String}. If the
     * value of this {@code ObjectExpression} changes, the value of the
     * {@code StringBinding} will be updated automatically.
     *
     * @return the new {@code StringBinding}
     * @since JavaFX 8.0
     */
    public StringBinding asString() {
        return (StringBinding) StringFormatter.convert(this);
    }

    /**
     * Creates a {@link javafx.beans.binding.StringBinding} that holds the value
     * of the {@code ObjectExpression} turned into a {@code String}. If the
     * value of this {@code ObjectExpression} changes, the value of the
     * {@code StringBinding} will be updated automatically.
     * <p>
     * The result is formatted according to the formatting {@code String}. See
     * {@code java.util.Formatter} for formatting rules.
     *
     * @param format
     *            the formatting {@code String}
     * @return the new {@code StringBinding}
     * @since JavaFX 8.0
     */
    public StringBinding asString(String format) {
        return (StringBinding) Bindings.format(format, this);
    }

    public StringBinding asPrettyString() {
        return MeasurementBindings.asPrettyString(this);
    }

    public StringBinding asPrettyString(String format) {
        return MeasurementBindings.asPrettyString(this, format);
    }

    public StringBinding asPrettyString(Function<Double,String> converter) {
        return MeasurementBindings.asPrettyString(this, converter);
    }

    public StringBinding asPrettyString(ObservableObjectValue<Function<Double,String>> converter) {
        return MeasurementBindings.asPrettyString(this, converter);
    }

    /**
     * Creates a {@link javafx.beans.binding.StringBinding} that holds the value
     * of the {@code NumberExpression} turned into a {@code String}. If the
     * value of this {@code NumberExpression} changes, the value of the
     * {@code StringBinding} will be updated automatically.
     * <p>
     * The result is formatted according to the formatting {@code String} and
     * the passed in {@code Locale}. See {@code java.util.Formatter} for
     * formatting rules. See {@code java.util.Locale} for details on
     * {@code Locale}.
     *
     * @param format
     *            the formatting {@code String}
     * @return the new {@code StringBinding}
     * @since JavaFX 8.0
     */
    public StringBinding asString(Locale locale, String format) {
        return (StringBinding) Bindings.format(locale, format, this);
    }
}
