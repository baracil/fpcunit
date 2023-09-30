package net.femtoparsec.units.generator;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Bastien Aracil
 */
@RequiredArgsConstructor
public class DefaultGenerationPathProvider implements GenerationPathProvider {

    @NonNull
    private final String basePackageName;

    @Override
    public GenerationPath get(GenerationType generationType) throws IOException {
        final Map<String, String> properties = readProperties();
        final Path path = Paths.get(properties.get(generationType.getBaseDirProperty())).normalize();
        return new GenerationPath(path, basePackageName);
    }

    private Map<String, String> readProperties() throws IOException {
        try (Reader reader = new BufferedReader(new InputStreamReader(GenerationPathProvider.class.getResourceAsStream("generation-path.properties")))) {
            final Properties properties = new Properties();
            properties.load(reader);
            final Map<String, String> propertyMap = new HashMap<>();
            properties.stringPropertyNames().forEach(n -> propertyMap.put(n, properties.getProperty(n)));

            return  propertyMap;
        }
    }
}
