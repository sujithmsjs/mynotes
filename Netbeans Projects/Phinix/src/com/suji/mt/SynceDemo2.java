package com.suji.mt;


public class SynceDemo2 {

    public static void main(String[] args) {
        Box b = new Box(10);
        T123 t = new T123(b);
        t.start();
        
        //Box b2 = new Box(10);
        T123 t2 = new T123(b);
        t2.start();
    }

}
