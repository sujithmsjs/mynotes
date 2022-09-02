package com.suji.demo1;

class B extends A {

    public static int a, b;

    static {
        a = 150;
        System.out.println("B static Block");
    }

    {
        b = 1100;
        System.out.println("B non-static Block");
    }

    B() {
        System.out.println("B Constructor");
        b = 1200;
    }
}

public class Blocks1 {

    public static void main(String[] args) {
        /*
        Code:
            System.out.println(B.a);
        Output:
            A static Block
            B static Block
            150
         
        Code:   
            System.out.println(B.b);
        
        Output:
            A static Block
            B static Block
            0
        
        
        Code:
            B b = new B();
            System.out.println(B.b);
        
        Output:
            A non-static Block
            A Constructor
            B non-static Block
            B Constructor
            1200
         */

    }

}
