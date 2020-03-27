package paystation.view;

import paystation.domain.PayStation;
import paystation.domain.PayStationImpl;
import paystation.domain.coin.IllegalCoinException;
import paystation.domain.receipt.Receipt;
import softcollection.lcd.LCDDigitDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PayStationGUI extends JFrame {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception exception) {
            // Do nothing; the look and feel is not vital
        }
        new PayStationGUI();
    }

    private LCDDigitDisplay lcdDigitDisplay;

    private PayStation payStation;

    public PayStationGUI() {
        super("PayStation GUI");
        payStation = new PayStationImpl(new GUITestingFactory());
        JFrame.setDefaultLookAndFeelDecorated(true);
        setLocation(100,20);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(createCoinInputPanel(), BorderLayout.EAST);
        contentPane.add(createButtonPanel(), BorderLayout.SOUTH);
        contentPane.add(createDisplayPanel(), BorderLayout.CENTER);
        updateDisplay();
        pack();
        setVisible(true);
    }

    private void updateDisplay() {
        String prefixedZeroes = String.format("%4d", payStation.readDisplay());
        lcdDigitDisplay.set(prefixedZeroes);
    }

    private JComponent createCoinInputPanel() {
        JTextField textField = new JTextField(3);
        textField.addActionListener(coinInputActionListener);
        return textField;
    }

    private ActionListener coinInputActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent actionEvent) {
            String input = actionEvent.getActionCommand();
            int coin = Integer.parseInt(input);
            try {
                payStation.addPayment(coin);
            } catch (IllegalCoinException exception) {
                exception.printStackTrace();
            }
            JTextField textField = (JTextField) actionEvent.getSource();
            textField.setText("");
            updateDisplay();
        }
    };

    private JComponent createButtonPanel() {
        Box box = new Box(BoxLayout.X_AXIS);
        JButton jButton;

        jButton = new JButton("Cancel");
        jButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(Box.createHorizontalGlue());
        box.add(jButton);
        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                payStation.cancel();
                updateDisplay();
            }
        } );

        jButton = new JButton("Buy");
        jButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(Box.createHorizontalGlue());
        box.add(jButton);
        box.add(Box.createHorizontalGlue());
        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Receipt receipt = payStation.buy();
                updateDisplay();
                showReceiptWindow(receipt);
            }
        } );

        return box;
    }

    private JComponent createDisplayPanel() {
        lcdDigitDisplay = new LCDDigitDisplay();
        return lcdDigitDisplay;
    }

    private void showReceiptWindow(Receipt receipt) {
        JFrame jFrame = new JFrame("Receipt");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        receipt.print(printStream);
        String output = byteArrayOutputStream.toString();
        JTextArea text = new JTextArea(5, 20);
        text.setEditable(false);
        text.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        text.append(output);
        jFrame.getContentPane().add(text);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}

