package net.femtoparsec.units.property.measurement;

import net.femtoparsec.units.named.measurement.Surface;
import net.femtoparsec.units.named.unit.SurfaceUnit;
import net.femtoparsec.units.property.NamedBindingFactory;
import net.femtoparsec.units.property.NamedStandardMeasurementBinding;
import net.femtoparsec.units.named.quantity.SurfaceQuantity;
import javafx.beans.Observable;


/**
 * @author Bastien Aracil
 */
public class SurfaceBinding extends NamedStandardMeasurementBinding<SurfaceQuantity, SurfaceUnit, Surface, SurfaceBinding> {

    public static final NamedBindingFactory<SurfaceQuantity, SurfaceUnit, Surface, SurfaceBinding> FACTORY = SurfaceBinding::new;

    public SurfaceBinding(BindingComputer<Surface> computer, Observable... dependencies) {
        super(computer, FACTORY);
        super.bind(dependencies);
    }


}
