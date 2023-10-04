package net.femtoparsec.units.property;

import net.femtoparsec.units.api.Measurement;
import net.femtoparsec.units.api.Unit;
import net.femtoparsec.units.api.Quantity;

/**
 * @author Bastien Aracil
 */
public class SimpleMeasurementProperty<Q extends Quantity<Q,U,M>, U extends Unit<Q,U,M>, M extends Measurement<Q,U,M>> extends MeasurementPropertyBase<Q,U,M> {
    private static final Object DEFAULT_BEAN = null;
    private static final String DEFAULT_NAME = "";

    private final Object bean;
    private final String name;

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getBean() {
        return bean;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * The constructor of {@code ObjectProperty}
     */
    public SimpleMeasurementProperty() {
        this(DEFAULT_BEAN, DEFAULT_NAME);
    }

    /**
     * The constructor of {@code ObjectProperty}
     *
     * @param initialValue
     *            the initial value of the wrapped value
     */
    public SimpleMeasurementProperty(M initialValue) {
        this(DEFAULT_BEAN, DEFAULT_NAME, initialValue);
    }

    /**
     * The constructor of {@code ObjectProperty}
     *
     * @param bean
     *            the bean of this {@code ObjectProperty}
     * @param name
     *            the name of this {@code ObjectProperty}
     */
    public SimpleMeasurementProperty(Object bean, String name) {
        this.bean = bean;
        this.name = (name == null) ? DEFAULT_NAME : name;
    }

    /**
     * The constructor of {@code ObjectProperty}
     *
     * @param bean
     *            the bean of this {@code ObjectProperty}
     * @param name
     *            the name of this {@code ObjectProperty}
     * @param initialValue
     *            the initial value of the wrapped value
     */
    public SimpleMeasurementProperty(Object bean, String name, M initialValue) {
        super(initialValue);
        this.bean = bean;
        this.name = (name == null) ? DEFAULT_NAME : name;
    }



}
