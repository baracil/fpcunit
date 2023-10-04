package net.femtoparsec.units.json;


import com.fasterxml.jackson.databind.module.SimpleModule;
import net.femtoparsec.units.api.Measurement;
import net.femtoparsec.units.api.NamedQuantity;
import net.femtoparsec.units.core.NamedQuantityConsumer;
import net.femtoparsec.units.named.Quantities;

public final class UnitModule extends SimpleModule {

  public UnitModule() {
    Quantities.accept(new NamedQuantityConsumer() {
      @Override
      public <Q extends NamedQuantity<M>, M extends Measurement<?>> void accept(Class<Q> quantityType, Q quantity) {
        new QuantitySerDes<Q, M>(quantity, quantityType).register(UnitModule.this);
      }
    });
  }

}