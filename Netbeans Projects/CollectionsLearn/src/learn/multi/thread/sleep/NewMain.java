package learn.multi.thread.sleep;

public class NewMain {

    public static void main(String[] args) {
        try {
            
            System.out.println("I am sleeping.");
            Thread.sleep(10_000);
            System.out.println("I wake up.");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void main00(String[] args) {
        System.out.println("Test Start.");
        Alaram a = new Alaram();
        a.start();

        while (a.isTimeUp()) {
            try {
                System.out.println("Reading Questing...");
                Thread.sleep(3_000);
                System.out.println("Answering...");
                Thread.sleep(3_000);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
    }
    
} 



class Alaram implements Runnable {

    private Thread target;

    public Alaram() {
        target = new Thread(this);
    }

    public void start() {
        target.start();
    }

    public boolean isTimeUp() {
        return target.isAlive();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(15_000);
            System.out.println("Alaram ringing... You're time up.");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}
