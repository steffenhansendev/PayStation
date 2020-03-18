package paystation.domain;

import java.io.PrintStream;

public class NoAdditionalInfoPrinter implements AdditionalInfoPrinter {
    @Override
    public void print(PrintStream printStream) {
        // Do nothing
    }
}
