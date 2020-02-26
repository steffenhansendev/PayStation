package paystation.domain;

import org.junit.*;
import static org.junit.Assert.*;

public class TestIntegration {
    private PayStation payStation;

    @Before
    public void setUp() {
        payStation = new PayStationImpl(new TestingRateStrategy(), new CentCoinStrategy());
    }

    @Test
    public void testingRateStrategyShouldIntegrateCorrectly() throws IllegalCoinException {
        payStation = new PayStationImpl(new TestingRateStrategy(), new TestingCoinStrategy());
        payStation.addPayment(3);
        payStation.addPayment(7);
        payStation.addPayment(9);
        assertEquals("19 should yield 19 minutes parking time with TestingRateStrategy", 19, payStation.readDisplay());
    }

    @Test
    public void americanLinearRateStrategyShouldIntegrateCorrectly() throws IllegalCoinException {
        payStation = new PayStationImpl(new AmericanLinearRateStrategy(), new CentCoinStrategy());
        add100Cents();   //100
        add100Cents();   //200
        add100Cents();   //300
        payStation.addPayment(25);  //325
        payStation.addPayment(25);  //350
        assertEquals("350 should yield 140 minutes parking time with AmericanLinearRateStrategy", 140, payStation.readDisplay());
    }

    @Test
    public void americanProgressiveRateStrategyShouldIntegrateCorrectly() throws IllegalCoinException {
        payStation = new PayStationImpl(new AmericanProgressiveRateStrategy(), new CentCoinStrategy());
        add100Cents();   //100
        add100Cents();   //200
        add100Cents();   //300
        payStation.addPayment(25);  //325
        payStation.addPayment(25);  //350
        assertEquals("300 should yield yield 120 minutes parking time with AmericanProgressiveRateStrategy", 120, payStation.readDisplay());
    }

    @Test
    public void danishLinearRateStrategyShouldIntegrateCorrectly() throws IllegalCoinException {
        payStation = new PayStationImpl(new DanishLinearRateStrategy(), new KroneCoinStrategy());
        payStation.addPayment(20);
        payStation.addPayment(5);
        assertEquals("25 should yield 175 minutes parking time with DanishLinearRateStrategy", 175, payStation.readDisplay());
    }

    @Test
    public void danishProgressiveRateStrategyShouldIntegrateCorrectly() throws IllegalCoinException {
        payStation = new PayStationImpl(new DanishProgressiveRateStrategy(), new KroneCoinStrategy());
        payStation.addPayment(20);
        payStation.addPayment(5);
        assertEquals("25 should yield 145 minutes parking time with DanishProgressiveRateStrategy", 145, payStation.readDisplay());
    }

    @Test (expected = IllegalCoinException.class)
    public void testingCoinStrategyShouldIntegrateCorrectly() throws IllegalCoinException {
        payStation = new PayStationImpl(new TestingRateStrategy(), new TestingCoinStrategy());
        payStation.addPayment(-1);
    }

    @Test (expected = IllegalCoinException.class)
    public void centCoinStrategyShouldIntegrateCorrectly() throws IllegalCoinException {
        payStation = new PayStationImpl(new TestingRateStrategy(), new CentCoinStrategy());
        payStation.addPayment(1);
    }

    @Test (expected = IllegalCoinException.class)
    public void kroneCoinStrategyShouldIntegrateCorrectly() throws IllegalCoinException {
        payStation = new PayStationImpl(new TestingRateStrategy(), new KroneCoinStrategy());
        payStation.addPayment(25);
    }

    @Test
    public void AlternatingRateStrategyShouldIntegrateCorrectly() throws IllegalCoinException {
        payStation = new PayStationImpl(new AlternatingRateStrategy(new AmericanLinearRateStrategy(), new AmericanProgressiveRateStrategy(), new FixedWeekendDetectionStrategy(false)), new CentCoinStrategy());
        add100Cents();
        add100Cents();
        add100Cents();
        add100Cents();
        add100Cents();
        assertEquals("500 cents must yield 120 minutes parking time during weekdays", 200, payStation.readDisplay());
        payStation = new PayStationImpl(new AlternatingRateStrategy(new AmericanLinearRateStrategy(), new AmericanProgressiveRateStrategy(), new FixedWeekendDetectionStrategy(true)), new CentCoinStrategy());
        add100Cents();
        add100Cents();
        add100Cents();
        add100Cents();
        add100Cents();
        assertEquals("500 cents must yield 200 minutes parking time during weekends", 150, payStation.readDisplay());

    }

    private void add100Cents() throws IllegalCoinException {
        payStation.addPayment(25);
        payStation.addPayment(25);
        payStation.addPayment(25);
        payStation.addPayment(25);
    }
}