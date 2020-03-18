package paystation.domain;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class TestBarCodeReceipt {

    private Receipt receipt;
    private ByteArrayOutputStream byteArrayOutputStream;
    private PrintStream printStream;

    @Before
    public void setUp(){
    }

    @Test
    public void barCodeLayoutReceiptShouldPrint() {
        // PrintStream object to contain indirect output
        byteArrayOutputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(byteArrayOutputStream);
        // Get a receipt with 30 minutes wort of parking time
        receipt = new BarCodeReceipt(30);
        receipt.print(printStream);
        String output = byteArrayOutputStream.toString();
        // Inspect PrintStream object
        assertTrue(output.contains("|"));
    }
}
