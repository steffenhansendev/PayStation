package paystation.domain;

import java.util.Map;

/**
 * The strategy for validating coins
 */
public interface CoinStrategy {
    /**
     * @return true if coin is valid according to currency the pay station is compatible with.
     * @throws IllegalCoinException if an invalid coin is input
     */
    public boolean isCoinValid(int coinValue) throws IllegalCoinException;

    /**
     * Maps all valid coins for the currency the pay station is compatible with to a count of 0.
     * @param inputMap is a map of coins to be initialized
     */
    public void initializeCoins(Map<Integer, Integer> inputMap);
}