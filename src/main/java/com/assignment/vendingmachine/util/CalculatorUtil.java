package com.assignment.vendingmachine.util;

import com.assignment.vendingmachine.coin.CoinEnum;
import com.assignment.vendingmachine.exception.NoSufficientFundsException;

import java.util.*;
import java.util.function.Predicate;

/**
 * utility to calculate anf work out the number os coins with denomination for given amount
 */
public class CalculatorUtil {

    /**
     * calculates the number of coins with each denomination for top to bottom based on coin value
     * @param amount
     * @param sortedMap
     * @return
     * @throws NoSufficientFundsException
     */
    public  EnumMap<CoinEnum, Integer> calculateCoinsForGivenAmount(int amount, TreeMap<CoinEnum, Integer> sortedMap) throws NoSufficientFundsException {
        //enummap to create with each coin enum
        EnumMap<CoinEnum,Integer> outputMap = new EnumMap<CoinEnum,Integer>(CoinEnum.class);
        Iterator<CoinEnum> itr = sortedMap.keySet().iterator();
        while(amount >0 && itr.hasNext()) {
            CoinEnum coinenum = itr.next();
            if(amount >= coinenum.getCoinValue()) {
                int countofCoins = amount / coinenum.getCoinValue();
                if(countofCoins > sortedMap.get(coinenum)) {
                    amount = amount % coinenum.getCoinValue() + coinenum.getCoinValue() *(countofCoins-sortedMap.get(coinenum));
                    countofCoins = sortedMap.get(coinenum);
                }else {
                    amount = amount % coinenum.getCoinValue();
                }
                outputMap.put(coinenum, countofCoins);

            }
        }
        if(amount > 0) {
            throw new NoSufficientFundsException("no sufficient coins in the machine to dispense");
        }
        return  outputMap;
    }

    /**
     * removing the coins from machine based on output map calculation
     * @param outputMap
     * @param originalMap
     */
    public void emptyTheCoinsFromMachine(EnumMap<CoinEnum, Integer> outputMap, Map<CoinEnum, Integer> originalMap) {
        outputMap.entrySet().spliterator().forEachRemaining(coinEnumIntegerEntry -> {
            reduceCoins(coinEnumIntegerEntry.getKey(),coinEnumIntegerEntry.getValue(),originalMap);
        });
    }


    private void reduceCoins(CoinEnum coinEnum,int coinsnumber,Map<CoinEnum, Integer> originalMap) {
        originalMap.put(coinEnum,originalMap.get(coinEnum) -coinsnumber);
    }



}
