package paystation.domain;

import java.util.Map;

public class CentCoinStrategy implements CoinStrategy {
    public boolean isCoinValid(int coinValue) throws IllegalCoinException {
        switch (coinValue) {
            case 5:
            case 10:
            case 25:
                break;
            default:
                throw new IllegalCoinException(coinValue + " is not a valid coin.");
        }
        return true;
    }

    public void initializeCoins(Map<Integer, Integer> inputMap) {
        inputMap.put(5, 0);
        inputMap.put(10, 0);
        inputMap.put(25, 0);
    }
}
