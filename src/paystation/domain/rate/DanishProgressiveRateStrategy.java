package paystation.domain.rate;

public class DanishProgressiveRateStrategy implements RateStrategy {
    int time = 0;

    public int calculateTime(int totalOfTransActionCoins) {
        time = totalOfTransActionCoins * 6;
        if(time >= 120) {
            totalOfTransActionCoins -= 20;
            time = 120 + totalOfTransActionCoins * 5;
        }
        return time;
    }
}