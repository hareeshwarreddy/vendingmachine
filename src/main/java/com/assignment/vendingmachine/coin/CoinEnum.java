package com.assignment.vendingmachine.coin;

import java.util.Arrays;
import java.util.List;

public enum CoinEnum {
    ONE_PENSE("ONE_PENSE",1),TWO_PENSE("TWO_PENSE",2),FIVE_PENSE("FIVE_PENSE",5),TEN_PENSE("TEN_PENSE",10),TWENTY_PENSE("TWENTY_PENSE",20),FIFTY_PENSE("FIFTY_PENSE",50);
    private int coinValue;
    private String coinType;
    CoinEnum(String coinType, int coinValue) {
        this.coinValue = coinValue;
        this.coinType = coinType;
    }

    public int getCoinValue() {
        return coinValue;
    }

    public static CoinEnum getCoinEnum(int coinValue) {
        List<CoinEnum> coinTypes = Arrays.asList(CoinEnum.values());
        return coinTypes.stream().filter(coinEnum -> coinEnum.coinValue == coinValue).findFirst().orElse(null);
    }

    public static boolean validate(int coinType) {
        List<CoinEnum> coinTypes = Arrays.asList(CoinEnum.values());
        return coinTypes.stream().anyMatch(coinEnum -> coinEnum.coinValue == coinType);
    }


}
