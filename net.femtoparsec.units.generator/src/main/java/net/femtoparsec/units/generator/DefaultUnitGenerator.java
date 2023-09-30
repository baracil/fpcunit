package net.femtoparsec.units.generator;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.femtoparsec.units.generator.bean.Quantity;

import java.util.List;

/**
 * @author Bastien Aracil
 */
@RequiredArgsConstructor
public class DefaultUnitGenerator {

    @NonNull
    private final List<Quantity> quantities;


}
