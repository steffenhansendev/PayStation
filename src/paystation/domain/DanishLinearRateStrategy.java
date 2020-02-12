package paystation.domain;

public class DanishLinearRateStrategy implements RateStrategy {
    public int calculateTime(int totalOfTransActionCoins) {
        return totalOfTransActionCoins * 7;
    }
}
