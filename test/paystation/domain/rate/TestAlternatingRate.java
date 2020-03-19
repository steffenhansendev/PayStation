package paystation.domain.rate;

import org.junit.Before;
import org.junit.Test;
import paystation.domain.coin.IllegalCoinException;

import static org.junit.Assert.assertEquals;

public class TestAlternatingRate {


    @Before
    public void setUp() {
    }

    @Test
    public void _500CentsShouldYield200MinutesOnMonday() throws IllegalCoinException {
        RateStrategy rateStrategy = new AlternatingRateStrategy(new AmericanLinearRateStrategy(), new AmericanProgressiveRateStrategy(), new FixedWeekendDetectionStrategy(false));
        assertEquals("500 cents must yield 120 minutes parking time during weekdays", 200, rateStrategy.calculateTime(500));
    }

    @Test
    public void _500CentsShouldYield200MinutesOnSunday() throws IllegalCoinException {
        RateStrategy rateStrategy = new AlternatingRateStrategy(new AmericanLinearRateStrategy(), new AmericanProgressiveRateStrategy(), new FixedWeekendDetectionStrategy(true));
        assertEquals("500 cents must yield 200 minutes parking time during weekends", 150, rateStrategy.calculateTime(500));
    }
}