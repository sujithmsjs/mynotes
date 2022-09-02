package com.suji.interfaces;

interface In{
    //public default void m1()
    default void m1(){
        System.out.println("Interface In Default Method.");
    }
}

interface In2 extends In{
    @Override
    default void m1(){
        System.out.println("Interface In2 Default Method.");
    }
}

interface In3 extends In2{
    @Override
    default void m1(){
        System.out.println("Interface In3 Default Method.");
    }
}

class B implements In3{
    @Override
    public void m1(){
        System.out.println("Class Concrete Default Method.");
    }
}

public class DefaultMethods {

    public static void main(String[] args) {
       // In in = new In(); Uncompilable source code; cannot be instantiated.
       In in = new In() {};
       in.m1();
       in = new B();
       in.m1();
       InterfaceDemo.printClassDesc("com.suji.interfaces.In");
    }
}
