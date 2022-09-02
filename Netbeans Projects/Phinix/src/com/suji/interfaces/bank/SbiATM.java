package com.suji.interfaces.bank;


public class SbiATM implements BankATM {

    private int amount;
    
    @Override
    public int checkBlnc() {
        return amount;
    }

    @Override
    public int withdraw(int amount) {
       if(this.amount >= amount){
           this.amount -= amount;
       }else{
           System.out.println("Insufficient balance!!!");
       }
       return this.amount;
    }

    @Override
    public int deposit(int amount) {
       this.amount += amount;
       return this.amount;
    }

}
