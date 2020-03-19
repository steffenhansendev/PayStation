package paystation.domain.rate;

public class AmericanProgressiveRateStrategy implements RateStrategy {
    int time = 0;

    public int calculateTime(int totalOfTransActionCoins) {
        time = totalOfTransActionCoins / 5 * 2;
        if(time >= 60) {
            totalOfTransActionCoins -= 150;
            time = 60;
            time += totalOfTransActionCoins / 5 * 1.5;
        }
        if(time >= 120) {
            totalOfTransActionCoins -= 200;
            time = 120;
            time += totalOfTransActionCoins / 5 * 1;
        }
        return time;
    }
}