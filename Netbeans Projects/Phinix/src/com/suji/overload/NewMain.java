package com.suji.overload;

class A{
    public static void m1(){
        System.out.println("A m1()");
    }

    public void m2(){
        System.out.println("A m2()");
    }
    
    public final void m3(){
        System.out.println("A m2()");
    }
    
    // Final Methods can be overloaded. but can't be overridden.
    public final void m3(int n){
        System.out.println("A m2()");
    }
    
}

class B extends A{
    public static void m1(){
        System.out.println("B m1()");
    }
    
    public void m2(){
        System.out.println("B m2()");
    }
    /*
    VerifyError: class com.suji.overload.B overrides final method m3.()
    public final void m3(){ 
        System.out.println("A m2()");
    }
    */
    
}

public class NewMain {

    public static void main(String[] args) {
        B b = new B();
        b.m1(); // B m1()
        b.m2();
        
        A a = new B();
        a.m1(); // A m1() Static methods can't be orverridden.
        a.m2(); // B m2() Non-Static methods can be orverriden.
    }

}
