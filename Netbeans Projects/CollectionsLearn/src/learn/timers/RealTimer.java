package learn.timers;

public class RealTimer extends Thread {
    
    private long millisecounds;
    private Runnable target;

    public RealTimer(long millisecounds, Runnable target) {
        super(target);
        this.millisecounds = millisecounds;
        this.target = target;
    }
    
    @Override
    public void start(){
        super.start();
    }
    
    @Override
    public void run() {
        while (true) {
            try {

                Thread.sleep(millisecounds);
                target.run();

            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
    }
}
