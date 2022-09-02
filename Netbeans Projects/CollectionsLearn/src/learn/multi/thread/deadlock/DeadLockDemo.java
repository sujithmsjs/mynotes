package learn.multi.thread.deadlock;

import java.util.logging.Level;
import java.util.logging.Logger;


public class DeadLockDemo {

    public static void main(String[] args) {
        try {
            Thread thread = Thread.currentThread(); 
            thread.join(); // Dead LOCK.
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }

}
