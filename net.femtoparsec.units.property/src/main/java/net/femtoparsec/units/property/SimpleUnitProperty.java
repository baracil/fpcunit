package net.femtoparsec.units.property;

import net.femtoparsec.units.api.Unit;

/**
 * @author Bastien Aracil
 */
public class SimpleUnitProperty<U extends Unit<?,U,?>> extends UnitPropertyBase<U> {
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
    public SimpleUnitProperty() {
        this(DEFAULT_BEAN, DEFAULT_NAME);
    }

    /**
     * The constructor of {@code ObjectProperty}
     *
     * @param initialValue
     *            the initial value of the wrapped value
     */
    public SimpleUnitProperty(U initialValue) {
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
    public SimpleUnitProperty(Object bean, String name) {
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
    public SimpleUnitProperty(Object bean, String name, U initialValue) {
        super(initialValue);
        this.bean = bean;
        this.name = (name == null) ? DEFAULT_NAME : name;
    }



}
