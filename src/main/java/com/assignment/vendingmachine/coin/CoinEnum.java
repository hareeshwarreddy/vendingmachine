package com.assignment.vendingmachine.coin;

import java.util.Arrays;
import java.util.List;

public enum CoinEnum {
    ONE_PENCE(1),
    TWO_PENCE(2),
    FIVE_PENCE(5),
    TEN_PENCE(10),
    TWENTY_PENCE(20),
    FIFTY_PENCE(50),
    ONE_POUND(100),
    TWO_POUND(200);
    private int coinValue;
    CoinEnum( int coinValue) {
        this.coinValue = coinValue;
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
