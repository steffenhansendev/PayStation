package paystation.domain;

public interface PayStationFactory {
    public RateStrategy createRateStrategy();

    public CoinStrategy createCoinStrategy();

    public Receipt createReceipt(int boughtParkingTime);
}