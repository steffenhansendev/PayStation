package paystation.domain.rate;

import paystation.domain.rate.RateStrategy;

public class TestingRateStrategy implements RateStrategy {
    public int calculateTime(int amount) {
        return amount;
    }
}
