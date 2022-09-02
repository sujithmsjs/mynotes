package learn.multi.thread;

import java.util.logging.Level;
import java.util.logging.Logger;


public class ThreadDemo {

    public static void main(String[] args) {
        //Starting child thread.
        ChildThread ct = new ChildThread("Child 1 ");
        ct.start();
        
        ChildThread ct2 = new ChildThread("Child 2 ");
        ct2.start();
        
        ChildThread ct3 = new ChildThread("Child 3 ");
        ct3.start();
        
        ChildThread ct4 = new ChildThread("Child 4 ");
        ct4.start();
        
        Thread t = Thread.currentThread();
        t.setName("Main");
        try {   
            for (int i = 0; i < 10; i++) {
                Thread.sleep(500);
                System.out.println(t.getName()+":"+t.getId()+" : "+i);
            }
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
        System.out.println("Main joining Threads.");
        try {
            ct.join();
            ct2.join();
            ct3.join();
            ct4.join();
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        System.out.println(t.getName()+" Terminated.");
    }

}

class ChildThread extends Thread{

    public ChildThread(String name) {
        super(name);
    }

    @Override
    public void run(){
        try {
            
            for (int i = 0; i < 16; i++) {
                Thread.sleep(500);
                System.out.println(getName()+":"+getId()+" : "+i);
            }
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
        System.out.println(Thread.currentThread().getName()+" terminated.");
    }
    
}
