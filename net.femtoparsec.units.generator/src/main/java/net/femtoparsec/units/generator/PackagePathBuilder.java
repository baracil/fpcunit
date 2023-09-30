package net.femtoparsec.units.generator;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.nio.file.Path;

/**
 * @author Bastien Aracil
 */
@RequiredArgsConstructor
public class PackagePathBuilder {

    @NonNull
    private final Path defaultPackagePath;

    @NonNull
    private final String basePackageName;

    private String[] tokens = null;

    private Path basePackagePath = null;

    public Path getBasePackagePath() {
        this.splitBasePackageName();
        this.buildDefaultPackagePath();
        return basePackagePath;
    }

    private void splitBasePackageName() {
        this.tokens = basePackageName.split("\\.");
    }

    private void buildDefaultPackagePath() {
        Path current = defaultPackagePath;
        for (String token : tokens) {
            current = current.resolve(token);
        }
        this.basePackagePath = current;
    }

}
