package paystation.domain.decorator;

import paystation.domain.PayStationImpl;
import paystation.domain.coin.IllegalCoinException;


public class Refuse11Times5Decorator extends PayStationDecorator {
    private int numberOf5Additions;

    public Refuse11Times5Decorator(PayStationImpl component) {
        super(component);
        numberOf5Additions = 0;
    }

    @Override
    public void addPayment(int coinValue) throws IllegalCoinException {
        if (numberOf5Additions < 10) {
            super.addPayment(coinValue);
            numberOf5Additions++;
        }
    }
}