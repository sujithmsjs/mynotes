package learn.multi.thread.sleep;


public class TimmerDemo {

    public static void main(String[] args) {
        int n = 0;
        Thread thread = Thread.currentThread();
        Timmer t = new Timmer(thread, 5_000);
        t.start();

        try {
            while (true) {
                Thread.sleep(500);
                System.out.println(n++);
            }
        } catch (InterruptedException ex) {
            System.out.println("You're time up.");
        }

    }

}

class Timmer implements Runnable {

    private Thread target;
    private Thread timmer;
    private long ms;

    public Timmer(Thread target, long ms) {
        this.target = target;
        this.ms = ms;
        timmer = new Thread(this, "Timmer");
    }

    public void start() {
        timmer.start();
    }

    @Override
    public void run() {
        try {
            System.out.println("Time counting started.");
            Thread.sleep(ms);
            target.interrupt();

        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }
}
