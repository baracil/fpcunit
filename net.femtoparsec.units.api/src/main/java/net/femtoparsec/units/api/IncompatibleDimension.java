package net.femtoparsec.units.api;

import lombok.Getter;

/**
 * @author Bastien Aracil
 */
@Getter
public class IncompatibleDimension extends FpcUnitException {

    private final Dimension dimension1;
    private final Dimension dimension2;

    public IncompatibleDimension(Dimension dimension1, Dimension dimension2) {
        super("The dimensions are incompatible : "+ dimension1.getEncoded()+" <> "+ dimension2.getEncoded());
        this.dimension1 = dimension1;
        this.dimension2 = dimension2;
    }

}
