package paystation.domain.factory;

import paystation.domain.coin.CentCoinStrategy;
import paystation.domain.coin.CoinStrategy;
import paystation.domain.display.DisplayStrategy;
import paystation.domain.display.ParkingTimeDisplayStrategy;
import paystation.domain.rate.AmericanProgressiveRateStrategy;
import paystation.domain.rate.RateStrategy;
import paystation.domain.receipt.Receipt;
import paystation.domain.receipt.ReceiptImpl;

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
        return new ReceiptImpl(boughtParkingTime);
    }

    @Override
    public DisplayStrategy createDisplayStrategy() {
        return new ParkingTimeDisplayStrategy();
    }
}