package paystation.domain;

import paystation.domain.coin.CoinStrategy;
import paystation.domain.coin.IllegalCoinException;
import paystation.domain.display.DisplayStrategy;
import paystation.domain.factory.PayStationFactory;
import paystation.domain.rate.RateStrategy;
import paystation.domain.receipt.Receipt;

import java.util.LinkedHashMap;
import java.util.Map;

public class PayStationImpl implements PayStation {
  private int parkingTime;
  private Map<Integer, Integer> transactionCoins;
  private Map<Integer, Integer> earnedCoins;
  private boolean isInTransaction;
  private RateStrategy rateStrategy;
  private CoinStrategy coinStrategy;
  private DisplayStrategy displayStrategy;
  private PayStationFactory payStationFactory;

  public PayStationImpl(PayStationFactory payStationFactory) {
    this.payStationFactory = payStationFactory;
    configureFromFactory();
    transactionCoins = new LinkedHashMap<Integer, Integer>();
    coinStrategy.initializeCoins(transactionCoins);
    earnedCoins = new LinkedHashMap<Integer, Integer>();
    coinStrategy.initializeCoins(earnedCoins);
    isInTransaction = false;
  }

  public void addPayment(int coinValue )
          throws IllegalCoinException {
    if (!isInTransaction) {
      isInTransaction = true;
    }
    coinStrategy.isCoinValid(coinValue);
    transactionCoins.put(coinValue, transactionCoins.get(coinValue) + 1);
    calculateParkingTime();
  }

  public int readDisplay() {
    return displayStrategy.readDisplay(parkingTime);
  }

  public Receipt buy() {
    Receipt receipt = payStationFactory.createReceipt(parkingTime);
    for (Map.Entry<Integer, Integer> transactionCoinsEntry : transactionCoins.entrySet()) {
      int key = transactionCoinsEntry.getKey();
      int value = transactionCoinsEntry.getValue();
      earnedCoins.put(key, earnedCoins.get(key) + value);
    }
    coinStrategy.initializeCoins(transactionCoins);
    parkingTime = 0;
    isInTransaction = false;
    return receipt;
  }

  public Map<Integer, Integer> cancel() {
    Map<Integer, Integer> returnCoins = new LinkedHashMap<Integer, Integer>(transactionCoins);
    coinStrategy.initializeCoins(transactionCoins);
    parkingTime = 0;
    isInTransaction = false;
    return returnCoins;
  }

  public Map<Integer, Integer> empty() throws EmptyDuringTransactionException {
    if (isInTransaction) {
      throw new EmptyDuringTransactionException("Please complete or cancel current transaction before emptying");
    }
    Map<Integer, Integer> returnCoins = new LinkedHashMap<Integer, Integer>(earnedCoins);
    coinStrategy.initializeCoins(earnedCoins);
    return returnCoins;
  }

  public void reconfigure(PayStationFactory payStationFactory) {
    this.payStationFactory = payStationFactory;
    configureFromFactory();
  }

  private void calculateParkingTime() {
    int totalOfTransActionCoins = 0;
    for (Map.Entry<Integer, Integer> transactionCoinsEntry : transactionCoins.entrySet()) {
      int key = transactionCoinsEntry.getKey();
      int value = transactionCoinsEntry.getValue();
      totalOfTransActionCoins += key * value;
    }
    parkingTime = rateStrategy.calculateTime(totalOfTransActionCoins);
  }

  private void configureFromFactory() {
    this.rateStrategy = payStationFactory.createRateStrategy();
    this.coinStrategy = payStationFactory.createCoinStrategy();
    this.displayStrategy = payStationFactory.createDisplayStrategy();
  }
}