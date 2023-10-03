module fpc.units.api {
  requires static lombok;


  exports net.femtoparsec.units.api;

  opens net.femtoparsec.units.api to fpc.units.core;

}