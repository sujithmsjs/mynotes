package learn.multi.sync.inter;

public class NewMain1 {

    public static void main(String[] args) {

        //Case study
        Q q = new Q();
        Thread t = new Thread(q);
        Thread t2 = new Thread();
        R r = new R();
        R r2 = new R();
        
        r.start();
        
        

        System.out.println("Main thread dead.");
    }

}

class Q implements Runnable {

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name+" Q class run method.");
    }

}

class R extends Thread{
    @Override
    public void run() {
        String name = Thread.currentThread().getName();

        System.out.println(name + " R class run method.");
    }
}
