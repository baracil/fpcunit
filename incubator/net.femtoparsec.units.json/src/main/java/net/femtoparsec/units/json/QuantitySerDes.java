package net.femtoparsec.units.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.RequiredArgsConstructor;
import net.femtoparsec.units.api.Measurement;
import net.femtoparsec.units.api.NamedQuantity;
import net.femtoparsec.units.named.Quantities;

import java.io.IOException;

@RequiredArgsConstructor
public class QuantitySerDes<Q extends NamedQuantity<M>, M extends Measurement<?>> {

  private final Q quantity;
  private final Class<Q> quantityType;

  public void register(SimpleModule module) {
    module.addSerializer(quantityType, new QuantitySerializer());
    module.addDeserializer(quantityType, new QuantityDeserializer());
    module.addSerializer(quantity.getMeasurementType(), new MeasurementSerializer());
    module.addDeserializer(quantity.getMeasurementType(), new MeasurementDeserializer());
  }

  public class QuantitySerializer extends JsonSerializer<Q> {

    @Override
    public void serialize(Q value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
      gen.writeString(value.getName());
    }
  }

  @RequiredArgsConstructor
  private class QuantityDeserializer extends JsonDeserializer<Q> {

    @Override
    public Q deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
      final var name = p.getValueAsString();
      final var quantity = Quantities.QUANTITIES_BY_NAME.get(name);

      if (!quantityType.isInstance(quantity)) {
        throw new IllegalArgumentException("The quantity with name '" + name + "' is not of type " + quantityType);
      }

      return quantityType.cast(Quantities.QUANTITIES_BY_NAME.get(name));
    }
  }


  private class MeasurementSerializer extends JsonSerializer<M> {

    @Override
    public void serialize(M value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
      gen.writeString(value.getValue() + " " + value.getUnit().getName());
    }
  }

  private class MeasurementDeserializer extends JsonDeserializer<M> {

    @Override
    public M deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
      return quantity.parseMeasurement(p.getValueAsString());
    }
  }

}
