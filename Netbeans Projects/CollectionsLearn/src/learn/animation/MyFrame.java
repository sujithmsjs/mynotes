package learn.animation;

import java.awt.Color;
import java.awt.Container;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MyFrame extends JFrame {
    
    private JPanel panel;

    public MyFrame(JPanel panel) {
        this.panel = panel;
        initUI();
    }
    
    public MyFrame(String title) throws HeadlessException {
        super(title);
        initUI();
    }
    public static void main(String[] args) {
        new MyFrame("My Game");
    }

    private void initUI() {
        
        if(panel!=null){
            add(panel);
        }else{
            SamplePanel sp = new SamplePanel();
            
            Container c = getContentPane();
            c.add(sp);
            c.setBackground(Color.RED);
            sp.setLocation(300, 400);
            System.out.println("BG color tying to set.");
        }
        
        setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //  setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
