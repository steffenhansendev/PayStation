package paystation.domain.display;

public class ParkingTimeDisplayStrategy implements DisplayStrategy {
    @Override
    public int readDisplay(int input) {
        return input;
    }
}
