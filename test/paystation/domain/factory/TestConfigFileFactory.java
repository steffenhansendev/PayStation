package paystation.domain.factory;

import org.junit.Before;
import org.junit.Test;
import paystation.domain.rate.RateStrategy;

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
}
