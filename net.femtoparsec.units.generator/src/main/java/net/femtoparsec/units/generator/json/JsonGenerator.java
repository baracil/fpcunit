package net.femtoparsec.units.generator.json;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.femtoparsec.units.generator.GenerationPath;
import net.femtoparsec.units.generator.Generator;
import net.femtoparsec.units.generator.VelocityDumper;
import net.femtoparsec.units.generator.bean.Quantity;
import net.femtoparsec.units.generator.named.AbstractUnitGenerator;
import org.apache.velocity.VelocityContext;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class JsonGenerator implements Generator {

  @Override
  public void generate(@NonNull List<Quantity> quantities, @NonNull GenerationPath generationPath) throws IOException {
      new Execution(generationPath,quantities).generate();
  }

  public static final String CLASS_NAME = "UnitModule";

  @RequiredArgsConstructor
  private static class Execution extends AbstractUnitGenerator {

    private final VelocityDumper velocityDumper = new VelocityDumper("net/femtoparsec/units/generator/json/json_module.vt");

    private final GenerationPath generationPath;
    private final List<Quantity> quantities;

    private Path outputPath;

    @Override
    public void generate() {
      this.prepareOutputPath();
      this.prepareOutputDirectory();

      final VelocityContext context = createContext();
      context.put("className", CLASS_NAME);
      context.put("quantities", quantities);
      context.put("generationPath", generationPath);

      velocityDumper.dump(outputPath, context);
    }

    private void prepareOutputDirectory() {
      this.prepareOutputDirectory(outputPath);
    }

    private void prepareOutputPath() {
      this.outputPath = generationPath.getBasePackagePath().resolve("json").resolve(CLASS_NAME + ".java");
    }


  }

}
