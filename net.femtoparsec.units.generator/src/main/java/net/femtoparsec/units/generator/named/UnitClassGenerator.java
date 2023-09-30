package net.femtoparsec.units.generator.named;

import net.femtoparsec.units.generator.GenerationPath;
import net.femtoparsec.units.generator.bean.Quantity;
import lombok.NonNull;

import java.nio.file.Path;

/**
 * @author Bastien Aracil
 */
public class UnitClassGenerator extends AbstractClassGenerator {

    public UnitClassGenerator(Quantity quantity, GenerationPath generationPath) {
        super(quantity, generationPath);
    }

    @Override
    protected String getClassName(Quantity quantity) {
        return quantity.getUnitName();
    }

    @Override
    protected String getTemplateName() {
        return "net/femtoparsec/units/generator/named/unit_class.vt";
    }

    @Override
    protected Path getOutputPath(@NonNull String filename) {
        return getBasePackagePath().resolve("named")
                                   .resolve("unit")
                                   .resolve(filename);

    }
}
