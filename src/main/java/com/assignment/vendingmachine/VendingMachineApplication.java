package com.assignment.vendingmachine;


import com.assignment.vendingmachine.coin.CoinEnum;
import com.assignment.vendingmachine.exception.NoSufficientFundsException;
import com.assignment.vendingmachine.service.VendingMachineService;
import com.assignment.vendingmachine.service.VendingMachineServiceImpl;
import com.assignment.vendingmachine.state.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


/**
 * Main class to demonstrate vending API
 */
public class VendingMachineApplication {

    private static final Logger logger = LoggerFactory.getLogger(VendingMachineApplication.class);
    private Map<CoinEnum,Integer> coinMap;


    public static void main(String[] args) throws Exception {
        VendingMachineApplication app = new VendingMachineApplication();
        VendingMachineService vendingMachineService = new VendingMachineServiceImpl(State.NOT_READY);
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n");
        System.out.println(">> Select an Option from below<<");
        boolean execute  = true;
        TreeMap<CoinEnum, Integer> map = new TreeMap<>(Collections.reverseOrder());
        while(execute) {
            if (vendingMachineService.getCurrentState().equals(State.NOT_READY)) {
                System.out.println("1:      Load the vending machine with set of coins");
            }
            if (vendingMachineService.getCurrentState().equals(State.READY) || vendingMachineService.getCurrentState().equals(State.EMPTY)) {
                System.out.println("2:      register coins form user");
            }
            if (vendingMachineService.getCurrentState().equals(State.READY)) {
                System.out.println("3:      dispense the coins for given number");
            }
            System.out.println("4:      To Exit");
            String option = scanner.next().trim();


            switch (option) {
                case "1":
                    app.addCoins(map, scanner);
                    vendingMachineService.initialiseMachine(map);
                    break;
                case "2":
                    app.addCoins(map, scanner);
                    vendingMachineService.addMoreCoinsToMachine(map);
                    break;
                case "3":
                    try {
                        EnumMap<CoinEnum, Integer> outputMap = vendingMachineService.dispenseCoins(app.dispenseCoins(scanner));
                        app.dispenseCoinsToUser(outputMap);
                    } catch (NoSufficientFundsException ex) {
                        System.out.println("Sorry!!..there are no sufficient coins in the machine to dispense..try with lesser amount");
                    }
                    break;
                case "4":
                    execute = false;

            }
        }

    }

    private void addCoins(Map<CoinEnum,Integer> map, Scanner scanner) {
        String coinType = "";
        while (!coinType.trim().equalsIgnoreCase("X")) {
            try {
                System.out.println(">> Pick the One of the coin type from 1,2,5,10,20,50   (or X to continue):");
                coinType = scanner.next().trim();
                if("X".equalsIgnoreCase(coinType)){
                    continue;
                }
                if(!CoinEnum.validate(Integer.valueOf(coinType))) {
                    System.out.println("only pick the right coin type");
                    continue;
                }

                System.out.println(">> enter the  number coins for coin type::"+coinType);
                String coins = scanner.next().trim();
                map.put(CoinEnum.getCoinEnum(Integer.valueOf(coinType)),Integer.valueOf(coins));

            } catch (NumberFormatException nfe) {
                System.out.println("error occurred during execution");
            }
        }
    }

    private int dispenseCoins(Scanner scanner) {
        System.out.println(">> Enter the amount for which you need to dispense coins:");
        String amount = scanner.next();
        return Integer.valueOf(amount);
    }

    private void dispenseCoinsToUser(EnumMap<CoinEnum,Integer> outputMap) {
        System.out.println(">> here are the coins with denominaiton:");
        outputMap.entrySet().stream().forEach(coinEntry->System.out.println("Coin Denomination::"+coinEntry.getKey().getCoinValue()+"  number of coins::"+ coinEntry.getValue()));
    }



}
