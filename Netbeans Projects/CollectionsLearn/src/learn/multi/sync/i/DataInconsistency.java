package learn.multi.sync.i;

public class DataInconsistency {

    public static void main(String[] args) {
        System.out.println("Test start.");
        
        Pro p = new Pro(Thread.currentThread());
       
        SweetBox sw = new SweetBox();
        
        p.start();
        
        
        try {
            while (p.getThread().isAlive()) {
                System.out.println(sw.add());
                Thread.sleep(0,1);
            }
        } catch (InterruptedException ex) {
            System.out.println("Main thread has been intterupted");
        }
    }

}

class SweetBox {

    private int sweets = 1000;

    public int add() {
        return ++sweets;
    }
}

class Pro implements Runnable {

    private Thread target;
    private Thread t;

    Pro(Thread t) {
        this.t = t;
        target = new Thread(this);
    }

    @Override
    public void run() {
        try {
            System.out.println("Timmer set.");
            Thread.sleep(7_000);
            t.interrupt();
            System.out.println("Time Up.. Intterrupting main tread.");
            
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }

    public void start() {
        target.start();
    }

    public Thread getThread() {
        return target;
    }
}
