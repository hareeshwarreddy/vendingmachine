# Vending Machine
## Build
- JDK 8
-  Gradle
- Build from the project root directory using `gradlew clean build`

## This component offers an  API which:
- Initialises a vending machine from NON_READY to READY state by adding coins for use when the machine is set up. accepts the interger parameter with diffrnt coin denominaitons
- Registers coins that have been deposited by a user
- dispense the coins for given integer amount with coin denominaitons

## Assumptions
-  Coin denominations are integer value
- Coin denominations are only ONE_PENSE till FIFTY_PENSE
- The number of each coin is a positive integer value
- Coins can only be dispensed if they are available
- machine will go into EMPTY state if no coins are available


## pending work
- More unit test are needed
- dispense coins can be optimised with some enhancements to code

## Instructions
- Run from a command line with
    `java -jar vendingmachine-0.0.1-SNAPSHOT.jar`

use case 1: The following test-harness screen shows the machine being initialised and the float being added.  After which the menu options have been updated -

        >> Select an Option from below<<
1:      Load the vending machine with set of coins
4:      To Exit
1
>> Pick the One of the coin type from 1,2,5,10,20,50   (or X to continue):
2
>> enter the  number coins for coin type::2
5
>> Pick the One of the coin type from 1,2,5,10,20,50   (or X to continue):
1
>> enter the  number coins for coin type::1
4
>> Pick the One of the coin type from 1,2,5,10,20,50   (or X to continue):
X
2:      register coins form user
3:      dispense the coins for given number
4:      To Exit
3
>> Enter the amount for which you need to dispense coins:
9
>> here are the coins with denominaiton:
Coin Denomination::1  number of coins::1
Coin Denomination::2  number of coins::4
2:      register coins form user
3:      dispense the coins for given number
4:      To Exit
3
>> Enter the amount for which you need to dispense coins:
5
>> here are the coins with denominaiton:
Coin Denomination::1  number of coins::3
Coin Denomination::2  number of coins::1
2:      register coins form user
4:      To Exit

case 2: machine throws error when no enough coins to dispense in the machine

>> Select an Option from below<<
1:      Load the vending machine with set of coins
4:      To Exit
1
>> Pick the One of the coin type from 1,2,5,10,20,50   (or X to continue):
2
>> enter the  number coins for coin type::2
5
>> Pick the One of the coin type from 1,2,5,10,20,50   (or X to continue):
1
>> enter the  number coins for coin type::1
4
>> Pick the One of the coin type from 1,2,5,10,20,50   (or X to continue):
X
2:      register coins form user
3:      dispense the coins for given number
4:      To Exit
2
>> Pick the One of the coin type from 1,2,5,10,20,50   (or X to continue):
1
>> enter the  number coins for coin type::1
3
>> Pick the One of the coin type from 1,2,5,10,20,50   (or X to continue):
X
2:      register coins form user
3:      dispense the coins for given number
4:      To Exit
3
>> Enter the amount for which you need to dispense coins:
10
>> here are the coins with denominaiton:
Coin Denomination::2  number of coins::5
2:      register coins form user
3:      dispense the coins for given number
4:      To Exit
3
>> Enter the amount for which you need to dispense coins:
10
Sorry!!..there are no sufficient coins in the machine to dispense..try with lesser amount
2:      register coins form user
3:      dispense the coins for given number
4:      To Exit
