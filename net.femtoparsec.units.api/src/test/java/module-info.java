open module fpc.units.api.test {

  requires transitive fpc.units.api;

  requires transitive org.junit.jupiter.api;
  requires transitive org.junit.jupiter.params;


  exports net.femtoparsec.units.api.test;
}