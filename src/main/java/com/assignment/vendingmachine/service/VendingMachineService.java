package com.assignment.vendingmachine.service;

import com.assignment.vendingmachine.state.State;
import com.assignment.vendingmachine.coin.CoinEnum;
import com.assignment.vendingmachine.exception.NoSufficientFundsException;

import java.util.EnumMap;
import java.util.Map;
import java.util.TreeMap;

public interface VendingMachineService {
    /**
     * intialise the machine set of coins
     * @param coins
     */
    public void initialiseMachine(TreeMap<CoinEnum,Integer> coins);

    /**
     * add more coins to the machine based on user registered coins
     * @param coins
     */
    public void addMoreCoinsToMachine(TreeMap<CoinEnum,Integer> coins);

    /**
     * dispense the coins for given amount
     * @param amount
     * @return EnumMap
     * @throws NoSufficientFundsException
     */
    public EnumMap<CoinEnum,Integer> dispenseCoins(Integer amount) throws NoSufficientFundsException;

    /**
     * get current state of the machine
     * @return state
     */
    public State getCurrentState();
}
