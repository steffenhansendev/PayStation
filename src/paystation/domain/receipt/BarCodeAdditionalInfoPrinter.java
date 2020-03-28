package paystation.domain.receipt;

import java.io.PrintStream;

public class BarCodeAdditionalInfoPrinter implements AdditionalInfoPrinter {

    private static AdditionalInfoPrinter instance;

    public static AdditionalInfoPrinter getInstance() {
        if (instance == null) {
            instance = new BarCodeAdditionalInfoPrinter();
        }
        return instance;
    }

    private BarCodeAdditionalInfoPrinter(){}

    @Override
    public void print(PrintStream printStream) {
        printStream.println("|| |   | | ||   ||| | |    |  |||  |     | | || ||");
    }
}