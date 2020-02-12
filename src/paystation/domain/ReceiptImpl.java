package paystation.domain;

public class ReceiptImpl implements Receipt {
    private int boughtParkingTime;

    public ReceiptImpl(int boughtParkingTime) {
        this.boughtParkingTime = boughtParkingTime;
    }

    public int value() {
        return boughtParkingTime;
    }
}