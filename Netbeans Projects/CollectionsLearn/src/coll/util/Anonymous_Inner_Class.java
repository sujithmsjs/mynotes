package coll.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * A class that have no name is known as anonymous inner class in java. It
 * should be used if you have to override method of class or interface. Java
 * Anonymous inner class can be created by two ways: 1. Class (may be abstract
 * or concrete). 2. Interface
 */

/*
Local and Anonymous Classes
There are two additional types of inner classes.
You can declare an inner class within the body of a method. These classes are known as local classes.
You can also declare an inner class within the body of a method without naming the class.
These classes are known as anonymous classes.
*/
public class Anonymous_Inner_Class {

    public static void main(String[] args) {

        // Anonymous inner class
        Bank b = new Bank() {
            @Override
            // public void Outerclass$1.name()
            public void name() {
                System.out.println("SBI Bank");
            }
        };

        // Anonymous inner class
        Bank h = new Bank() {
            @Override
            // public void Outerclass$2.name()
            public void name() {
                System.out.println("HDFC Bank");
            }
        };

        System.out.println(b); // <Outerclass>$1
        b.name(); // calls to <Outerclass>$1.name()

        System.out.println(h); // <Outerclass>$1
        h.name(); // calls to <Outerclass>$2.name()

        // Show fields
        Field[] fields = b.getClass().getFields();
        for (Field field : fields) {
            System.out.println("field = " + field);
        }

        //Show methods.
        Method[] methods = b.getClass().getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("method = " + method);
        }
    }

}

interface Bank {

    /*
    Interface variables are always constant.
    interace variables by defauut "public static final".
    And variables should initialize with default values.
    Best practice to use constants should be in uppercase latters.
     */
    public static final int M = 9;
    int B = 0;
    String NAME = "";

    void name();
}
