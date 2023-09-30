package net.femtoparsec.units.generator.hibernate;

import lombok.NonNull;
import net.femtoparsec.units.generator.GenerationPath;
import net.femtoparsec.units.generator.bean.Quantity;
import net.femtoparsec.units.generator.named.AbstractClassGenerator;

import java.nio.file.Path;

/**
 * @author Bastien Aracil
 */
public class HibernateClassGenerator extends AbstractClassGenerator {

    public HibernateClassGenerator(Quantity quantity, GenerationPath generationPath) {
        super(quantity, generationPath);
    }

    @Override
    protected String getClassName(Quantity quantity) {
        return quantity.getMeasurementName()+"Type";
    }

    @Override
    protected Path getOutputPath(@NonNull String filename) {
        return getBasePackagePath().resolve("hibernate")
                                   .resolve(filename);

    }

    @Override
    protected String getTemplateName() {
        return "net/femtoparsec/units/generator/hibernate/hibernate-class.vt";
    }
}
