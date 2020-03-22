package paystation.domain.receipt;

import java.io.PrintStream;

// TODO: Should be implemented as SINGLETON
public class NoAdditionalInfoPrinter implements AdditionalInfoPrinter {
    @Override
    public void print(PrintStream printStream) {
        // Do nothing
    }
}