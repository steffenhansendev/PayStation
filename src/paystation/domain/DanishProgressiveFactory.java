package paystation.domain;

public class DanishProgressiveFactory implements PayStationFactory {
    @Override
    public RateStrategy createRateStrategy() {
        return new DanishProgressiveRateStrategy();
    }

    @Override
    public CoinStrategy createCoinStrategy() {
        return new KroneCoinStrategy();
    }

    @Override
    public Receipt createReceipt(int boughtParkingTime) {
        return new StandardReceipt(boughtParkingTime);
    }
}
