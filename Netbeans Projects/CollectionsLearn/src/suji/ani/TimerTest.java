package suji.ani.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;


public class TimerTest implements ActionListener {

    // The delay parameter is used to set both the initial delay and the delay between event firing, in milliseconds.
    public static void main(String[] args) {
        TimerTest tt = new TimerTest();
        Timer t = new Timer(1_000,tt);
        t.start();
        while(true){
            
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e);
    }

}
