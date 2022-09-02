package learn.multi;


public class RunnableDemo {

    public static void main(String[] args) {
        Flash f = new Flash();
        Thread t = new Thread(f,"Sujith");
        Thread t2 = new Thread(f,"Vineeth");
        Thread t3 = new Thread(f,"Sumanth");
        Thread t4 = new Thread(f,"Vamshi");
        t.start();
        t2.start();
        t3.start();
        t4.start();
    }

}
