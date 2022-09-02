package learn.multi.thread.sleep;

import java.util.Random;

public class TimmerDemo2 {

    public static void main(String[] args) {
        int n = 0;
        Thread thread = Thread.currentThread();
        Timmer2 t = new Timmer2(thread, 5_000);
        t.start();

        try {
            Random random = new Random();
            while (true) {
                Thread.sleep(random.nextInt(500));
                System.out.println(n++);
            }
        } catch (InterruptedException ex) {
            System.out.println("You're time up.");
        }

    }

}

class Timmer2 implements Runnable {

    private Thread target;
    private Thread timmer;
    private long ms;

    public Timmer2(Thread target, long ms) {
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
