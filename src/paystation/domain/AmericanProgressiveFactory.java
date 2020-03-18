package paystation.domain;

public class AmericanProgressiveFactory implements PayStationFactory {
    @Override
    public RateStrategy createRateStrategy() {
        return new AmericanProgressiveRateStrategy();
    }

    @Override
    public CoinStrategy createCoinStrategy() {
        return new CentCoinStrategy();
    }

    @Override
    public Receipt createReceipt(int boughtParkingTime) {
        return new StandardReceipt(boughtParkingTime);
    }
}