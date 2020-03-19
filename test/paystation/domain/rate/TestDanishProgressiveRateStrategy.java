package paystation.domain.rate;

import org.junit.Before;
import org.junit.Test;
import paystation.domain.coin.IllegalCoinException;
import paystation.domain.rate.DanishProgressiveRateStrategy;
import paystation.domain.rate.RateStrategy;

import static org.junit.Assert.assertEquals;

public class TestDanishProgressiveRateStrategy {
    private RateStrategy rateStrategy;
    @Before
    public void setUp() {
        rateStrategy = new DanishProgressiveRateStrategy();
    }
    @Test
    public void _1KroneShouldYield6Minutes() throws IllegalCoinException {
        assertEquals("1 krone must yield 6 minutes parking time", 6, rateStrategy.calculateTime(1));
    }
    @Test
    public void _20KronerShouldYield6Minutes() throws IllegalCoinException {
        assertEquals("20 kroner must yield 120 minutes parking time", 120, rateStrategy.calculateTime(20));
    }
    @Test
    public void _21KronerShouldYield6Minutes() throws IllegalCoinException {
        assertEquals("21 kroner must yield 125 minutes parking time", 125, rateStrategy.calculateTime(21));
    }
}
