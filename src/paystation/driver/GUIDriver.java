package paystation.driver;

import paystation.domain.PayStationImpl;
import paystation.domain.factory.ConfigFileFactory;
import paystation.view.PayStationGUI;

import javax.swing.*;

public class GUIDriver {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new PayStationGUI(new PayStationImpl(new ConfigFileFactory()));
    }
}