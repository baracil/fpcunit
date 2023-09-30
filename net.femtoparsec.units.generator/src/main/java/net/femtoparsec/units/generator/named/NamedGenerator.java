package net.femtoparsec.units.generator.named;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.femtoparsec.units.generator.GenerationPath;
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
public class NamedGenerator implements UnitGenerator {

    public static NamedGenerator create(@NonNull Path generationSourcePath, @NonNull String basePackageName) throws IOException {
        final var generationPah = new GenerationPath(generationSourcePath,basePackageName);
        final var quantities = new JacksonQuantityReader().read();
        return new NamedGenerator(quantities,generationPah);
    }

    private final List<Quantity> quantities;
    private final GenerationPath generationPath;

    @Override
    public void generate() throws IOException {
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
