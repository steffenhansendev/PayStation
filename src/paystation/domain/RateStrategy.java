package paystation.domain;

/**
 * The strategy for calculating parking rates
 */
public interface RateStrategy {
    /**
     * return the number of minutes parking time the provided payment is valid for.
     * @param totalOfTransActionCoins payment in som currency.
     * @return number of minutes parking time.
     */
    public int calculateTime(int totalOfTransActionCoins);
}
