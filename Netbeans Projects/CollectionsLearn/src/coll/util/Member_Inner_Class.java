package coll.util;

public class Member_Inner_Class {

//    Terminology
//    : Nested classes are divided into two categories
//    : static and non
//    -static
//    . Nested classes that are declared static are called static nested classes
//    . Non
//    -static nested classes are called inner classes

    
    
    public static void main(String[] args) {
        A a = new A();
        
        // For non static classes we need an enclosing instance.
        A.B b = a.getB(); 
        
        // For non static classes we need an enclosing instance.
        A.B b1 = a.new B();
        
        // For static nested class.
        A.C c = new A.C();
        /*
            We can create instance for static classes.
            Static inner classes are indipendent form outer classes.
         */
        c.show();
        //  A.B b2 = new A.B(); an enclosing instance that contains A.B is required.
        b.show();
        a.show();
        //  B b2 = a.getB(); incompatible types: A.B cannot be converted to B
    }
}

class A {

    int n = 10;
    int m = 100;
    static int s = 50;

    class B {

        public void show() {
            int n = 20;
            System.out.println(n + " " + m);
        }
    }

    static class C {

        public void show() {
            int n = 20;
            //System.out.println(n+" "+m);  non-static variable m cannot be referenced from a static context
            System.out.println(n + " " + s);
        }
    }

    public void show() {
        System.out.println(n);
    }

    public B getB() {
        B b = new B();
        return b;
    }
}

class B {

    String name;
}
