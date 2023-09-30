package net.femtoparsec.units.generator.bean;

import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author Bastien Aracil
 */
@RequiredArgsConstructor
@Builder(builderClassName = "Builder")
public class UnitsInformation {

    @NonNull
    private final List<Quantity> quantities;

    @NonNull
    private final List<Operation> operations;
}
