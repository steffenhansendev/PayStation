package paystation.domain;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class TestStandardReceipt {

    private Receipt receipt;
    private ByteArrayOutputStream byteArrayOutputStream;
    private PrintStream printStream;

    @Before
    public void setUp(){
    }

    @Test
    public void standardLayoutReceiptShouldPrintForManualAssertion() {
        System.out.println("For manual assertion of standard layout receipt: ");
        receipt = new StandardReceipt(30);
        receipt.print(System.out);
    }

    @Test
    public void standardLayoutReceiptShouldPrint() {
        // PrintStream object to contain indirect output
        byteArrayOutputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(byteArrayOutputStream);
        // Get a receipt with 30 minutes wort of parking time
        receipt = new ReceiptImpl(30, new NoAdditionalInfoPrinter());
        receipt.print(printStream);
        String output = byteArrayOutputStream.toString();
        // Inspect PrintStream object
        String[] outputAsLines = output.split("\n");
        assertEquals("Receipt must be printed on 5 separate lines", 5, outputAsLines.length);
        assertEquals("---", outputAsLines[0].substring(0, 3));
        assertEquals("---", outputAsLines[4].substring(0, 3));
        assertEquals("P A R K I N G", outputAsLines[1].substring(10, 23));
        assertEquals("030", outputAsLines[2].substring(19, 22));
        String parkedAtTime = outputAsLines[3].substring(29, 34);
        assertEquals(':', parkedAtTime.charAt(2));
        // Will throw exception if hour is not an integer
        Integer.parseInt(parkedAtTime.substring(0, 2));
        // Will throw exception if minute is not an integer
        Integer.parseInt(parkedAtTime.substring(3, 5));
    }
}
