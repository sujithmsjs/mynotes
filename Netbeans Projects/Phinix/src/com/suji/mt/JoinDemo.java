package com.suji.mt;

import java.util.logging.Level;
import java.util.logging.Logger;


public class JoinDemo {

    public static void main(String[] args) throws InterruptedException {
        AThread t = new AThread();
        t.start();
        t.join(); // Waits until child thread get died.
        System.out.println("Total: "+t.getTotal());
    }

}

class AThread extends Thread{

    private int total;
    
    public AThread() {
        super("Child");
        total = 0;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                total += i;
                Thread.sleep(1_000);
                System.out.println(total);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
    } 

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
}


