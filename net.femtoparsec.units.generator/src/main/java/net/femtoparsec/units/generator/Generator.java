package net.femtoparsec.units.generator;

import lombok.NonNull;
import net.femtoparsec.units.generator.bean.Quantity;

import java.io.IOException;
import java.util.List;

/**
 * @author Bastien Aracil
 */
public interface Generator {

    void generate(@NonNull List<Quantity> quantities, @NonNull GenerationPath generationPath) throws IOException;

}
