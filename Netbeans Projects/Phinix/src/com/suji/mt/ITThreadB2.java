package com.suji.mt;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ITThreadB2 {

    public static void main(String[] args) throws InterruptedException {

        ThreadB d = new ThreadB();
        d.start();
        
        
        System.out.println(d.getPriority());
        System.out.println(Thread.currentThread().getPriority());
        d.setPriority(6);
        
        synchronized (d) {
            System.out.println("Main thread Waiting for Updation");
            d.wait();
            System.out.println("Main thread received Notification.");
            System.out.println(d);
            
        }

        System.out.println("Total of the given numbers" + d.getSum());

    }

}

class ThreadB extends Thread {

    private int sum;

    public int getSum() {
        return sum;
    }

    @Override
    public void run() {
        System.out.println("Child Thread Started.");
        for (int i = 1; i < 20; i++) {
            try {
                sum += i;
                Thread.sleep(1_000);
                System.out.println(i);
                synchronized (this) {
                    System.out.println();
                    if (i == 10) {
                        System.out.println("Child Thread sent Notification.");
                        notify();
                    }
                }

            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }

    }

}
