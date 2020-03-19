package paystation.domain.coin;

import org.junit.Before;
import org.junit.Test;
import paystation.domain.coin.CentCoinStrategy;
import paystation.domain.coin.CoinStrategy;
import paystation.domain.coin.IllegalCoinException;

import java.util.LinkedHashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class TestCentCoinStrategy {
    CoinStrategy coinStrategy;
    @Before
    public void setUp() {
        coinStrategy = new CentCoinStrategy();
    }

    @Test
    public void _5CentsShouldBeAccepted() throws IllegalCoinException {
        assertTrue("5 cents must be a valid coin", coinStrategy.isCoinValid(5));
    }

    @Test
    public void _10CentsShouldBeAccepted() throws IllegalCoinException {
        assertTrue("10 must be a valid coin", coinStrategy.isCoinValid(10));
    }

    @Test(expected = IllegalCoinException.class)
    public void _17CentsShouldBeRejected() throws IllegalCoinException {
        coinStrategy.isCoinValid(17);
    }

    @Test
    public void _25CentsShouldBeAccepted() throws IllegalCoinException {
        assertTrue("25 cents must a valid coin", coinStrategy.isCoinValid(25));
    }

    @Test
    public void allValidCentsShouldBeMappedTo0WhenInitializing() {
        Map<Integer, Integer> expectedMap = new LinkedHashMap<Integer, Integer>();
        expectedMap.put(5, 0);
        expectedMap.put(10, 0);
        expectedMap.put(25, 0);
        Map<Integer, Integer> inputMap = new LinkedHashMap<Integer, Integer>();
        coinStrategy.initializeCoins(inputMap);
        assertEquals("Every unique cent coin must be mapped to 0 when initializing", expectedMap, inputMap);
    }
}
