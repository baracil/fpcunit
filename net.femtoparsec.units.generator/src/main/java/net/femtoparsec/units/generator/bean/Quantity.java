package net.femtoparsec.units.generator.bean;

import lombok.*;
import net.femtoparsec.units.api.Dimension;

import java.util.List;


/**
 * @author Bastien Aracil
 */
@Builder(builderClassName = "Builder")
@EqualsAndHashCode(of = "name")
@Getter
@ToString(exclude = {"operations","methods"})
public class Quantity {

    @NonNull
    private final String name;

    @NonNull
    private final Dimension dimension;

    @NonNull
    private final String quantityName;

    @NonNull
    private final String unitName;

    @NonNull
    private final String measurementName;

    private final boolean standard;

    @NonNull
    private final Unit siUnit;

    @NonNull
    private final List<Unit> units;

    @NonNull
    private final List<Constant> constants;

    @NonNull
    @Setter
    private List<Operation> operations;

    @NonNull
    @Setter
    private List<Method> methods;



}
