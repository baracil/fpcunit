package net.femtoparsec.units.generator;

import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Bastien Aracil
 */
@Getter
public class GenerationPath {

    private final Path generatedSourcePath;
    private final String basePackageName;
    private final Path basePackagePath;

    public GenerationPath(Path generatedSourcePath, String basePackageName) {
        this.basePackageName = basePackageName;
        this.generatedSourcePath = generatedSourcePath;
        this.basePackagePath = new PackagePathBuilder(generatedSourcePath, basePackageName).getBasePackagePath();
    }

    public void createGenerationDirectory() {
        createGenerationDirectory(generatedSourcePath, basePackagePath);
    }

    private void createGenerationDirectory(Path defaultPackagePath, Path basePackagePath) {
        if (!Files.isDirectory(defaultPackagePath)) {
            throw new IllegalArgumentException("Invalid default package path 'this is not a directory' : "+defaultPackagePath);
        }
        if (!Files.exists(basePackagePath)) {
            try {
                Files.createDirectories(basePackagePath);
            } catch (IOException e) {
                throw new IllegalArgumentException("Could not create '"+basePackagePath+"'", e);
            }
        }
        if (!Files.isDirectory(basePackagePath)) {
            throw new IllegalArgumentException("Could not create '"+basePackagePath+"'");
        }

    }

}
