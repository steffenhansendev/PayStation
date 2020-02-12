package paystation.domain;

public class IllegalCoinException extends Exception {
  public IllegalCoinException( String errorMessage ) { super(errorMessage); }
}
