
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class MyFrame extends JFrame  {

    JPanel panel;
    

    public MyFrame(JPanel panel,String title) {
        this.panel = panel;
        add(panel);

        setTitle(title);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
       // addWindowListener(new WA());
        //addComponentListener(new CA());
    }
}
