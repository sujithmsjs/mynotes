package util;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame {

    JPanel canvas;

    public MyFrame(JPanel canvas) {
        this.canvas = canvas;
        add(canvas);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
                
        
    }

    public static void main(String[] args) {

    }

}
