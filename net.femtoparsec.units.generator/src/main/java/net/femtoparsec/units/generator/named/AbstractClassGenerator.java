package net.femtoparsec.units.generator.named;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.femtoparsec.units.generator.GenerationPath;
import net.femtoparsec.units.generator.VelocityDumper;
import net.femtoparsec.units.generator.bean.Quantity;
import org.apache.velocity.VelocityContext;

import java.nio.file.Path;

/**
 * @author Bastien Aracil
 */
@RequiredArgsConstructor
public abstract class AbstractClassGenerator extends AbstractUnitGenerator {

    @NonNull
    private final Quantity quantity;

    @NonNull
    private final GenerationPath generationPath;

    private VelocityDumper velocityDumper;

    private Path outputPath;

    @Override
    public void generate() {
        this.velocityDumper = new VelocityDumper(getTemplateName());
        this.prepareOutputPath();
        this.prepareOutputDirectory();

        final VelocityContext context = createContext();
        context.put("quantity", quantity);
        context.put("generationPath", generationPath);
        context.put("className", getClassName(quantity));

        velocityDumper.dump(outputPath, context);
    }

    private void prepareOutputPath() {
        final String fileName = getClassName(quantity)+".java";
        this.outputPath = getOutputPath(fileName);
//
//                generationPath.getBasePackagePath().resolve(getPackage()).resolve(getClassName(quantity)+".java");
    }

    private void prepareOutputDirectory() {
        prepareOutputDirectory(outputPath);
    }

    protected Path getBasePackagePath() {
        return generationPath.getBasePackagePath();
    }

    protected abstract String getClassName(Quantity quantity);

    protected abstract Path getOutputPath(@NonNull String filename);

    protected abstract String getTemplateName();
}
