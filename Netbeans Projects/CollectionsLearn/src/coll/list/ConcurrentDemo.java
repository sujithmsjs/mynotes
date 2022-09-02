package coll.list;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class A{
    int life = 500;
    public static int id=0;

    public A() {
        id++;
    }
    
    public boolean isDead() {
        return life<=0;
    }

    @Override
    public String toString() {
        return "id:"+id;
    }

    
    
    
    
}

public class ConcurrentDemo {

    public static void main(String[] args) {
        ArrayList<A> list = new ArrayList<>();
        Adder d = new Adder(list);
        Remover d2 = new Remover(list);
    }
        
}


class Adder implements Runnable{

    private Thread thread;
    private ArrayList<A> list;
    private int n;
    
    public Adder(ArrayList<A> list) {
        this.list = list;
        thread = new Thread(this);
        thread.start();
    }
    
    @Override
    public void run() {
        try {
            while(true){
            //    Thread.sleep(1_000);
                list.add(new A());
                System.out.println(list);
            }
        } catch (Exception ex) {
            
        }
    }
}

class Remover implements Runnable{

    private Thread thread;
    private ArrayList<A> list;

    public Remover(ArrayList<A> list) {
        this.list = list;
        thread = new Thread(this);
        thread.start();
    }
    
    @Override
    public void run() {
        for (A a : list) {
            if(a.isDead()){
                list.remove(a);
            }
        }
    }
}