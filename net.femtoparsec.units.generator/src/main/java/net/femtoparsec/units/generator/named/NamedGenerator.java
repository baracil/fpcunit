package net.femtoparsec.units.generator.named;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.femtoparsec.units.generator.GenerationPath;
import net.femtoparsec.units.generator.Generator;
import net.femtoparsec.units.generator.UnitGenerator;
import net.femtoparsec.units.generator.bean.Quantity;
import net.femtoparsec.units.generator.parsing.JacksonQuantityReader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * @author Bastien Aracil
 */
@RequiredArgsConstructor
public class NamedGenerator implements Generator {


    @Override
    public void generate(@NonNull List<Quantity> quantities, @NonNull GenerationPath generationPath) throws IOException {
        new GeneratorExecution(quantities,generationPath).generate();;
    }

    @RequiredArgsConstructor
    private static class GeneratorExecution {

        private final List<Quantity> quantities;
        private final GenerationPath generationPath;

        public void generate()  {
            new UnitsGenerator(quantities, generationPath).generate();
            new QuantitiesGenerator(quantities, generationPath).generate();
            quantities.forEach(this::generate);
        }

        private void generate(Quantity quantity) {
            new QuantiyClassGenerator(quantity, generationPath).generate();
            new UnitClassGenerator(quantity, generationPath).generate();
            new MeasurementClassGenerator(quantity, generationPath).generate();
        }
    }
}
