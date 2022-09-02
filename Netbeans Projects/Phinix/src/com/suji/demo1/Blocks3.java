package com.suji.demo1;


class D{
    
    int n, m;
    
    {
        System.out.println("D Non-Static Block");
    }
    
    D(){
        System.out.println("Constructor: D()");
    }
    
    D(int n){
        System.out.println("Constructor: D(int n)");
    }
    
    D(int n, int m){
        System.out.println("Constructor: D(int n, int m)");
    }
}

public class Blocks3 {

    public static void main(String[] args) {
        /*
        Code:
            D d = new D();
        Output:
            D Non-Static Block
            Constructor: D()
        
        Code:
            D d = new D(1,2);
        Output:
            D Non-Static Block
            Constructor: D(int n, int m)
        */    
    }

}
