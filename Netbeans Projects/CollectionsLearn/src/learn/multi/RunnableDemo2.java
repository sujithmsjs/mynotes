package learn.multi;

import java.util.logging.Level;
import java.util.logging.Logger;


public class RunnableDemo2 {

    public static void main(String[] args) {
        Thanos t = new Thanos("Sujith");
        t.setValues(1, 10, 500);
        t.getThread().start();
        t.getThread().start();
        
        
        
        Thanos t2 = new Thanos("SuperMan");
        t2.setValues(30, 40, 1000);
        t2.getThread().start();
        
    }

}
