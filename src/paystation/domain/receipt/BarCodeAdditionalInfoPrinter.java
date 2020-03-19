package paystation.domain.receipt;

import java.io.PrintStream;

public class BarCodeAdditionalInfoPrinter implements AdditionalInfoPrinter {
    @Override
    public void print(PrintStream printStream) {
        printStream.println("|| |   | | ||   ||| | |    |  |||  |     | | || ||");
    }
}
