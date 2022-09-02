package suji.ani;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class MyFrame extends JFrame {

    private JPanel panel;
    
    
    public MyFrame(JPanel panel, String title) {
        super(title);
        add(panel);
        setResizable(false);
        
    }


}
