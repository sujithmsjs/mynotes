package learn.multi.thread.sleep;


public class CheckFPS extends Thread {

    public static void main(String[] args) throws InterruptedException {
        long start = System.nanoTime();
       // Thread.sleep(1);
        long test = 0;
        for (int i = 0; i < 1000; i++) {
            test += 2;
        }
        
        System.out.println(test);
        
        long stop = System.nanoTime();
        long delta = stop - start;
        System.out.println(delta);
    }

}
