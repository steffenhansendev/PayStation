package paystation.domain.receipt;

import java.io.PrintStream;

public class NoAdditionalInfoPrinter implements AdditionalInfoPrinter {

    private static AdditionalInfoPrinter instance;

    public static AdditionalInfoPrinter getInstance() {
        if (instance == null) {
            instance = new NoAdditionalInfoPrinter();
        }
        return instance;
    }

    private NoAdditionalInfoPrinter(){}

    @Override
    public void print(PrintStream printStream) {
        // Do nothing
    }
}