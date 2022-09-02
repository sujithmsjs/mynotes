package com.suji.mt;

import java.util.logging.Level;
import java.util.logging.Logger;

class Plate{
    private int n;
    
    public synchronized void consume(){
        n--;
        System.out.println("Consumed 1");
    }
    
    public synchronized  void produce(){
        n++;
        System.out.println("Produced 1");
    }
    public void show(){
        System.out.println("Items: "+n);
    }
}

class Consumer extends Thread{

    private Plate plate;

    public Consumer(Plate plate) {
        this.plate = plate;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            plate.produce();
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
    } 
}

class Producer extends Thread {

    private Plate plate;

    public Producer(Plate plate) {
        this.plate = plate;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            plate.consume();
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
        
    }
}

public class ChopSticks {

    public static void main(String[] args) {
        
        Plate plt = new Plate();
        Consumer com = new Consumer(plt);
        Producer pro = new Producer(plt);
        com.start();
        pro.start();
       
        
    }

}
