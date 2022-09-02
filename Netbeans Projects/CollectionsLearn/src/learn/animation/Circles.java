/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learn.animation;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import javax.swing.JPanel;
import learn.timers.RealTimer;

/**
 *
 * @author sujit
 */
public class Circles extends JPanel implements Runnable {
    public static final int SCREEN_WIDTH = 500;
    public static final int SCREEN_HEIGHT = 500;
    public static final int UNIT_SIZE = 10;
    public static final int DELAY = 5;
    
    private RealTimer rt;
    private int outArcAngel = 0;
    private int inArcAngel = 0;
    
    private int outVelociry = -1;
    private int intVelociry = 1;
    
    
    
    public Circles() {
        setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        rt = new RealTimer(DELAY,this);
        rt.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); 
        setBackground(Color.BLACK);
        Graphics2D g2d = (Graphics2D) g;
        BasicStroke bs = new BasicStroke(UNIT_SIZE);
        float lineWidth = bs.getLineWidth();
        System.out.println("lineWidth = " + lineWidth);
        
        g2d.setStroke(bs);
        g2d.setColor(Color.YELLOW);
        
    //    g2d.drawLine(UNIT_SIZE, UNIT_SIZE, SCREEN_WIDTH-UNIT_SIZE, SCREEN_HEIGHT-UNIT_SIZE);
   // g2d.drawOval(0+UNIT_SIZE/2, 0+UNIT_SIZE/2, SCREEN_WIDTH-UNIT_SIZE, SCREEN_HEIGHT-UNIT_SIZE);
        g2d.drawArc(0+UNIT_SIZE/2, 0+UNIT_SIZE/2, SCREEN_WIDTH-UNIT_SIZE, SCREEN_HEIGHT-UNIT_SIZE, outArcAngel, 270);
        
        g2d.setColor(Color.CYAN);
        g2d.drawArc(0+UNIT_SIZE*2, 0+UNIT_SIZE*2, SCREEN_WIDTH-UNIT_SIZE*4, SCREEN_HEIGHT-UNIT_SIZE*4, inArcAngel, 270);
    }
    
    public static void main(String[] args) {
        new MyFrame("My paint");
    }

    @Override
    public void run() {
        outArcAngel += outVelociry;
        inArcAngel += intVelociry;
                
                
        
        
       repaint();
    }
    
    
    
}
