package learn.multi;

import java.util.logging.Level;
import java.util.logging.Logger;


public class NewMain3 {

    public static void main(String[] args) {
        Thread ct = Thread.currentThread();
        System.out.println(ct);
        ct.setName("Thanos");
        System.out.println(ct);
        Thread.State state = ct.getState();
        System.out.println(state);
        
        try {
            
            for (int i = 1; i <= 10; i++) {
                    Thread.sleep(500);
                    System.out.println(i);
            }
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }
}


