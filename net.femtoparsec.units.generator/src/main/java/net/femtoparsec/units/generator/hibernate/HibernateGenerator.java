package net.femtoparsec.units.generator.hibernate;

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
public class HibernateGenerator implements UnitGenerator {

    @NonNull
    private final List<Quantity> quantities;

    @NonNull
    private final GenerationPathProvider generationPathProvider;

    private GenerationPath generationPath;

    @Override
    public void generate() throws IOException {
        generationPath = generationPathProvider.get(GenerationType.HIBERNATE);
        quantities.forEach(this::generate);
    }

    private void generate(Quantity quantity) {
        new HibernateClassGenerator(quantity, generationPath).generate();
    }
}
