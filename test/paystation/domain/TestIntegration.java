package paystation.domain;

import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.PasswordAuthentication;

import static org.junit.Assert.*;

public class TestIntegration {
    private PayStation payStation;

    @Before
    public void setUp() {
        payStation = new PayStationImpl(new TestingFactory());
    }

    @Test
    public void testingRateStrategyShouldIntegrateCorrectly() throws IllegalCoinException {
        payStation = new PayStationImpl(new TestingFactory());
        payStation.addPayment(3);
        payStation.addPayment(7);
        payStation.addPayment(9);
        assertEquals("19 should yield 19 minutes parking time with TestingRateStrategy", 19, payStation.readDisplay());
    }

    @Test
    public void americanLinearRateStrategyShouldIntegrateCorrectly() throws IllegalCoinException {
        payStation = new PayStationImpl(new AmericanLinearFactory());
        add100Cents();   //100
        add100Cents();   //200
        add100Cents();   //300
        payStation.addPayment(25);  //325
        payStation.addPayment(25);  //350
        assertEquals("350 should yield 140 minutes parking time with AmericanLinearRateStrategy", 140, payStation.readDisplay());
    }

    @Test
    public void americanProgressiveRateStrategyShouldIntegrateCorrectly() throws IllegalCoinException {
        payStation = new PayStationImpl(new AmericanProgressiveFactory());
        add100Cents();   //100
        add100Cents();   //200
        add100Cents();   //300
        payStation.addPayment(25);  //325
        payStation.addPayment(25);  //350
        assertEquals("300 should yield yield 120 minutes parking time with AmericanProgressiveRateStrategy", 120, payStation.readDisplay());
    }

    @Test
    public void danishLinearRateStrategyShouldIntegrateCorrectly() throws IllegalCoinException {
        payStation = new PayStationImpl(new DanishLinearFactory());
        payStation.addPayment(20);
        payStation.addPayment(5);
        assertEquals("25 should yield 175 minutes parking time with DanishLinearRateStrategy", 175, payStation.readDisplay());
    }

    @Test
    public void danishProgressiveRateStrategyShouldIntegrateCorrectly() throws IllegalCoinException {
        payStation = new PayStationImpl(new DanishProgressiveFactory());
        payStation.addPayment(20);
        payStation.addPayment(5);
        assertEquals("25 should yield 145 minutes parking time with DanishProgressiveRateStrategy", 145, payStation.readDisplay());
    }

    @Test (expected = IllegalCoinException.class)
    public void testingCoinStrategyShouldIntegrateCorrectly() throws IllegalCoinException {
        payStation = new PayStationImpl(new TestingFactory());
        payStation.addPayment(-1);
    }

    @Test (expected = IllegalCoinException.class)
    public void centCoinStrategyShouldIntegrateCorrectly() throws IllegalCoinException {
        payStation = new PayStationImpl(new AmericanLinearFactory());
        payStation.addPayment(1);
    }

    @Test (expected = IllegalCoinException.class)
    public void kroneCoinStrategyShouldIntegrateCorrectly() throws IllegalCoinException {
        payStation = new PayStationImpl(new DanishLinearFactory());
        payStation.addPayment(25);
    }

    @Test
    public void receiptShouldIntegrateCorrectly() throws IllegalCoinException {
        ByteArrayOutputStream byteArrayOutputStream;
        PrintStream printStream;
        Receipt receipt;
        String output;
        String[] outputAsLines;

        payStation = new PayStationImpl(new TestingFactory());
        payStation.addPayment(7);
        receipt = payStation.buy();
        // PrintStream object to contain indirect output
        byteArrayOutputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(byteArrayOutputStream);
        // Get a receipt with 30 minutes wort of parking time
        receipt.print(printStream);
        output = byteArrayOutputStream.toString();
        // Inspect PrintStream object
        outputAsLines = output.split("\n");
        assertEquals("Receipt must be printed on 5 separate lines when it does not contain bar code", 5, outputAsLines.length);
        assertEquals("007", outputAsLines[2].substring(19, 22));
        assertFalse("Receipt must not contain '|' when it does not contain bar code", output.contains("|"));

        payStation = new PayStationImpl(new TestingFactory(new BarCodeAdditionalInfoPrinter()));
        payStation.addPayment(7);
        receipt = payStation.buy();
        // PrintStream object to contain indirect output
        byteArrayOutputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(byteArrayOutputStream);
        // Get a receipt with 30 minutes wort of parking time
        receipt.print(printStream);
        output = byteArrayOutputStream.toString();
        // Inspect PrintStream object
        outputAsLines = output.split("\n");
        assertEquals("Receipt must be printed on 6 separate lines when it contains bar code", 6, outputAsLines.length);
        assertEquals("007", outputAsLines[2].substring(19, 22));
        assertTrue("Receipt must contain '|' when it contains bar code", output.contains("|"));
    }

    private void add100Cents() throws IllegalCoinException {
        payStation.addPayment(25);
        payStation.addPayment(25);
        payStation.addPayment(25);
        payStation.addPayment(25);
    }
}