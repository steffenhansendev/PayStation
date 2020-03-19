package paystation.domain.receipt;

import java.io.PrintStream;

public class NoAdditionalInfoPrinter implements AdditionalInfoPrinter {
    @Override
    public void print(PrintStream printStream) {
        // Do nothing
    }
}
