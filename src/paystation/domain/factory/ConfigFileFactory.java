package paystation.domain.factory;

import paystation.domain.coin.CoinStrategy;
import paystation.domain.display.DisplayStrategy;
import paystation.domain.rate.RateStrategy;
import paystation.domain.receipt.Receipt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class ConfigFileFactory implements PayStationFactory {

    private InputStream inputStream;
    private Properties properties = new Properties();

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
        return null;
    }

    @Override
    public Receipt createReceipt(int boughtParkingTime) {
        return null;
    }

    @Override
    public DisplayStrategy createDisplayStrategy() {
        return null;
    }
}
