package paystation.domain.factory;

import paystation.domain.coin.CoinStrategy;
import paystation.domain.coin.TestingCoinStrategy;
import paystation.domain.display.DisplayStrategy;
import paystation.domain.display.ParkingTimeDisplayStrategy;
import paystation.domain.rate.RateStrategy;
import paystation.domain.rate.TestingRateStrategy;
import paystation.domain.receipt.AdditionalInfoPrinter;
import paystation.domain.receipt.NoAdditionalInfoPrinter;
import paystation.domain.receipt.Receipt;
import paystation.domain.receipt.ReceiptImpl;

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

    @Override
    public DisplayStrategy createDisplayStrategy() {
        return new ParkingTimeDisplayStrategy();
    }
}