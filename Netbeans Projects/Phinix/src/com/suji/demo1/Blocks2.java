package com.suji.demo1;

class A{
    public static int a, b;
    static {
        a = 50;
        System.out.println("A static Block");
    }
    {
        b = 100;
        System.out.println("A non-static Block");
    }
    A(){
        System.out.println("A Constructor");
    }
}




public class Blocks2 {

    public static void main(String[] args) {
        System.out.println(A.a);
        System.out.println(A.b);
        A a = new A();
        System.out.println(A.b);
    }

}
