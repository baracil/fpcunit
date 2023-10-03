package net.femtoparsec.units.generator;

import net.femtoparsec.units.generator.bean.Quantity;
import net.femtoparsec.units.generator.parsing.JacksonQuantityReader;

import java.io.IOException;
import java.util.List;

public class Dumper {
  public static void main(String[] args) throws IOException {
    final var read = new JacksonQuantityReader().read();

    read.forEach(q-> System.out.println(q.getName()+" "+q.getDimension().getEncoded()));
  }
}
