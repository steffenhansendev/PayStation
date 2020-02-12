package paystation.domain;

import java.util.Map;

public class KroneCoinStrategy implements CoinStrategy {
    public boolean isCoinValid(int coinValue) throws IllegalCoinException {
        switch(coinValue){
            case 1:
            case 2:
            case 5:
            case 10:
            case 20:
                break;
            default:
                throw new IllegalCoinException(coinValue + " is not a valid coin.");
        }
        return true;
    }

    public void initializeCoins(Map<Integer, Integer> inputMap) {
        inputMap.put(1, 0);
        inputMap.put(2, 0);
        inputMap.put(5, 0);
        inputMap.put(10, 0);
        inputMap.put(20, 0);
    }
}