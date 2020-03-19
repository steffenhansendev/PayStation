package paystation.domain.rate;

import paystation.domain.rate.WeekendDetectionStrategy;

public class FixedWeekendDetectionStrategy implements WeekendDetectionStrategy {

    private boolean isWeekend = false;

    public FixedWeekendDetectionStrategy(boolean isWeekend) {
        this.isWeekend = isWeekend;
    }

    public boolean isWeekend() {
        return isWeekend;
    }
}
