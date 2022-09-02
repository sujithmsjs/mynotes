package learn.multi.sync.inter;

import java.util.logging.Level;
import java.util.logging.Logger;


public class ProConsDemo {

    public static void main(String[] args) {
        Box b = new Box();
        Pro p = new Pro(b);
        Cons c = new Cons(b);
        
        p.start();
        c.start();
        
    }

}

class Box{
    int n;
    boolean isSet=false;
    
    
    synchronized void put(int n){
        if(!isSet){
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
            
            System.out.println("Put: " + n);
            this.n = n;
            isSet = true;
            notify();
        }
    }
    
    synchronized int get(){
        
        if(isSet){
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
            
            System.out.println("Get: " + n);
            isSet = false;
            notify();
        }
        return n;
    }
}

class Pro extends Thread{
    Box b;

    public Pro(Box b) {
        this.b = b;
    }
    
    @Override
    public void run(){
        int n = 1;
        while(true){
           b.put(n++);
        }
    }
}

class Cons extends Thread{
    Box b;

    public Cons(Box b) {
        this.b = b;
    }
    
    @Override
    public void run(){
        while(true){
            b.get();
        }
    }
}