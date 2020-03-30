package paystation.domain.rate;

import paystation.thirdparty.LunaRateCalculator;

public class LunaRateStrategyAdapter implements RateStrategy {

    private LunaRateCalculator adaptee;

    public void LunaAdapterRateStrategy(LunaRateCalculator adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public int calculateTime(int totalOfTransActionCoins) {
        double translatedParameter = totalOfTransActionCoins / 100.0;
        return adaptee.calculateRateForAmount(translatedParameter);
    }
}
