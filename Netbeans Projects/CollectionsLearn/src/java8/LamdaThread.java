package java8;

import java.util.logging.Level;
import java.util.logging.Logger;


public class LamdaThread  {

    public static void main(String[] args) {
        Runnable r = ()->{
            try {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(1_000);
                    System.out.println(i);
                }
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        };
        
        Thread t = new Thread(r);
        t.start();
    }

}
