package net.femtoparsec.units.generator.named;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.femtoparsec.units.generator.GenerationPath;
import net.femtoparsec.units.generator.VelocityDumper;
import net.femtoparsec.units.generator.bean.Quantity;
import org.apache.velocity.VelocityContext;

import java.nio.file.Path;
import java.util.List;

/**
 * @author Bastien Aracil
 */
@RequiredArgsConstructor
public class UnitsGenerator extends AbstractUnitGenerator {

    private final VelocityDumper velocityDumper = new VelocityDumper("/net/femtoparsec/units/generator/named/units.vt");

    @NonNull
    private final List<Quantity> quantities;

    @NonNull
    private final GenerationPath generationPath;

    private Path outputPath;

    @Override
    public void generate() {
        this.validateQuantities();

        this.prepareOutputPath();
        this.prepareOutputDirectory();

        final VelocityContext context = createContext();
        context.put("quantities", quantities);
        context.put("generationPath", generationPath);

        velocityDumper.dump(outputPath, context);
    }

    private void validateQuantities() {
        quantities.forEach(q -> new QuantityValidator(q).validate());
    }

    private void prepareOutputDirectory() {
        this.prepareOutputDirectory(outputPath);
    }

    private void prepareOutputPath() {
        this.outputPath = generationPath.getBasePackagePath().resolve("named").resolve("Units.java");
    }
}
