package paystation.domain.coin;
import paystation.domain.coin.CoinStrategy;
import paystation.domain.coin.IllegalCoinException;

import java.util.Map;
public class TestingCoinStrategy implements CoinStrategy {
    public boolean isCoinValid(int coinValue) throws IllegalCoinException {
        switch (coinValue) {
            case 3:
            case 7:
            case 9:
                break;
            default:
                throw new IllegalCoinException(coinValue + " is not a valid coin.");
        }
        return true;
    }
    /** Testing is done with coin values unlikely to be part of any real currency
     * @param inputMap is a map of coins to be initialized
     */
    public void initializeCoins(Map<Integer, Integer> inputMap) {
        inputMap.put(3, 0);
        inputMap.put(7, 0);
        inputMap.put(9, 0);
    }
}
