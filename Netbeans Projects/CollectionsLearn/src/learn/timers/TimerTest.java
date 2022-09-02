package learn.timers;

public class TimerTest implements Alaram{

    public static void main(String[] args) {
        TimerTest tTest = new TimerTest();
       Timer t = new Timer(1_000, tTest);
       t.start();
    }

    @Override
    public void wakeUp() {
        System.out.println("WakeUp");
    }
}

interface Alaram{
    public void wakeUp();
}

class Timer implements Runnable {
    Thread t;
    long time;
    Alaram child;

    public void start() {
        t.start();
    }
    

    public void run() {
        while (true) {
            try {
                
                Thread.sleep(time);
                 child.wakeUp();
                
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
    }

    public Timer(long time, Alaram child) {
        this.time = time;
        this.child = child;
        t = new Thread(this);
    }

}
