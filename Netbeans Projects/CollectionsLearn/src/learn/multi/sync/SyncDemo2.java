package learn.multi.sync;


public class SyncDemo2 {

    public static void main(String[] args) {
        CallMe cm = new CallMe();
        
        Caller c = new Caller("Hellow",cm);
        Caller c2 = new Caller("Hi",cm);
        Caller c3 = new Caller("Watch dng",cm);
        Caller c4 = new Caller("Are you there.",cm);
       
        c.start();
        c2.start();
        c3.start();
        c4.start();
        
        try {
            c.getThread().join();
            c2.getThread().join();
            c3.getThread().join();
            c4.getThread().join();
        
        
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    
    }

}

class CallMe{
    synchronized void call(String message){
        System.out.print("["+message);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        System.out.println("]");
    }
}

class Caller implements Runnable{
    private String message;
    private CallMe receiver;
    private Thread t;
 
    public Caller(String message, CallMe receiver) {
        this.message = message;
        this.receiver = receiver;
        t = new Thread(this);
    }

    @Override
    public void run() {
        receiver.call(message);
    }
    
    public Thread getThread(){
        return t;
    }
    
    public void start(){
        t.start();
    }
    
    


}