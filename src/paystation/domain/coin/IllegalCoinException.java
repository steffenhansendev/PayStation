package paystation.domain.coin;

public class IllegalCoinException extends Exception {
  public IllegalCoinException( String errorMessage ) { super(errorMessage); }
}
