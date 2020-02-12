package paystation.domain;

import java.util.Map;

public interface PayStation {
  /**
   * Insert coin into the pay station and adjust state accordingly.
   * @param coinValue is an integer value representing the coin in
   * cent. That is, a quarter is coinValue=25, etc.
   * @throws IllegalCoinException in case coinValue is not 
   * a valid coin value
   */
  public void addPayment(int coinValue) throws IllegalCoinException;
  /**
   * Read the machine's display. The display shows a numerical
   * description of the amount of parking time accumulated so far
   * based on inserted payment.  
   * @return the number to display on the pay station display
   */
  public int readDisplay();
  /**
   * Buy parking time. Terminate the ongoing transaction and
   * return a parking receipt. A non-null object is always returned.
   * @return a valid parking receipt object.
   */ 
  public Receipt buy();
  /**
   * Cancel the present transaction. Resets the pay station for a new transaction
   * @return A dictionary defining the coins returned to the user.
   * The key is a valid coin type and the associated value is
   * the number of these coins that are returned (i.e. identical
   * to the number of this coin type that the user has entered.)
   * The dictionary objects is never null even if no coins have been entered.
   */
  public Map<Integer, Integer> cancel();
  /**
   * Return the total amount of money earned by the pay station since las call and empty it, setting the earning to zero
   */
  public Map<Integer, Integer> empty() throws EmptyDuringTransactionException;
}