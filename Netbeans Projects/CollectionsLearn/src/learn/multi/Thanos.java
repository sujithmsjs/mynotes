package learn.multi;


public class Thanos implements Runnable {
    private Thread t;
    private int start;
    private int end;
    private long millisecounds;
    
    
    public Thanos(String name) {
        t = new Thread(this,name);
    }

    public void setValues(int start, int end, long millisecounds) {
        this.start = start;
        this.end = end;
        this.millisecounds = millisecounds;
     //   t.start();

    }

    public Thread getThread() {
        return t;
    }

    
    @Override
    public void run() {
        System.out.println(this);
        try {
            System.out.println("This is a run methods.");
            for (int i = start; i < end; i++) {
                 
                Thread.sleep(millisecounds);
                 System.out.println(t.getId()+". "+t.getName() + " : " + i);
            }
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public String toString() {
        return "Thanos{" + "start=" + start + ", end=" + end + ", millisecounds=" + millisecounds + '}';
    }
}
