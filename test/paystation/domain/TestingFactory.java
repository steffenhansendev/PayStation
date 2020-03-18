package paystation.domain;

import org.junit.Test;

public class TestingFactory implements PayStationFactory {

    private AdditionalInfoPrinter additionalInfoPrinter;

    public TestingFactory(AdditionalInfoPrinter additionalInfoPrinter) {
        this.additionalInfoPrinter = additionalInfoPrinter;
    }

    public TestingFactory(){
        this.additionalInfoPrinter = new NoAdditionalInfoPrinter();
    }

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
        return new ReceiptImpl(boughtParkingTime, additionalInfoPrinter);
    }
}