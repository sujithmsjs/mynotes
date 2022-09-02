package com.suji.mt;


public class Box {

    private int total = 0;
    private int n;

    public Box() {
        n = 10;
    }

    
    
    public Box(int n) {
        this.n = n;
    }
    
    
    
    public synchronized void printNums(){
        for (int i = 1; i <= n; i++) {
            try {
                total += i;
                Thread.sleep(1_000);
                System.out.println(total);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
        System.out.println("Sum of first "+n+" numbers: "+total);
    }

    public int getTotal() {
        return total;
    }
}
