package paystation.domain.rate;

import org.junit.Before;
import org.junit.Test;
import paystation.domain.coin.IllegalCoinException;
import paystation.domain.rate.DanishLinearRateStrategy;
import paystation.domain.rate.RateStrategy;

import static org.junit.Assert.assertEquals;

public class TestDanishLinearRateStrategy {
    private RateStrategy rateStrategy;
    @Before
    public void setUp() {
        rateStrategy = new DanishLinearRateStrategy();
    }
    @Test
    public void _1KroneShouldYield7Minutes() throws IllegalCoinException {
        assertEquals("1 krone must yield 7 minutes parking time", 7, rateStrategy.calculateTime(1));
    }
    @Test
    public void _3KronerShouldYield21Minutes() throws IllegalCoinException {
        assertEquals("3 kroner must yield 21 minutes parking time", 21, rateStrategy.calculateTime(3));
    }
}
