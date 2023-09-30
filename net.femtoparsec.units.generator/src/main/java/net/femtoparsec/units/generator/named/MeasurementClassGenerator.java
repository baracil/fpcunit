package net.femtoparsec.units.generator.named;

import net.femtoparsec.units.generator.GenerationPath;
import net.femtoparsec.units.generator.bean.Quantity;
import lombok.NonNull;

import java.nio.file.Path;

/**
 * @author Bastien Aracil
 */
public class MeasurementClassGenerator extends AbstractClassGenerator {

    public MeasurementClassGenerator(Quantity quantity, GenerationPath generationPath) {
        super(quantity, generationPath);
    }

    @Override
    protected String getClassName(Quantity quantity) {
        return quantity.getMeasurementName();
    }

    @Override
    protected String getTemplateName() {
        return "net/femtoparsec/units/generator/named/measurement_class.vt";
    }

    @Override
    protected Path getOutputPath(@NonNull String filename) {
        return getBasePackagePath().resolve("named")
                                   .resolve("measurement")
                                   .resolve(filename);
    }
}
