package com.suji.interfaces.bank;


public class ATMTest {

    public static void main(String[] args) {
        BankATM atm = new SbiATM();
        
        System.out.println(atm.checkBlnc());
        System.out.println(atm.deposit(5000));
        System.out.println(atm.withdraw(200));
    
    
    }
    

}
