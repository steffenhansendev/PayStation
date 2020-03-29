package paystation.domain.decorator;

import paystation.domain.EmptyDuringTransactionException;
import paystation.domain.PayStation;
import paystation.domain.PayStationImpl;
import paystation.domain.coin.IllegalCoinException;
import paystation.domain.factory.PayStationFactory;
import paystation.domain.receipt.Receipt;

import java.io.PrintStream;
import java.util.Date;
import java.util.Map;

public class LogDecorator implements PayStation {
    private PayStation component;
    private PrintStream printStream;

    public LogDecorator(PrintStream printStream, PayStation component) {
        this.component = component;
        this.printStream = printStream;
    }

    @Override
    public void addPayment(int coinValue) throws IllegalCoinException {
        component.addPayment(coinValue);
        printStream.println("Payment of "+coinValue+" was added at: "+ new Date());
    }

    @Override
    public int readDisplay() {
        return component.readDisplay();
    }

    @Override
    public Receipt buy() {
        return component.buy();
    }

    @Override
    public Map<Integer, Integer> cancel() {
        return component.cancel();
    }

    @Override
    public Map<Integer, Integer> empty() throws EmptyDuringTransactionException {
        return component.empty();
    }

    @Override
    public void reconfigure(PayStationFactory payStationFactory) {
        component.reconfigure(payStationFactory);
    }
}
