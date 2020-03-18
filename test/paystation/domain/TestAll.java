package paystation.domain;

import org.junit.runner.*;
import org.junit.runners.Suite;

@RunWith(Suite.class)
    @Suite.SuiteClasses(
            {
                    TestPayStation.class,
                    TestIntegration.class,
                    TestAmericanLinearRateStrategy.class,
                    TestAmericanProgressiveRateStrategy.class,
                    TestKroneCoinStrategy.class,
                    TestCentCoinStrategy.class,
                    TestDanishLinearRateStrategy.class,
                    TestDanishProgressiveRateStrategy.class,
                    TestAlternatingRate.class,
                    TestReceipt.class
            }
    )

public class TestAll {
}