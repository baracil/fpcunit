package net.femtoparsec.units.generator;

import net.femtoparsec.units.generator.bean.Quantity;

import java.io.IOException;
import java.util.List;

/**
 * @author Bastien Aracil
 */
public interface QuantityReader {

  List<Quantity> read() throws IOException;
}
