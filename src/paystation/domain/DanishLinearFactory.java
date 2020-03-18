package paystation.domain;

public class DanishLinearFactory implements PayStationFactory {
    @Override
    public RateStrategy createRateStrategy() {
        return new DanishLinearRateStrategy();
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
