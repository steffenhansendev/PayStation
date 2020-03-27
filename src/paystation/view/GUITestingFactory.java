package paystation.view;

import paystation.domain.coin.CentCoinStrategy;
import paystation.domain.coin.CoinStrategy;
import paystation.domain.display.DisplayStrategy;
import paystation.domain.display.ParkingEndsAtDisplayStrategy;
import paystation.domain.factory.PayStationFactory;
import paystation.domain.rate.*;
import paystation.domain.receipt.BarCodeAdditionalInfoPrinter;
import paystation.domain.receipt.Receipt;
import paystation.domain.receipt.ReceiptImpl;

public class GUITestingFactory implements PayStationFactory {
    @Override
    public RateStrategy createRateStrategy() {
        return new AlternatingRateStrategy(new AmericanLinearRateStrategy(), new AmericanProgressiveRateStrategy(), new ClockBasedWeekendDetectionStrategy());
    }

    @Override
    public CoinStrategy createCoinStrategy() {
        return new CentCoinStrategy();
    }

    @Override
    public Receipt createReceipt(int boughtParkingTime) {
        return new ReceiptImpl(boughtParkingTime, new BarCodeAdditionalInfoPrinter());
    }

    @Override
    public DisplayStrategy createDisplayStrategy() {
        return new ParkingEndsAtDisplayStrategy();
    }
}
