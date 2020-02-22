package paystation.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestAlternatingRate {

    private RateStrategy rateStrategy;

    @Before
    public void setUp() {
        rateStrategy = new AlternatingRateStrategy(new AmericanLinearRateStrategy(), new AmericanProgressiveRateStrategy());
    }

    @Test
    public void _500CentsShouldYield200MinutesOnMonday() throws IllegalCoinException {
        assertEquals("WILL FAIL DURING WEEKEND: 300 krone must yield 200 minutes parking time during weekdays", 200, rateStrategy.calculateTime(500));
    }

    @Test
    public void _500CentsShouldYield200MinutesOnSunday() throws IllegalCoinException {
        assertEquals("WILL FAIL DURING WEEKDAYS: 300 krone must yield 200 minutes parking time during weekdays", 150, rateStrategy.calculateTime(500));
    }

}
