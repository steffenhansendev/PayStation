package paystation.domain.rate;

public class AlternatingRateStrategy implements RateStrategy {

    private RateStrategy currentState;
    private RateStrategy weekdayRateStrategy;
    private RateStrategy weekendRateStrategy;
    private WeekendDetectionStrategy weekendDetectionStrategy;

    public AlternatingRateStrategy(RateStrategy weekdayRateStrategy, RateStrategy weekendRateStrategy, WeekendDetectionStrategy weekendDetectionStrategy) {
        this.weekdayRateStrategy = weekdayRateStrategy;
        this.weekendRateStrategy = weekendRateStrategy;
        this.weekendDetectionStrategy = weekendDetectionStrategy;
    }

    public int calculateTime(int totalOfTransActionCoins) {
        if(isWeekend()){
            currentState = weekendRateStrategy;
        } else  {
            currentState = weekdayRateStrategy;
        }
        return currentState.calculateTime(totalOfTransActionCoins);
    }

    private boolean isWeekend() {
        return weekendDetectionStrategy.isWeekend();
    }
}
