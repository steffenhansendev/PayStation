package paystation.domain.decorator;

import org.junit.Before;
import org.junit.Test;
import paystation.domain.EmptyDuringTransactionException;
import paystation.domain.PayStation;
import paystation.domain.PayStationImpl;
import paystation.domain.coin.IllegalCoinException;
import paystation.domain.factory.AmericanLinearFactory;
import paystation.domain.factory.AmericanProgressiveFactory;
import paystation.domain.factory.TestingFactory;
import paystation.domain.receipt.Receipt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestRefuse11Times5Decorator {

    private PayStation payStation;


    @Before
    public void setUp() {
        payStation = new Refuse11Times5Decorator(new PayStationImpl(new AmericanLinearFactory()));
    }

   @Test
    public void adding11Times5CoinValueShouldBeRefused() throws IllegalCoinException {
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

    @Test
    public void refuse11times5DecoratorShouldIntegrateCorrectly() throws IllegalCoinException, EmptyDuringTransactionException {
        payStation.addPayment(5);
        assertEquals("Display must read 2 after adding coin value of 5", 2, payStation.readDisplay());
        payStation.cancel();
        assertEquals("Display must read 0 after cancelling", 0, payStation.readDisplay());
        assertTrue("A purchase must return an instance of Receipt", payStation.buy() instanceof Receipt);
        assertTrue("Emptying pay station must yield the coin that was added", payStation.empty().containsKey(5));
    }

    @Test (expected = IllegalCoinException.class)
    public void refuse11Times5DecoratorShouldReconfigureCorrectly() throws IllegalCoinException {
        payStation.addPayment(5);
        assertEquals("Display must read 2 after adding coin value of 5", 2, payStation.readDisplay());
        payStation.reconfigure(new TestingFactory());
        payStation.addPayment(5);
    }
}
