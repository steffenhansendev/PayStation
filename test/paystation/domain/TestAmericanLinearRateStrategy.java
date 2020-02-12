package paystation.domain;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class TestAmericanLinearRateStrategy {
    private RateStrategy rateStrategy;
    @Before
    public void setUp() {
        rateStrategy = new AmericanLinearRateStrategy();
    }
    @Test
    public void _5CentsShouldYield2Minutes() throws IllegalCoinException {
        assertEquals("5 cents should yield 2 minutes parking time", 2, rateStrategy.calculateTime(5));
    }
    @Test
    public void _35CentsShouldYields14Minutes() throws IllegalCoinException {
        assertEquals("35 cents should yield 14 minutes parking time", 14, rateStrategy.calculateTime(35));
    }
}
