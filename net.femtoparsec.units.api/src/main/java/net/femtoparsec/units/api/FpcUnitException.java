package net.femtoparsec.units.api;

public class FpcUnitException extends RuntimeException {

  public FpcUnitException(String message) {
    super(message);
  }

  public FpcUnitException(String message, Throwable cause) {
    super(message, cause);
  }

  public FpcUnitException(Throwable cause) {
    super(cause);
  }

  public FpcUnitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
