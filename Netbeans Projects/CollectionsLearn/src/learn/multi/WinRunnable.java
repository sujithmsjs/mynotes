package learn.multi;

import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JProgressBar;


public class WinRunnable implements Runnable {
    private long timeTaken;

    public long getTimeTaken() {
        return timeTaken;
    }
    
    public static void main(String[] args) {
    //<editor-fold defaultstate="collapsed" desc="One way of implementing code.">
        WinRunnable wr = new WinRunnable();
        Thread t = new Thread(wr,"Sujith");
        t.start();
        Thread t2 = new Thread(wr,"Sravani");
        t2.start();
        Thread t3 = new Thread(wr,"Anusha");
        t3.start();
        
//</editor-fold>
        
        
    }

    @Override
    public void run() {
        RunFrame rf  = new RunFrame();
        JProgressBar bar = rf.getBar();
        JLabel label = rf.getLabel();
        Thread t = Thread.currentThread();
        
        rf.setTitle(t.toString());
        rf.setVisible(true);
        Random random = new Random();
        bar.setBorderPainted(true);
        
        try {
            long timeTaken = System.currentTimeMillis();
            for (int i = 1; i <= 100; i++) {
                Thread.sleep(random.nextInt(500));
                label.setText(t.toString()+i);
                bar.setValue(i);
                bar.setString(String.valueOf(i));
                
            }
            timeTaken = timeTaken - System.currentTimeMillis();
            
            bar.setVisible(false);
            label.setText("Completed.");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
