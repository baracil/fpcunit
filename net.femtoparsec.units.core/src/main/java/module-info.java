module fpc.units.core {
  requires static lombok;

  requires transitive fpc.units.api;

  exports net.femtoparsec.units.core;
}