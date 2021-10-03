package com.assignment.vendingmachine.service;

import com.assignment.vendingmachine.state.State;
import com.assignment.vendingmachine.coin.CoinEnum;
import com.assignment.vendingmachine.exception.NoSufficientFundsException;
import com.assignment.vendingmachine.util.CalculatorUtil;

import java.util.EnumMap;
import java.util.Map;
import java.util.TreeMap;

public class VendingMachineServiceImpl implements VendingMachineService {

    private State state;
    private TreeMap<CoinEnum, Integer> coins;
    public  VendingMachineServiceImpl(State state) {
        this.state = state;
    }
    @Override
    public void initialiseMachine(TreeMap<CoinEnum, Integer> coins) {
        this.coins = coins;
        this.state = State.READY;
    }

    @Override
    public void addMoreCoinsToMachine(TreeMap<CoinEnum, Integer> coins) {
        this.coins = coins;
        this.state = State.READY;
    }

    @Override
    public EnumMap<CoinEnum,Integer> dispenseCoins(Integer amount) throws NoSufficientFundsException {
        CalculatorUtil calculatorUtil = new CalculatorUtil();
        EnumMap<CoinEnum,Integer> outputMap =  calculatorUtil.calculateCoinsForGivenAmount(amount,this.coins);
        calculatorUtil.emptyTheCoinsFromMachine(outputMap,this.coins);
        if(this.coins.values().stream().anyMatch(coin -> coin >0))
                this.state = State.READY;
        else
        this.state = State.EMPTY;
        return outputMap;
    }

    @Override
    public State getCurrentState() {
        return this.state;
    }


}