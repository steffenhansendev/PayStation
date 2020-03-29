package paystation.domain.decorator;

import org.junit.Before;
import org.junit.Test;
import paystation.domain.EmptyDuringTransactionException;
import paystation.domain.PayStation;
import paystation.domain.PayStationImpl;
import paystation.domain.coin.IllegalCoinException;
import paystation.domain.factory.AmericanLinearFactory;
import paystation.domain.factory.TestingFactory;
import paystation.domain.receipt.Receipt;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestLogDecorator {

    private PayStation payStation;
    private PrintStream testPrintStream;
    private ByteArrayOutputStream byteArrayOutputStream;

    @Before
    public void setUp() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        testPrintStream = new PrintStream(byteArrayOutputStream);
        payStation = new LogDecorator(testPrintStream, new PayStationImpl(new TestingFactory()));
    }

    @Test
    public void testTest() {
        System.out.println(new Date().toString());
    }

    @Test
    public void adding3ShouldPrint3AndCurrentDateAndTime() throws IllegalCoinException {
        payStation.addPayment(3);
        String output = byteArrayOutputStream.toString();
        System.out.println(output);
        Date now = new Date();
        assertTrue(output.contains("Payment of 3 was added at: "));
        assertTrue(output.contains(now.toString()));
        Pattern pattern = Pattern.compile("[0-9]\\s[0-2][0-9]:[0-5][0-9]:[0-5][0-9]");
        assertTrue(pattern.matcher(output).find());
    }

    @Test
    public void logDecoratorShouldIntegrateCorrectly() throws IllegalCoinException, EmptyDuringTransactionException {
        payStation.addPayment(3);
        assertEquals("Display must read 3 after adding coin value of 3", 3, payStation.readDisplay());
        payStation.cancel();
        assertEquals("Display must read 0 after cancelling", 0, payStation.readDisplay());
        assertTrue("A purchase must return an instance of Receipt", payStation.buy() instanceof Receipt);
        assertTrue("", payStation.empty().containsKey(3));
    }

    @Test (expected = IllegalCoinException.class)
    public void logDecoratorShouldReconfigureCorrectly() throws IllegalCoinException {
        payStation.addPayment(3);
        assertEquals("Display must read 3 after adding coin value of 3", 3, payStation.readDisplay());
        payStation.reconfigure(new AmericanLinearFactory());
        payStation.addPayment(3);
    }
}
