package learn.multi.sync.inter;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Test {

    public synchronized static void main(String[] args) {
        Demo d = new Demo();
        d.t.start();
        
        int n = 0;
        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println("main: "+(++n));
                if(n%10==0){
                    d.notify();
                }
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
        
    }

}

class Demo implements Runnable{
    Thread t;

    public Demo() {
        t = new Thread(this);
    }
    
    @Override
    public synchronized void run() {
        int n = 0;
        while(true){
            try {
                Thread.sleep(1000);
                System.out.println("Child: "+(++n));
                if(n%5==0){
                    wait();
                }
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
    }
}