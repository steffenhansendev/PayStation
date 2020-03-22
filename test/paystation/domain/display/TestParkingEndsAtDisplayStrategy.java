package paystation.domain.display;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class TestParkingEndsAtDisplayStrategy {
    private DisplayStrategy displayStrategy;

    @Before
    public void setUp() {
        displayStrategy = new ParkingEndsAtDisplayStrategy();
    }

    @Test
    public void buying10minutesWorthOfParkingTimeShouldYield10MinuteDelayFromNow() {
        int parkingTimeBought = 10;
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.add(Calendar.MINUTE, 10);
        int expectedTime = calendar.get(Calendar.HOUR_OF_DAY) * 100 + calendar.get(Calendar.MINUTE);
        assertEquals("Expiration of parking time must be 10 minutes from now", expectedTime, displayStrategy.readDisplay(parkingTimeBought));
    }
}
