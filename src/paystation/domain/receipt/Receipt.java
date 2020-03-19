package paystation.domain.receipt;

import java.io.PrintStream;

public interface Receipt {
  /**
   * Return the number of minutes this receipt is valid for.
   * @return number of minutes parking time
  */
  public int value();

  public void print(PrintStream printStream);
}

