package demo;

import java.awt.Dimension;
import javax.swing.JPanel;


public class Wall extends JPanel {

    Dimension SCREEN_SIZE = new Dimension(600,400);
    
    
    
    
    public Wall() {
        setPreferredSize(SCREEN_SIZE);
    }
    
    
    public static void main(String[] args) {
        Wall w = new Wall();
        MyFrame f = new MyFrame(w, "Name");
        f.setVisible(true);
    }
}
