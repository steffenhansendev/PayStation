package paystation.domain.factory;

import paystation.domain.coin.CoinStrategy;
import paystation.domain.display.DisplayStrategy;
import paystation.domain.rate.RateStrategy;
import paystation.domain.receipt.Receipt;

public interface PayStationFactory {
    public RateStrategy createRateStrategy();

    public CoinStrategy createCoinStrategy();

    public Receipt createReceipt(int boughtParkingTime);

    public DisplayStrategy createDisplayStrategy();
}