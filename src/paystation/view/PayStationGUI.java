package paystation.view;

import paystation.domain.PayStation;
import paystation.domain.PayStationImpl;
import paystation.domain.coin.IllegalCoinException;
import paystation.domain.factory.AmericanLinearFactory;
import paystation.domain.factory.AmericanProgressiveFactory;
import paystation.domain.factory.PayStationFactory;
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
        //setJMenuBar(createAllMenus());
        pack();
        setVisible(true);
    }

    private void updateDisplay() {
        String prefixedZeroes = String.format("%4d", payStation.readDisplay());
        lcdDigitDisplay.set(prefixedZeroes);
    }

    private JComponent createCoinInputPanel() {
        Box box = new Box(BoxLayout.Y_AXIS);
        // Coupling to specific coin strategy
        box.add(defineButton(" 5c", "5"));
        box.add(defineButton("10 c", "10"));
        box.add(defineButton("25 c", "25"));
        return box;
    }

    private ActionListener buttonActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent actionEvent) {
            String string = actionEvent.getActionCommand();
            int coin = Integer.parseInt(string);
            try {
                payStation.addPayment(coin);
            } catch (IllegalCoinException exception) {

            }
            updateDisplay();
        }
    };

    private JButton defineButton(String text, String actionCommand) {
        JButton jButton;
        jButton = new JButton(text);
        jButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        jButton.setActionCommand(actionCommand);
        jButton.addActionListener(buttonActionListener);
        return jButton;
    }

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
        String[] lines = byteArrayOutputStream.toString().split("\n");
        JTextArea text = new JTextArea(lines.length, 20);
        text.setEditable(false);
        text.setFont(new Font("DialogInput", Font.PLAIN, 14));
        for (int i = 0; i < lines.length; i++) {
            text.append(lines[i]+"\n");
        }
        jFrame.getContentPane().add(text);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}

