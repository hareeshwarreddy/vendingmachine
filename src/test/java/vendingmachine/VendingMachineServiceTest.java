package vendingmachine;


import com.assignment.vendingmachine.coin.CoinEnum;
import com.assignment.vendingmachine.exception.NoSufficientFundsException;
import com.assignment.vendingmachine.service.VendingMachineService;
import com.assignment.vendingmachine.service.VendingMachineServiceImpl;
import com.assignment.vendingmachine.state.State;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

public class VendingMachineServiceTest {

    TreeMap<CoinEnum, Integer> coins;
    VendingMachineService vendingMachineService;
    @Before
    public  void setUp() {
         coins = new TreeMap<>(Collections.reverseOrder());
        coins.put(CoinEnum.ONE_PENSE,4);
        coins.put(CoinEnum.TWO_PENSE,5);
        vendingMachineService = new VendingMachineServiceImpl(State.NOT_READY);
        vendingMachineService.initialiseMachine(coins);
    }
    @Test
    public void testInitialLoadOfMachine() {
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

}
