package paystation.domain.receipt;

import java.io.PrintStream;

// TODO: Should be implemented as SINGLETON
public class BarCodeAdditionalInfoPrinter implements AdditionalInfoPrinter {
    @Override
    public void print(PrintStream printStream) {
        printStream.println("|| |   | | ||   ||| | |    |  |||  |     | | || ||");
    }
}