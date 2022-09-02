package com.suji.exp;


public class FinallyDemo2 {

    public static void main(String[] args) {
        try{
            System.out.println(5/0);
        }catch(ArithmeticException e){
            System.out.println("Hehe error!");
        }finally{
            System.out.println("Final block");
        }
        System.out.println("Coded ended.");
    }

}
