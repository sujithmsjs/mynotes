package learn.multi.thread.sleep;


public class DataInconsistency {

    public static void main(String[] args) {
        
        MyThread t1 = new MyThread(new Box("Sujith"));
        MyThread t2 = new MyThread(new Box("Wizard"));

        t1.start();
        t2.start();
    }

}

class Box{
    String name;

    public Box(String name) {
        this.name = name;
    }
    
    
    public void wish() throws InterruptedException{
        for (int i = 0; i < 10; i++) {
            System.out.print("Hello ");
            Thread.sleep(1_000);
            System.out.println(name + "!");
        }
    }
}

class MyThread implements Runnable {

    private Thread target;
    private Box box;
    
    MyThread(Box box) {
        this.box = box;
        target = new Thread(this);
    }
    
    @Override
    public void run() {
        try {
            box.wish();
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
