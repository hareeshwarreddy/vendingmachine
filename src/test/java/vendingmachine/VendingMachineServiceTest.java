package vendingmachine;


import com.assignment.vendingmachine.coin.CoinEnum;
import com.assignment.vendingmachine.exception.NoSufficientFundsException;
import com.assignment.vendingmachine.service.VendingMachineService;
import com.assignment.vendingmachine.service.VendingMachineServiceImpl;
import com.assignment.vendingmachine.state.State;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

public class VendingMachineServiceTest {

    TreeMap<CoinEnum, Integer> coins;
    VendingMachineService vendingMachineService;
    @Before
    public  void setUp() {
         coins = new TreeMap<>(Collections.reverseOrder());
        coins.put(CoinEnum.ONE_PENCE,4);
        coins.put(CoinEnum.TWO_PENCE,5);
        vendingMachineService = new VendingMachineServiceImpl(State.NOT_READY);
        vendingMachineService.initialiseMachine(coins);
    }
    @Test
    public void testInitialLoadOfMachine() {
        assertEquals(14,calculatecoinsTotal(coins));
        assertEquals(State.READY,vendingMachineService.getCurrentState()) ;
    }
    @Test
    public void testRegisterCoins() {
        coins.put(CoinEnum.FIFTY_PENCE,3);
        vendingMachineService.addMoreCoinsToMachine(coins);
        assertEquals(164,calculatecoinsTotal(coins));
        assertEquals(State.READY,vendingMachineService.getCurrentState()) ;
    }

    @Test
    public void testDispenceCoinsSuccessfully() throws Exception {
        vendingMachineService.dispenseCoins(9);
        assertEquals(State.READY,vendingMachineService.getCurrentState()) ;
    }

    @Test
    public void testDispenceCoinsSuccessfullyWithEmptyState() throws Exception {
        vendingMachineService.dispenseCoins(14);
        assertEquals(State.EMPTY,vendingMachineService.getCurrentState()) ;
    }

    @Test(expected = NoSufficientFundsException.class)
    public void testExceptionSceantioForDispenseCoins() throws Exception {
        vendingMachineService.dispenseCoins(15);

    }

    private int calculatecoinsTotal(Map<CoinEnum,Integer> coinsMap) {
         int[] total = new int[coinsMap.size()];
        AtomicInteger count=new AtomicInteger(0);
          coinsMap.keySet().forEach(coinEnum -> {
              total[count.getAndIncrement()] = coinEnum.getCoinValue()*coinsMap.get(coinEnum);});
          return Arrays.stream(total).sum();
    }

}
