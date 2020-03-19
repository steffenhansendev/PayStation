package paystation.domain;

import org.junit.*;
import paystation.domain.coin.IllegalCoinException;
import paystation.domain.factory.TestingFactory;
import paystation.domain.receipt.Receipt;

import static org.junit.Assert.*;
import java.util.Map;

public class TestPayStation {
  private PayStation payStation;

  @Before
  public void setup() {
    payStation = new PayStationImpl(new TestingFactory());
  }

  @Test
  public void buyingShouldReturnValidReceipt() throws IllegalCoinException {
    payStation.addPayment(3);
    payStation.addPayment(7);
    payStation.addPayment(9);
    Receipt receipt = payStation.buy();
    assertNotNull("Receipt reference can not be null", receipt);
    assertEquals("1, 7, and 9 should yield 19 minutes parking time", 3 + 7 + 9, receipt.value());
  }

  @Test
  public void receiptShouldStoreBoughtParkingTime() throws IllegalCoinException {
    payStation.addPayment(9);
    payStation.addPayment(9);
    payStation.addPayment(9);
    Receipt receipt = payStation.buy();
    assertEquals("Receipt should store the parking time bought", 9 + 9 + 9, receipt.value());
  }

  @Test
  public void buyingReceiptShouldReadyPayStationForNewTransaction() throws IllegalCoinException {
    payStation.addPayment(3);
    payStation.buy();
    assertEquals("Display should read 0 after a receipt has been bought", 0, payStation.readDisplay());
    payStation.addPayment(3);
    payStation.addPayment(7);
    assertEquals("Display should read bought parking time after a receipt has been bought", 3 + 7, payStation.readDisplay());
    Receipt receipt = payStation.buy();
    assertEquals("Receipt should store the parking time bought after a previous receipt has been bought", 3 + 7, receipt.value());
  }

  @Test
  public void cancellingShouldResetPayStation() throws IllegalCoinException {
    payStation.addPayment(3);
    payStation.cancel();
    assertEquals("Display should read 0 after cancelling transaction", 0, payStation.readDisplay());
    payStation.addPayment(7);
    assertEquals("Display should read bought parking time after a transaction has been cancelled", 7, payStation.readDisplay());
  }

  @Test(expected = EmptyDuringTransactionException.class)
  public void emptyingShouldBeDisallowedUnderTransaction() throws EmptyDuringTransactionException, IllegalCoinException {
    payStation.addPayment(7);
    payStation.empty();
  }

  @Test
  public void emptyingShouldYield7For7Earnings() throws EmptyDuringTransactionException, IllegalCoinException {
    payStation.addPayment(7);
    payStation.buy();
    Map<Integer, Integer> returnedCoins = payStation.empty();
    int totalEarnings = 0;
    totalEarnings += returnedCoins.get(3) * 3;
    totalEarnings += returnedCoins.get(7) * 7;
    totalEarnings += returnedCoins.get(9) * 9;
    assertEquals("Selling parking time for 7 should yield 7 when emptying", 7, totalEarnings);
  }

  @Test
  public void emptyingShouldYield16For16Earnings() throws EmptyDuringTransactionException, IllegalCoinException {
    payStation.addPayment(7);
    payStation.addPayment(9);
    payStation.buy();
    Map<Integer, Integer> returnedCoins = payStation.empty();
    int totalEarnings = 0;
    totalEarnings += returnedCoins.get(3) * 3;
    totalEarnings += returnedCoins.get(7) * 7;
    totalEarnings += returnedCoins.get(9) * 9;
    assertEquals("Selling parking time for 16 should yield 16  when emptying", 7 + 9, totalEarnings);
  }

  @Test
  public void emptyingShouldEmptyEarnedCoins() throws EmptyDuringTransactionException, IllegalCoinException {
    payStation.addPayment(3);
    payStation.addPayment(7);
    payStation.addPayment(9);
    payStation.buy();
    payStation.empty();
    Map<Integer, Integer> returnedCoins = payStation.empty();
    assertEquals("Returned coins must contain no 3 coins after empty", 0, (int) returnedCoins.get(3));
    assertEquals("Returned coins must contain no 7 coins after empty", 0, (int) returnedCoins.get(7));
    assertEquals("Returned coins must contain no 9 coins after empty", 0, (int) returnedCoins.get(9));
  }

  @Test
  public void emptyingShouldYield0For0Earnings() throws EmptyDuringTransactionException {
    Map<Integer, Integer> returnedCoins = payStation.empty();
    int totalEarnings = 0;
    totalEarnings += returnedCoins.get(3) * 3;
    totalEarnings += returnedCoins.get(7) * 7;
    totalEarnings += returnedCoins.get(9) * 9;
    assertEquals("Having received 0 should yield 0 when emptying", 0, totalEarnings);
  }

  @Test
  public void cancellingShouldAlwaysReturnNonNullObject() throws IllegalCoinException {
    assertNotNull("The dictionary object returned when cancelling must not be null if no coins has been inserted", payStation.cancel());
    payStation.addPayment(7);
    assertNotNull("The dictionary object returned when cancelling must not be null if coins has been inserted", payStation.cancel());
  }

  @Test
  public void cancellingShouldReturnOne3Two7sAndThree9WhenTheseHaveBeenInserted() throws IllegalCoinException {
    payStation.addPayment(3);
    payStation.addPayment(7);
    payStation.addPayment(9);
    payStation.addPayment(9);
    payStation.addPayment(7);
    payStation.addPayment(9);
    Map<Integer, Integer> returnedCoins = payStation.cancel();
    assertEquals("Returned coins must contain one 3 coin", 1, (int) returnedCoins.get(3));
    assertEquals("Returned coins must contain two 7 coins", 2, (int) returnedCoins.get(7));
    assertEquals("Returned coins must contain three 9  coins", 3, (int) returnedCoins.get(9));
  }

  @Test
  public void cancellingShouldEmptyTheSetOfTransactionCoins() throws IllegalCoinException {
    payStation.addPayment(3);
    payStation.addPayment(3);
    payStation.addPayment(7);
    payStation.addPayment(7);
    payStation.addPayment(9);
    payStation.addPayment(9);
    payStation.cancel();
    Map<Integer, Integer> returnedCoinsAfterCancel = payStation.cancel();
    assertEquals("Number of 3 must be 0 when the transaction has been cancelled", 0, (int) returnedCoinsAfterCancel.get(3));
    assertEquals("Number of 7 must be 0 when the transaction has been cancelled", 0, (int) returnedCoinsAfterCancel.get(7));
    assertEquals("Number of 9 must be 0 when the transaction has been cancelled", 0, (int) returnedCoinsAfterCancel.get(9));
  }

  @Test
  public void buyShouldEmptyTheSetOfTransactionCoins() throws IllegalCoinException {
    payStation.addPayment(3);
    payStation.addPayment(3);
    payStation.addPayment(7);
    payStation.addPayment(7);
    payStation.addPayment(9);
    payStation.addPayment(9);
    payStation.buy();
    Map<Integer, Integer> returnedCoins = payStation.cancel();
    assertEquals("Number of 3 must be 0 when the transaction has been completed", 0, (int) returnedCoins.get(3));
    assertEquals("Number of 7 must be 0 when the transaction has been completed", 0, (int) returnedCoins.get(7));
    assertEquals("Number of 9 must be 0 when the transaction has been completed", 0, (int) returnedCoins.get(9));
  }
}