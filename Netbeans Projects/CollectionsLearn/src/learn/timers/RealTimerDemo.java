package learn.timers;


public class RealTimerDemo implements Runnable{

    public static void main(String[] args) {
        RealTimerDemo rtd = new RealTimerDemo();
        RealTimer rt = new RealTimer(1_000,rtd);
        rt.start();
    }

    @Override
    public void run() {
        System.out.println("Hellow");
    }

}