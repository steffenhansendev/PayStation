package paystation.domain.rate;

public class AmericanLinearRateStrategy implements RateStrategy {
    public int calculateTime(int totalOfTransActionCoins) {
        return totalOfTransActionCoins / 5 * 2;
    }
}
