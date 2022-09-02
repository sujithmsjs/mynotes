package com.suji.shapes.work;

import just.*;
import com.suji.shapes.LoveShape;
import com.suji.paint.Util;
import com.suji.shapes.B;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import com.suji.shapes.Fire;


class Board123 extends JPanel {

    public static final Dimension SCREEN_SIZE = new Dimension(800, 600);
    private javax.swing.Timer timer;
    private static final int DELAY = 10;
    private Fire fire;
    
    public Board123() {
        setBackground(Color.BLACK);
        setPreferredSize(SCREEN_SIZE);
        setFocusable(true);
        addMouseListener(new MA());
        fire = new Fire();
       
    }
    

     LoveShape s = new LoveShape();
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Util.drawGridLines(g2d, SCREEN_SIZE, 100);
        fire.setFrame(0, 0, 20, 20);
        fire.setVisible(true);
        fire.draw(g2d);
        
  
        
        
    }

    private static class Bullet2 {

        public Bullet2() {
        }
    }

    class MA extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            
        }
    }
}

public class TestCode extends JFrame {

    Board123 b;

    TestCode() {
        b = new Board123();
        add(b);
        setTitle("Just demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new TestCode().setVisible(true);
    }

}
