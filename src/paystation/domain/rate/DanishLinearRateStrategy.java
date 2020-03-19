package paystation.domain.rate;

public class DanishLinearRateStrategy implements RateStrategy {
    public int calculateTime(int totalOfTransActionCoins) {
        return totalOfTransActionCoins * 7;
    }
}
