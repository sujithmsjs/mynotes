package com.suji.interfaces;

interface StaInf{
    
    //public static final int CONST
    int CONST  = 4;
    
    //public static void m1()
    static void m1(){
        System.out.println("hey, this is interface static method."+CONST);
    }
}

public class IFStaticMethods {

    public static void main(String[] args) {
        StaInf.m1();
        InterfaceDemo.printClassDesc("com.suji.interfaces.StaInf");
    }

}
