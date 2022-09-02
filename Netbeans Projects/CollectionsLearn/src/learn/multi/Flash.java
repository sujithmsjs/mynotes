package learn.multi;

import java.util.Random;


public class Flash implements Runnable {

    @Override
    public void run() {
        try {
            
            
            Random random = new Random();
            Thread t = Thread.currentThread();
            for (int i = 1; i < 11; i++) {
                Thread.sleep(random.nextInt(2000));
                //System.out.println(t.getId()+". "+t.getName() + " : " + i);
                System.out.println(t + " : " + i);
            }

        } catch (InterruptedException ex) {
            System.out.println("ex = " + ex);
        }
    }
}
