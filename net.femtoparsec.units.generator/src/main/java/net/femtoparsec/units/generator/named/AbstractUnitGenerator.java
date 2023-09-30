package net.femtoparsec.units.generator.named;

import net.femtoparsec.units.generator.StringUtils;
import net.femtoparsec.units.generator.UnitGenerator;
import org.apache.velocity.VelocityContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Bastien Aracil
 */
public abstract class AbstractUnitGenerator implements UnitGenerator {

    protected VelocityContext createContext() {
        VelocityContext context = new VelocityContext();
        context.put("StringUtils", StringUtils.class);
        return context;
    }

    protected void prepareOutputDirectory(Path outputPath) {
        try {
            if (!Files.isDirectory(outputPath.getParent())) {
                Files.createDirectories(outputPath.getParent());
            }
            if (!Files.isDirectory(outputPath.getParent())) {
                throw new IllegalArgumentException("invalid output path "+outputPath);
            }

        } catch (IOException e) {
            throw new IllegalArgumentException("invalid output path "+outputPath, e);
        }
    }


}
