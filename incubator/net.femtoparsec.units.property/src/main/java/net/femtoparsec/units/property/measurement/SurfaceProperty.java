package net.femtoparsec.units.property.measurement;

import net.femtoparsec.units.named.measurement.Surface;
import net.femtoparsec.units.named.unit.SurfaceUnit;
import net.femtoparsec.units.property.NamedSimpleStandardMeasurementProperty;
import net.femtoparsec.units.named.quantity.SurfaceQuantity;

/**
 * @author Bastien Aracil
 */
public class SurfaceProperty extends NamedSimpleStandardMeasurementProperty<SurfaceQuantity, SurfaceUnit, Surface, SurfaceBinding> {

    public SurfaceProperty() {
        super(SurfaceBinding.FACTORY);
    }

    public SurfaceProperty(Object bean, String name) {
        super(bean, name, SurfaceBinding.FACTORY);
    }

    public SurfaceProperty(Object bean, String name, Surface initialValue) {
        super(bean, name, initialValue, SurfaceBinding.FACTORY);
    }

    public SurfaceProperty(Surface initialValue) {
        super(initialValue, SurfaceBinding.FACTORY);
    }


}
