package learn.multi.thread.deadlock;

public class ChildWaitingForMainDemo {

    public static void main(String[] args) {
        try {
            Child.thread = Thread.currentThread();
            Child c = new Child();
            Thread t = new Thread(c);
            t.start();
           // t.join(); DEAD Load arise.
            String name = Thread.currentThread().getName();
            for (int i = 1; i <= 10; i++) {
                Thread.sleep(500);
                 System.out.println(name+": "+i);
            }
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }

}

class Child implements Runnable{
    static Thread thread;
    
    
    
    @Override
    public void run() {
        try {
            thread.join();
            
            String name = Thread.currentThread().getName();
            for (int i = 1; i <= 10; i++) {
               Thread.sleep(500);
                System.out.println(name+": "+i);
            }
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }
    
}