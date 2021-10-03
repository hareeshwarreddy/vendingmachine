package com.assignment.vendingmachine.util;

import com.assignment.vendingmachine.coin.CoinEnum;
import com.assignment.vendingmachine.exception.NoSufficientFundsException;

import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;

/**
 * utility to calculate and work out the number os coins with denomination for given amount
 */
public class CalculatorUtil {

    /**
     * calculates the number of coins with each denomination for top to bottom based on coin value
     * @param amount
     * @param sortedMap
     * @return enumMap
     * @throws NoSufficientFundsException
     */
    public  Map<CoinEnum, Integer> calculateCoinsForGivenAmount(int amount, Map<CoinEnum, Integer> sortedMap) throws NoSufficientFundsException {
        //enummap to create with each coin enum
        EnumMap<CoinEnum,Integer> outputMap = new EnumMap<>(CoinEnum.class);
        Iterator<CoinEnum> itr = sortedMap.keySet().iterator();
        while(amount >0 && itr.hasNext()) {
            CoinEnum coinenum = itr.next();
            int coinValue = coinenum.getCoinValue();
            if(amount >= coinValue) {
                int countofCoins = amount / coinValue;
                //check if the numbers coins exists in original map, else only take the available coins and set the amount with delta
                if(countofCoins > sortedMap.get(coinenum)) {
                    amount = amount % coinValue + coinValue *(countofCoins-sortedMap.get(coinenum));
                    countofCoins = sortedMap.get(coinenum);
                }else {
                    amount = amount % coinValue;
                }
                outputMap.put(coinenum, countofCoins);
            }
        }
        //finally if amount is still greater than 0, means there are no enough coins with set of coin denominations
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
    public void emptyTheCoinsFromMachine(Map<CoinEnum, Integer> outputMap, Map<CoinEnum, Integer> originalMap) {
        outputMap.entrySet().spliterator().forEachRemaining(coinEnumIntegerEntry -> reduceCoins(coinEnumIntegerEntry.getKey(),coinEnumIntegerEntry.getValue(),originalMap));
    }


    private void reduceCoins(CoinEnum coinEnum,int coinsnumber,Map<CoinEnum, Integer> originalMap) {
        originalMap.put(coinEnum,originalMap.get(coinEnum) -coinsnumber);
    }



}
