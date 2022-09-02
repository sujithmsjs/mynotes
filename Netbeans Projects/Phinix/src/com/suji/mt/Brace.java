package com.suji.mt;


public class Brace{

    private char ch1,ch2;
    
    Brace(char c1, char c2) {
        ch1 = c1;
        ch2 = c2;
    }
    
    
    

    public synchronized void printBrace() {
        for (int i = 1; i <= 10; i++) {
            try {
                for (int j = 0; j < 10_000; j++) {
                    
                }
                System.out.print(ch1);
                Thread.sleep(200);
                 System.out.println(ch2);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
    }

    
}

class BraceThread extends Thread{
    private Brace b;

    public BraceThread(Brace b) {
        this.b = b;
    }
    
    @Override
    public void run() {
        b.printBrace();
    }
}
