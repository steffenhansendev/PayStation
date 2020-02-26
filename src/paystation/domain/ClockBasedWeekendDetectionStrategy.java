package paystation.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ClockBasedWeekendDetectionStrategy implements WeekendDetectionStrategy {
    public boolean isWeekend() {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY;
    }
}
