package net.femtoparsec.units.generator;

import java.io.IOException;

/**
 * @author Bastien Aracil
 */
public interface GenerationPathProvider {

    GenerationPath get(GenerationType generationType) throws IOException;
}
