package paystation.domain;
public class TestingRateStrategy implements RateStrategy {
    public int calculateTime(int amount) {
        return amount;
    }
}
