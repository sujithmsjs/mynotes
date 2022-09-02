package util.swing;

import java.awt.*;
import javax.swing.*;
import java.util.*;


public class MyCanvas extends JPanel {

    private Dimension SCREEN_SIZE = new Dimension(800, 600);
    private static final Random ran = new Random();

    public MyCanvas() {
        setPreferredSize(SCREEN_SIZE);
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = getWidth();
        int h = getHeight();

        g.setColor(Color.red);
        Graphics2D g2 = (Graphics2D) g;
        
        for (int i = 0; i < 50; i++) {
            // Rectangle r = new Rectangle(ran.nextInt(SCREEN_SIZE.width - 50),ran.nextInt(SCREEN_SIZE.height - 50),50,50);
            Rectangle r = new Rectangle(ran.nextInt(w - 50), ran.nextInt(h - 50), 50, 50);
            g2.fill(r);
        }

        // Rectangle r = new Rectangle(SCREEN_SIZE.width/2 - 25, SCREEN_SIZE.height/2 - 25,50,50);
    }
}
