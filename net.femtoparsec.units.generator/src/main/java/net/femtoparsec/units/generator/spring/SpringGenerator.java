package net.femtoparsec.units.generator.spring;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.femtoparsec.units.generator.GenerationPath;
import net.femtoparsec.units.generator.GenerationPathProvider;
import net.femtoparsec.units.generator.GenerationType;
import net.femtoparsec.units.generator.UnitGenerator;
import net.femtoparsec.units.generator.bean.Quantity;

import java.io.IOException;
import java.util.List;

/**
 * @author Bastien Aracil
 */
@RequiredArgsConstructor
public class SpringGenerator implements UnitGenerator {

    @NonNull
    private final List<Quantity> quantities;

    @NonNull
    private final GenerationPathProvider generationPathProvider;

    private GenerationPath classGenerationPath;

    private GenerationPath configurationGenerationPath;

    @Override
    public void generate() throws IOException {
        this.initializeGenerationPaths();

        this.generateConfiguration();
        quantities.forEach(this::generateClass);
    }

    private void initializeGenerationPaths() throws IOException {
        classGenerationPath = generationPathProvider.get(GenerationType.SPRING_CLASS);
        configurationGenerationPath = generationPathProvider.get(GenerationType.SPRING_CONFIGURATION);
    }

    private void generateClass(Quantity quantity) {
        new SpringClassGenerator(quantity, classGenerationPath).generate();
    }

    private void generateConfiguration() {
        new SpringConfigurationGenerator(quantities, configurationGenerationPath).generate();
    }
}
