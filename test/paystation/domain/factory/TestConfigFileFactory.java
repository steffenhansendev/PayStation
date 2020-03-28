package paystation.domain.factory;

import org.junit.Before;
import org.junit.Test;
import paystation.domain.coin.CoinStrategy;
import paystation.domain.display.DisplayStrategy;
import paystation.domain.rate.RateStrategy;
import paystation.domain.receipt.Receipt;

import static org.junit.Assert.assertTrue;

public class TestConfigFileFactory {

    private PayStationFactory payStationFactory;

    @Before
    public void setUp() {
        payStationFactory = new ConfigFileFactory();
    }

    @Test
    public void configFileFactoryShouldReturnRateStrategy() {
        assertTrue(payStationFactory.createRateStrategy() instanceof RateStrategy);
    }

    @Test
    public void configFileFactoryShouldReturnCoinStrategy() {
        assertTrue(payStationFactory.createCoinStrategy() instanceof CoinStrategy);
    }

    @Test
    public void configFileFactoryShouldReturnReceipts() {
        assertTrue(payStationFactory.createReceipt(30) instanceof Receipt);
    }

    @Test
    public void configFileFactoryShouldReturnDisplayStrategy() {
        assertTrue(payStationFactory.createDisplayStrategy() instanceof DisplayStrategy);
    }
}