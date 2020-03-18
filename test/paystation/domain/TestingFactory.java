package paystation.domain;

public class TestingFactory implements PayStationFactory {

    @Override
    public RateStrategy createRateStrategy() {
        return new TestingRateStrategy();
    }

    @Override
    public CoinStrategy createCoinStrategy() {
        return new TestingCoinStrategy();
    }

    @Override
    public Receipt createReceipt(int boughtParkingTime) {
        return new StandardReceipt(boughtParkingTime);
    }
}