package paystation.domain;

import java.io.PrintStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ReceiptImpl implements Receipt {
    private int boughtParkingTime;

    public ReceiptImpl(int boughtParkingTime) {
        this.boughtParkingTime = boughtParkingTime;
    }

    public int value() {
        return boughtParkingTime;
    }

    public void print(PrintStream printStream){
        String boughtParkingTime = this.boughtParkingTime + "";
        int numberOfDigitsInBoughtParkingTime = boughtParkingTime.length();
        switch (numberOfDigitsInBoughtParkingTime) {
            case 1:
                boughtParkingTime = "00" + boughtParkingTime;
                break;
            case 2:
                boughtParkingTime = "0" + boughtParkingTime;
                break;
            default:
                throw new UnsupportedOperationException();
        }
        Calendar calendarNow = GregorianCalendar.getInstance();
        String hour = prependZeroTo(calendarNow.get(Calendar.HOUR_OF_DAY) + "");
        String minute = prependZeroTo(calendarNow.get(Calendar.MINUTE) + "");
        String now = hour + ":" + minute;
        printStream.println("--------------------------------------------------");
        printStream.println("-------   P A R K I N G   R E C E I P T    -------");
        printStream.println("                   " + boughtParkingTime + " minutes                ");
        printStream.println("               Car parked at " + now );
        printStream.println("--------------------------------------------------");
    }

    private String prependZeroTo(String string){
        if (string.length() == 1) {
            string = "0" + string;
        }
        return string;
    }
}