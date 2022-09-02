package learn.animation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import learn.timers.RealTimer;

public class TimerTest implements ActionListener, Runnable {

    public TimerTest() {
//        RealTimer r = new RealTimer(1_000, this);
        Timer t = new Timer(10_000,this);
        t.start();
//        r.start();
    }

    public static void main(String[] args) {
        new TimerTest();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Hellow");
    }

    @Override
    public void run() {
        System.out.println("Hellow");
    }

}
