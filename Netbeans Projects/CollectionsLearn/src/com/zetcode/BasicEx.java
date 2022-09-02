package com.zetcode;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

class BasicDemo extends JPanel {

    
    private void doDrawing(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawString("Java 2D", 50, 50);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
    
    
    
    public static void main(String[] args) {
        MyFrame frame = new MyFrame(new BasicDemo(),"Basic demo");
        frame.setVisible(true);
    }
}

