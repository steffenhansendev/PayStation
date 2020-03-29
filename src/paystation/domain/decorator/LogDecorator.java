package paystation.domain.decorator;

import paystation.domain.PayStation;
import paystation.domain.coin.IllegalCoinException;

import java.io.PrintStream;
import java.util.Date;


public class LogDecorator extends PayStationDecorator {
    private PrintStream printStream;

    public LogDecorator(PrintStream printStream, PayStation component) {
        super(component);
        this.printStream = printStream;
    }

    @Override
    public void addPayment(int coinValue) throws IllegalCoinException {
        super.addPayment(coinValue);
        printStream.println("Payment of "+coinValue+" was added at: "+ new Date());
    }
}
