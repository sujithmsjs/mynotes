package learn.multi.thread;


public class JoinDemo {

    public static void main(String[] args) {
        Q q = new Q("Child");
        q.getThread().start();
        int sum = q.getSum();
        System.out.println("sum = " + sum);
    }

}

class Q implements Runnable{
    private Thread thread;
    private int sum;
    
    public Q(String name) {
        thread = new Thread(this,name);
    }

    public int getSum() {
        return sum;
    }

    public Thread getThread() {
        return thread;
    }
       
    @Override
    public void run() {
        try {
            sum = 0;
            for (int i = 1; i <= 10; i++) {
                Thread.sleep(500);
                sum+=i;
                System.out.println(sum);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
}
