package paystation.domain.rate;
import org.junit.*;
import paystation.domain.coin.IllegalCoinException;
import paystation.domain.rate.AmericanProgressiveRateStrategy;
import paystation.domain.rate.RateStrategy;

import static org.junit.Assert.assertEquals;
public class TestAmericanProgressiveRateStrategy {
    private RateStrategy rateStrategy;
    @Before
    public void setUp() {
        rateStrategy = new AmericanProgressiveRateStrategy();
    }
    @Test
    public void _150CentsShouldYield60Minutes() throws IllegalCoinException {
        assertEquals("150 cents should yield 60 minutes parking time", 60, rateStrategy.calculateTime(150));
    }
    @Test
    public void _350CentsShouldYield120Minutes() throws IllegalCoinException {
        assertEquals("350 cents should yield 120 minutes parking time", 120, rateStrategy.calculateTime(350));
    }
    @Test
    public void _650CentsShouldYield180Minutes() throws IllegalCoinException {
        assertEquals("650 cents should yield 180 minutes parking time", 180, rateStrategy.calculateTime(650));
    }
    @Test
    public void _655CentsShouldYield181Minutes() throws IllegalCoinException {
        assertEquals("665 cents should yield 181 minutes parking time", 181, rateStrategy.calculateTime(655));
    }
}