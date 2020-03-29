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
