package suji.ani;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLooper implements Runnable {

    private ActionListener listener;

    private Thread thread;
    int n = 0;

    GameLooper(ActionListener listener) {
        this.listener = listener;
        thread = new Thread(this);
    }

    public void start() {
        thread.start();
    }

    public static void main(String[] args) {

        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Action performed");
            }
        };

        GameLooper gl = new GameLooper(al);
        gl.start();

    }

    @Override

    public void run() {
        //Game loop
        long lastTime = System.nanoTime();

        double amountOfTicks = 60.0;
        double ns = 1_000_000_000 / amountOfTicks;
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                delta--;
                ActionEvent ae = new ActionEvent(this, 1, "Hai");
                listener.actionPerformed(ae);
            }
        }
    }

}
