package net.femtoparsec.units.generator.bean;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

/**
 * @author Bastien Aracil
 */
@Value
@Builder(builderClassName = "Builder")
public class Unit {

    @NonNull String instanceName;
    @NonNull String unitName;
    boolean si;
    @NonNull String factor;
    @NonNull String offset;

}
