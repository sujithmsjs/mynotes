package learn.multi.thread.sleep;


public class NewMain1 {

    public static void main(String[] args) throws InterruptedException {
        MyClock c = new MyClock();
        Thread child = c.getThread();
        
        child.start();
        
        Thread.sleep(1_000);
        
        while(child.isAlive()){
            System.out.println("Main test: "+child.getState()+" is Alive?"+child.isAlive());
        }
        System.out.println("Main test: "+child.getState()+" is Alive?"+child.isAlive());
        
        
        System.out.println("Main test: "+child.getState()+" is Alive?"+child.isAlive());
    }

}

class MyClock implements Runnable {

    private Thread target;
    
    MyClock() {
        
        target = new Thread(this);
        System.out.println(target.getState());
    }
    
    @Override
    public void run() {
        System.out.println("before sleep: "+target.getState());
        try {
            
            Thread.sleep(1_500);
            
            System.out.println("Print numbers: ");
            for (int i = 0; i < 10_000; i++) {
                System.out.println(target.getState()+": "+i );
            }
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
        System.out.println("After sleep: "+target.getState());
    }
    
    public void start() {
        target.start();
    }
    
    public Thread getThread() {
        System.out.println(target.getState());
        return target;
    }
}
    
