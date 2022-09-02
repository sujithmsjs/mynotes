package learn.multi.sync;

public class GoldBox {

    public static void main(String[] args) {
        Q q = new Q();
        Producer p = new Producer(q);
        Consumer c = new Consumer(q);
        p.t.start();
        c.t.start();
    }
}

class Q {

    int n;
    private boolean valueSet = false;

    synchronized int get() {
        while (valueSet) {
            System.out.println("Got : " + n);
            valueSet = false;
        }
        return n;
    }

    synchronized void put(int n) {
        while (!valueSet) {
            this.n = n;
            valueSet = true;
            System.out.println("Put : " + n);
        }
    }
}

class Producer implements Runnable {

    Q q;

    Thread t;

    public Producer(Q q) {
        this.q = q;
        t = new Thread(this, "Producer");
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            q.put(i++);
        }
    }
}

class Consumer implements Runnable {

    Q q;
    Thread t;

    public Consumer(Q q) {
        this.q = q;
        t = new Thread(this, "Consumer");
    }

    @Override
    public void run() {
        while (true) {
            q.get();
        }
    }
}
