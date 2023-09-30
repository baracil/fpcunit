module fpc.units.property {
  requires static lombok;
  requires javafx.base;


  requires transitive fpc.units.api;
  requires transitive fpc.units.core;
  requires transitive fpc.units.named;

  exports net.femtoparsec.units.property;
  exports net.femtoparsec.units.property.measurement;
}