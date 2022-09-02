package com.suji.mt;

public class SyncDemo {
    
    public static void main(String[] args) throws InterruptedException {
//        Box b = new Box();
//        T123 t = new T123(b);
//        t.start();
//        System.out.println(b);
//        
//        T123 t2 = new T123(b);
//        t2.start();
        
        Brace b = new Brace('[',']');
        BraceThread t = new BraceThread(b);
        t.start();
        
       // Brace b2 = new Brace('{','}');
        BraceThread t2 = new BraceThread(b);
        t2.start();
        
        while(t2.isAlive()){
            System.out.println(t.getState());
            System.out.println(t2.getState());
            System.out.println(Thread.currentThread().getState());
            Thread.sleep(500);
        }
        
        
        
//        Box b2 = new Box();
//        T123 t2 = new T123(b2);
//        t2.start();


        
        
        
    }
    
}

class T123 extends Thread{
    
    private Box box;

    public T123(Box box) {
        this.box = box;
    }
    @Override
    public void run() {
        box.printNums();
    }
}
