package paystation.domain.display;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ParkingEndsAtDisplayStrategy implements DisplayStrategy {
    @Override
    public int readDisplay(int input) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.add(Calendar.MINUTE, input);
        return calendar.get(Calendar.HOUR_OF_DAY) * 100 + calendar.get(Calendar.MINUTE);
    }
}
