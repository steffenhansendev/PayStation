package paystation.domain;

public class AmericanLinearFactory implements PayStationFactory {
    @Override
    public RateStrategy createRateStrategy() {
        return new AmericanLinearRateStrategy();
    }

    @Override
    public CoinStrategy createCoinStrategy() {
        return new CentCoinStrategy();
    }

    @Override
    public Receipt createReceipt(int boughtParkingTime) {
        return new ReceiptImpl(boughtParkingTime);
    }
}
