package com.suji.interfaces;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

interface I {
    // public static final int
    int n = 5;

    //public abstract void m1()
    void m1();
}

class A implements I {

    @Override
    public void m1() {
        System.out.println("Hey I am A");
    }

}

public class InterfaceDemo {

    public static void main(String[] args) {
        printClassDesc("com.suji.interfaces.I");
    }
    
    public static void printClassDesc(String className){
        try {

            Method m[] = Class.forName(className).getDeclaredMethods();
            Field[] f = Class.forName(className).getDeclaredFields();

            for (Method method : m) {
                System.out.println(method);
            }

            for (Field field : f) {
                System.out.println(field);
            }

        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }

}
