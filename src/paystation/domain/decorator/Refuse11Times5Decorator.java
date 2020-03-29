package paystation.domain.decorator;

import paystation.domain.EmptyDuringTransactionException;
import paystation.domain.PayStation;
import paystation.domain.PayStationImpl;
import paystation.domain.coin.IllegalCoinException;
import paystation.domain.factory.PayStationFactory;
import paystation.domain.receipt.Receipt;
import java.util.Map;

public class Refuse11Times5Decorator implements PayStation {
    private PayStation component;
    private int numberOf5Additions;

    public Refuse11Times5Decorator(PayStationImpl component) {
        this.component = component;
        numberOf5Additions = 0;
    }

    @Override
    public void addPayment(int coinValue) throws IllegalCoinException {
        if (numberOf5Additions < 10) {
            component.addPayment(coinValue);
            numberOf5Additions++;
        }
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