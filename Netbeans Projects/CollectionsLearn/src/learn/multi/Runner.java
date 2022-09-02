package learn.multi;

import java.util.Random;

public class Runner extends BarPanel implements Runnable, Comparable {

    private int maxSpeed;
    private long timeTaken;
    private String name;
    private Thread t;

    public Runner(String name, int maxSpeed) {
        this.maxSpeed = maxSpeed;
        this.name = name;
        t = new Thread(this, name);
        System.out.println("Thread is created: " + t + " : " + t.getId());
    }

    public void start() {
        t.start();
    }

    public Thread getThread() {
        return t;
    }

    public String getName() {
        return name;
    }
    
    
    
    

    public long getTimeTaken() {
        return timeTaken;
    }

    @Override
    public void run() {
        
        //Getting Components. 
        Random random = new Random();
        try {
            timeTaken = System.currentTimeMillis();
            for (int i = 1; i <= 100; i++) {
                Thread.sleep(random.nextInt(maxSpeed));
                setBar(i);
                setText(Thread.currentThread() + ": " + i);
            }
            timeTaken =  System.currentTimeMillis() - timeTaken;
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public int compareTo(Object obj) {
        Runner run = (Runner) obj;
        if (timeTaken > run.getTimeTaken()) {
            return 1;
        } else if (timeTaken < run.getTimeTaken()) {
            return -1;
        } else {
            return 0;
        }

    }

    @Override
    public String toString() {
        return "Runner{" + "maxSpeed=" + maxSpeed + ", timeTaken=" + timeTaken + ", name=" + name + "}\n";
    }
}
