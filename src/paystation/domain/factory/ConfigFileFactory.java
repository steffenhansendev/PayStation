package paystation.domain.factory;

import paystation.domain.coin.CoinStrategy;
import paystation.domain.display.DisplayStrategy;
import paystation.domain.rate.RateStrategy;
import paystation.domain.receipt.AdditionalInfoPrinter;
import paystation.domain.receipt.Receipt;
import paystation.domain.receipt.ReceiptImpl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class ConfigFileFactory implements PayStationFactory {

    private InputStream inputStream;
    private Properties properties = new Properties();

    AdditionalInfoPrinter additionalInfoPrinter = null;

    public ConfigFileFactory() {
        try {
            inputStream = new FileInputStream("res/config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public RateStrategy createRateStrategy() {
        RateStrategy rateStrategy = null;
        Class<?> classType;
        Constructor<?> constructor;
        try {
            classType = Class.forName(properties.getProperty("ps.ratestrategy"));
            constructor = classType.getConstructor();
            rateStrategy = (RateStrategy) constructor.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return rateStrategy;
    }

    @Override
    public CoinStrategy createCoinStrategy() {
        CoinStrategy coinStrategy = null;
        Class<?> classType;
        Constructor<?> constructor;
        try {
            classType = Class.forName(properties.getProperty("ps.coinstrategy"));
            constructor = classType.getConstructor();
            coinStrategy = (CoinStrategy) constructor.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return coinStrategy;
    }

    @Override
    public Receipt createReceipt(int boughtParkingTime) {
        if (additionalInfoPrinter == null) {
            Class<?> classType;
            Method method;
            try {
                classType = Class.forName(properties.getProperty("ps.additionalinfoprinter"));
                method = classType.getMethod("getInstance");
                additionalInfoPrinter = (AdditionalInfoPrinter) method.invoke(null, null);
            } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return new ReceiptImpl(boughtParkingTime, additionalInfoPrinter);
    }

    @Override
    public DisplayStrategy createDisplayStrategy() {
        DisplayStrategy displayStrategy = null;
        Class<?> classType;
        Constructor<?> constructor;
        try {
            classType = Class.forName(properties.getProperty("ps.displaystrategy"));
            constructor = classType.getConstructor();
            displayStrategy = (DisplayStrategy) constructor.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return displayStrategy;
    }
}
