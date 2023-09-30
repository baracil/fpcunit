package net.femtoparsec.units.generator.spring;

import net.femtoparsec.units.generator.GenerationPath;
import net.femtoparsec.units.generator.bean.Quantity;
import net.femtoparsec.units.generator.named.AbstractClassGenerator;
import lombok.NonNull;

import java.nio.file.Path;

/**
 * @author Bastien Aracil
 */
public class SpringClassGenerator extends AbstractClassGenerator {

    public SpringClassGenerator(Quantity quantity, GenerationPath generationPath) {
        super(quantity, generationPath);
    }

    @Override
    protected String getClassName(Quantity quantity) {
        return quantity.getMeasurementName()+"PropertyEditor";
    }

    @Override
    protected String getTemplateName() {
        return "net/femtoparsec/units/generator/spring/spring-class.vt";
    }

    @Override
    protected Path getOutputPath(@NonNull String filename) {
        return getBasePackagePath().resolve("spring")
                                   .resolve(filename);

    }
}
