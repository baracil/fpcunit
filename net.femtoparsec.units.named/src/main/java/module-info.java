module fpc.units.named {
  requires static lombok;


  requires transitive fpc.units.api;
  requires transitive fpc.units.core;

  exports net.femtoparsec.units.named;
  exports net.femtoparsec.units.named.measurement;
  exports net.femtoparsec.units.named.unit;
  exports net.femtoparsec.units.named.quantity;
}