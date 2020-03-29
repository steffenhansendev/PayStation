package paystation.domain.decorator;

import paystation.domain.EmptyDuringTransactionException;
import paystation.domain.PayStation;
import paystation.domain.coin.IllegalCoinException;
import paystation.domain.factory.PayStationFactory;
import paystation.domain.receipt.Receipt;

import java.util.Map;

public abstract class PayStationDecorator implements PayStation {
    private PayStation component;

    public PayStationDecorator(PayStation component) {
        this.component = component;
    }

    @Override
    public void addPayment(int coinValue) throws IllegalCoinException {
        component.addPayment(coinValue);
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
