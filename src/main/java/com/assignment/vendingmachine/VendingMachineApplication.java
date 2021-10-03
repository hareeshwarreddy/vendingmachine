package com.assignment.vendingmachine;


import com.assignment.vendingmachine.coin.CoinEnum;
import com.assignment.vendingmachine.exception.NoSufficientFundsException;
import com.assignment.vendingmachine.service.VendingMachineService;
import com.assignment.vendingmachine.service.VendingMachineServiceImpl;
import com.assignment.vendingmachine.state.State;

import java.util.*;


/**
 * Main class to demonstrate vending API
 */
public class VendingMachineApplication {

    public static void main(String[] args) throws Exception {
        VendingMachineApplication app = new VendingMachineApplication();
        VendingMachineService vendingMachineService = new VendingMachineServiceImpl(State.NOT_READY);
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n");
        System.out.println(">> Select an Option from below<<");
        boolean execute  = true;
        //initialising map and keeping the coins denomination in reverse order
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
            //switch case for each of the option
            switch (option) {
                //initialising the machine
                case "1":
                    app.addCoins(map, scanner);
                    vendingMachineService.initialiseMachine(map);
                    break;
                    //adding more coins to machine
                case "2":
                    app.addCoins(map, scanner);
                    vendingMachineService.addMoreCoinsToMachine(map);
                    break;
                    //dispense the coins
                case "3":
                    try {
                        Map<CoinEnum, Integer> outputMap = vendingMachineService.dispenseCoins(app.dispenseCoins(scanner));
                        app.dispenseCoinsToUser(outputMap);
                    } catch (NoSufficientFundsException ex) {
                        System.out.println("Sorry!!..there are no supported coins in the machine to dispense for given amount.. please try with different amount");
                    }
                    break;
                case "4":
                    execute = false;
                    break;
                default:
                    execute = false;

            }
        }

    }

    private void addCoins(Map<CoinEnum,Integer> map, Scanner scanner) {
        String coinType = "";
        while (!coinType.trim().equalsIgnoreCase("X")) {
            try {
                System.out.println(">> Pick the One of the coin type from 1,2,5,10,20,50,100,200   (or X to continue):");
                coinType = scanner.next().trim();
                if("X".equalsIgnoreCase(coinType)){
                    continue;
                }
                if(!CoinEnum.validate(Integer.parseInt(coinType))) {
                    System.out.println("only pick the right coin type");
                    continue;
                }

                System.out.println(">> enter the  number coins for coin type::"+coinType);
                String coins = scanner.next().trim();
                map.put(CoinEnum.getCoinEnum(Integer.parseInt(coinType)),Integer.valueOf(coins));

            } catch (NumberFormatException nfe) {
                System.out.println("error occurred during execution");
            }
        }
    }

    private int dispenseCoins(Scanner scanner) {
        System.out.println(">> Enter the amount for which you need to dispense coins:");
        String amount = scanner.next();
        return Integer.parseInt(amount);
    }

    private void dispenseCoinsToUser(Map<CoinEnum,Integer> outputMap) {
        System.out.println(">> here are the coins with denominaiton:");
        outputMap.forEach((key, value) -> System.out.println("Coin Denomination::" + key.getCoinValue() + "  number of coins::" + value));
    }



}
