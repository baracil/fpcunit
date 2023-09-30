package net.femtoparsec.units.generator.parsing;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import net.femtoparsec.units.generator.parsing.xml.XMLOperation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLOperationDeserializer extends StdDeserializer<XMLOperation[]> {


  public XMLOperationDeserializer() {
    super(XMLOperation[].class);
  }

  @Override
  public XMLOperation[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
    final List<XMLOperation> result = new ArrayList<>();

    final var treeNode = (ObjectNode)jsonParser.getCodec().readTree(jsonParser);

    for (var s : treeNode.properties()) {
      final var name = s.getKey();
      final var node = s.getValue();

      final var type = XMLOperation.Types.findType(name);

      if (node.isObject()) {
        final var xmlOperation = deserializationContext.readTreeAsValue(node, type);
        result.add(xmlOperation);
      } else if (node instanceof ArrayNode arrayNode) {
        final var size = arrayNode.size();
        for (int i = 0; i < size; i++) {
          final var xmlOperation = deserializationContext.readTreeAsValue(arrayNode.get(i), type);
          result.add(xmlOperation);
        }
      }


    }

    return result.toArray(XMLOperation[]::new);
  }

}
