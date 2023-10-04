package net.femtoparsec.units.property;

import net.femtoparsec.units.api.LinearMeasurement;
import net.femtoparsec.units.api.Unit;
import net.femtoparsec.units.api.Quantity;
import net.femtoparsec.units.property.helper.ExpressionHelper;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.lang.ref.WeakReference;
import java.util.Objects;

/**
 * @author Bastien Aracil
 */
public abstract class StandardMeasurementPropertyBase<Q extends Quantity<Q,U,M>, U extends Unit<Q,U,M>, M extends LinearMeasurement<Q,U,M>> extends StandardMeasurementProperty<Q,U,M> {

    private M value;
    private ObservableValue<? extends M> observable = null;
    private InvalidationListener listener = null;
    private boolean valid = true;
    private ExpressionHelper<M> helper = null;

    /**
     * The constructor of the {@code StringPropertyBase}.
     */
    public StandardMeasurementPropertyBase() {
    }

    /**
     * The constructor of the {@code StringPropertyBase}.
     *
     * @param initialValue
     *            the initial value of the wrapped {@code String}
     */
    public StandardMeasurementPropertyBase(M initialValue) {
        this.value = initialValue;
    }

    @Override
    public void addListener(InvalidationListener listener) {
        helper = ExpressionHelper.addListener(helper, this, listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        helper = ExpressionHelper.removeListener(helper, listener);
    }

    @Override
    public void addListener(ChangeListener<? super M> listener) {
        helper = ExpressionHelper.addListener(helper, this, listener);
    }

    @Override
    public void removeListener(ChangeListener<? super M> listener) {
        helper = ExpressionHelper.removeListener(helper, listener);
    }

    /**
     * <p>Sends notifications to all attached
     * {@link javafx.beans.InvalidationListener InvalidationListeners} and
     * {@link javafx.beans.value.ChangeListener ChangeListeners}.</p>
     *
     * <p>This method is called when the value is changed, either manually by
     * calling {@link #set} or in case of a bound property, if the
     * binding becomes invalid.</p>
     */
    protected void fireValueChangedEvent() {
        ExpressionHelper.fireValueChangedEvent(helper);
    }

    private void markInvalid() {
        if (valid) {
            valid = false;
            invalidated();
            fireValueChangedEvent();
        }
    }

    /**
     * <p>The method {@code invalidated()} can be overridden to receive
     * invalidation notifications. This is the preferred option in
     * {@code Objects} defining the property, because it requires less memory.</p>
     *
     * <p>The default implementation is empty.</p>
     */
    protected void invalidated() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public M get() {
        valid = true;
        return observable == null ? value : observable.getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(M newValue) {
        if (isBound()) {
            throw new RuntimeException((getBean() != null && getName() != null ?
                    getBean().getClass().getSimpleName() + "." + getName() + " : ": "") + "A bound value cannot be set.");
        }
        if (!Objects.equals(value, newValue)) {
            value = newValue;
            markInvalid();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBound() {
        return observable != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bind(ObservableValue<? extends M> newObservable) {
        if (newObservable == null) {
            throw new NullPointerException("Cannot bind to null");
        }
        if (!newObservable.equals(observable)) {
            unbind();
            observable = newObservable;
            if (listener == null) {
                listener = new Listener<>(this);
            }
            observable.addListener(listener);
            markInvalid();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unbind() {
        if (observable != null) {
            value = observable.getValue();
            observable.removeListener(listener);
            observable = null;
        }
    }

    /**
     * Returns a string representation of this {@code StringPropertyBase} object.
     * @return a string representation of this {@code StringPropertyBase} object.
     */
    @Override
    public String toString() {
        final Object bean = getBean();
        final String name = getName();
        final StringBuilder result = new StringBuilder("StringProperty [");
        if (bean != null) {
            result.append("bean: ").append(bean).append(", ");
        }
        if ((name != null) && (!name.isEmpty())) {
            result.append("name: ").append(name).append(", ");
        }
        if (isBound()) {
            result.append("bound, ");
            if (valid) {
                result.append("value: ").append(get());
            } else {
                result.append("invalid");
            }
        } else {
            result.append("value: ").append(get());
        }
        result.append("]");
        return result.toString();
    }

    private static class Listener<Q extends Quantity<Q,U,M>, U extends Unit<Q,U,M>, M extends LinearMeasurement<Q,U,M>> implements InvalidationListener {

        private final WeakReference<StandardMeasurementPropertyBase<Q,U,M>> wref;

        public Listener(StandardMeasurementPropertyBase<Q,U,M> ref) {
            this.wref = new WeakReference<>(ref);
        }

        @Override
        public void invalidated(Observable observable) {
            StandardMeasurementPropertyBase<Q,U,M> ref = wref.get();
            if (ref == null) {
                observable.removeListener(this);
            } else {
                ref.markInvalid();
            }
        }
    }

}
