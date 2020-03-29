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

public class TestDecoratorChain {

    private PayStation payStation;
    private PrintStream testPrintStream;
    private ByteArrayOutputStream byteArrayOutputStream;

    @Before
    public void setUp() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        testPrintStream = new PrintStream(byteArrayOutputStream);
        payStation = new Refuse11Times5Decorator(
                new LogDecorator(
                        testPrintStream, new PayStationImpl(
                                new TestingFactory()
                        )
                )
        );
    }

    @Test
    public void adding3ShouldPrint3AndCurrentDateAndTime() throws IllegalCoinException {
        payStation.addPayment(3);
        String output = byteArrayOutputStream.toString();
        Date now = new Date();
        assertTrue(output.contains("Payment of 3 was added at: "));
        assertTrue(output.contains(now.toString()));
        Pattern pattern = Pattern.compile("[0-9]\\s[0-2][0-9]:[0-5][0-9]:[0-5][0-9]");
        assertTrue(pattern.matcher(output).find());
    }

    @Test
    public void decoratorChainShouldIntegrateCorrectly() throws IllegalCoinException, EmptyDuringTransactionException {
        payStation.addPayment(3);
        assertEquals("Display must read 3 after adding coin value of 3", 3, payStation.readDisplay());
        payStation.cancel();
        assertEquals("Display must read 0 after cancelling", 0, payStation.readDisplay());
        assertTrue("A purchase must return an instance of Receipt", payStation.buy() instanceof Receipt);
        assertTrue("Emptying pay station must yield the coin that was added", payStation.empty().containsKey(3));
    }

    @Test
    public void adding11Times5CoinValueShouldBeRefused() throws IllegalCoinException {
        payStation = new Refuse11Times5Decorator(
                new LogDecorator(
                        testPrintStream, new PayStationImpl(
                        new AmericanLinearFactory()
                )
                )
        );
        payStation.addPayment(5);
        payStation.addPayment(5);
        payStation.addPayment(5);
        payStation.addPayment(5);
        payStation.addPayment(5);
        payStation.addPayment(5);
        payStation.addPayment(5);
        payStation.addPayment(5);
        payStation.addPayment(5);
        payStation.addPayment(5);
        payStation.addPayment(5);
        assertEquals("Adding a coin value of 5 times 10 must yield 20 minutes parking time", 10 * 2, payStation.readDisplay());
    }

    @Test (expected = IllegalCoinException.class)
    public void decoratorChainShouldReconfigureCorrectly() throws IllegalCoinException {
        payStation.addPayment(3);
        assertEquals("Display must read 3 after adding coin value of 3", 3, payStation.readDisplay());
        payStation.reconfigure(new AmericanLinearFactory());
        payStation.addPayment(3);
    }


}
