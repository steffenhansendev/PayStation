package paystation.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AlternatingRateStrategy implements RateStrategy {

    private RateStrategy currentState = new AmericanLinearRateStrategy();
    private RateStrategy weekdayRateStrategy;
    private RateStrategy weekendRateStrategy;

    public AlternatingRateStrategy(RateStrategy weekdayRateStrategy, RateStrategy weekendRateStrategy) {
        this.weekdayRateStrategy = weekdayRateStrategy;
        this.weekendRateStrategy = weekendRateStrategy;
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
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY;
    }
}
