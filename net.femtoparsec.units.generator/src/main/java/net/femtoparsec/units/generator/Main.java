package net.femtoparsec.units.generator;

import net.femtoparsec.units.generator.json.JsonGenerator;
import net.femtoparsec.units.generator.named.NamedGenerator;
import net.femtoparsec.units.generator.parsing.JacksonQuantityReader;

import java.io.IOException;
import java.nio.file.Path;

/**
 * @author bastien.a
 */
public class Main {

  public static void main(String[] args) throws IOException {
    if (args.length != 3) {
      throw new IllegalArgumentException("Need three arguments for the generator : type, output directory, base package name");
    }
    final var type = args[0];


    final var generator = switch (type) {
      case "named" -> new NamedGenerator();
      case "json" -> new JsonGenerator();
      default -> throw new RuntimeException("Invalid type : '"+type+"'");
    };

    final var generatedSourcePath = Path.of(args[1]);
    final var basePackage = args[2];

    final var generationPah = new GenerationPath(generatedSourcePath,basePackage);
    final var quantities = new JacksonQuantityReader().read();


    generator.generate(quantities,generationPah);
  }

}
