package learn.animation.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;


class Canvas extends JPanel{

    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    
    
    public Canvas() {
        setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        setBackground(Color.BLACK);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 

        Graphics2D g2d = (Graphics2D) g;
        
        Font f = new Font("monospaced",Font.BOLD,200);
        g2d.setFont(f);
        
        FontMetrics fm = g2d.getFontMetrics();
        int strWid = fm.stringWidth("Sujith");
        int strHei = fm.getHeight();
        int a= fm.getAscent();
        
        int x = (SCREEN_WIDTH-strWid)/2;
        int y = (SCREEN_HEIGHT-strHei)/2;
        
        g2d.drawString("Sujith",x ,y+a);
        g2d.setPaint(Color.red);
        g2d.drawRect(x, y, strWid, strHei);
    //    g2d.draw(rect);
        
    }

    
}

public class NewMain extends JFrame {

    public static void main(String[] args) {
        Canvas c = new Canvas();
        JFrame f = new JFrame("Text Rendering Tricks");
        f.add(c);
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        
        f.setVisible(true);
        f.pack();
        f.setLocationRelativeTo(null);
    }

}
