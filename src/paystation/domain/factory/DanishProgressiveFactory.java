package paystation.domain.factory;

import paystation.domain.coin.CoinStrategy;
import paystation.domain.coin.KroneCoinStrategy;
import paystation.domain.rate.DanishProgressiveRateStrategy;
import paystation.domain.rate.RateStrategy;
import paystation.domain.receipt.Receipt;
import paystation.domain.receipt.ReceiptImpl;

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
        return new ReceiptImpl(boughtParkingTime);
    }
}
