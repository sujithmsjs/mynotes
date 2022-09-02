package com.suji.mt;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ITDemo {

    public static void main(String[] args) throws InterruptedException {
        
            Demo d = new Demo();
            d.start();
            System.out.println("Main thread Waiting for Updation");
            synchronized(d){
                System.out.println(d);
                d.wait();
            }
            

            System.out.println("Total of the given numbers" + d.getSum());


    }

}

class Demo extends Thread {

    private int sum;

    public int getSum() {
        return sum;
    }

    @Override
    public void run() {

        for (int i = 1; i < 20; i++) {
            try {
                sum += i;
                Thread.sleep(1_000);
                System.out.println(i);
                synchronized (this) {
                    System.out.println("Child class: "+this.sum);
                    if (i == 10) {
                        notify();
                    }
                }

            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }

    }

}
