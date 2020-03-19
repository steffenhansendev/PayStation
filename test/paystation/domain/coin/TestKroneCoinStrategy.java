package paystation.domain.coin;

import org.junit.Before;
import org.junit.Test;
import paystation.domain.coin.CoinStrategy;
import paystation.domain.coin.IllegalCoinException;
import paystation.domain.coin.KroneCoinStrategy;

import java.util.LinkedHashMap;
import java.util.Map;
import static junit.framework.Assert.*;

public class TestKroneCoinStrategy {
    private CoinStrategy coinStrategy;

    @Before
    public void setUp() {
        coinStrategy = new KroneCoinStrategy();
    }

    @Test
    public void _1KroneShouldBeAccepted() throws IllegalCoinException {
        assertTrue("1 krone must be a valid coin", coinStrategy.isCoinValid(1));
    }

    @Test
    public void _2KronerShouldBeAccepted() throws IllegalCoinException {
        assertTrue("2 kroner must be a valid coin", coinStrategy.isCoinValid(2));
    }

    @Test
    public void _5KronerShouldBeAccepted() throws IllegalCoinException {
        assertTrue("5 kroner must be a valid coin", coinStrategy.isCoinValid(5));
    }

    @Test
    public void _10KronerShouldBeAccepted() throws IllegalCoinException {
        assertTrue("10 kroner must be a valid coin", coinStrategy.isCoinValid(10));
    }

    @Test
    public void _20KronerShouldBeAccepted() throws IllegalCoinException {
        assertTrue("20 kroner must be a valid coin", coinStrategy.isCoinValid(20));
    }

    @Test (expected = IllegalCoinException.class)
    public void _25KronerShouldBeAccepted() throws IllegalCoinException {
        coinStrategy.isCoinValid(25);
    }

    @Test
    public void allValidKronerShouldBeMappedTo0WhenInitializing() {
        Map<Integer, Integer> expectedMap = new LinkedHashMap<Integer, Integer>();
        expectedMap.put(1, 0);
        expectedMap.put(2, 0);
        expectedMap.put(5, 0);
        expectedMap.put(10, 0);
        expectedMap.put(20, 0);
        Map<Integer, Integer> inputMap = new LinkedHashMap<Integer, Integer>();
        coinStrategy.initializeCoins(inputMap);
        assertEquals("Every unique krone coin must be mapped to 0 when initializing", expectedMap, inputMap);
    }
}