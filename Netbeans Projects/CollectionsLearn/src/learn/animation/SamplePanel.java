package learn.animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.image.RenderedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import learn.animation.util.BGUtil;

public class SamplePanel extends JPanel {

    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 600;
    public static final int UNIT_SIZE = 10;

    boolean isDrawn = false;
    
    public SamplePanel() {
        initUI();
    }
    
    private void initUI(){
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setFocusable(true);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
     //   super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Paint paint=  BGUtil.getGradientPaint(Color.YELLOW, Color.white);
        
        g2d.setPaint(paint);
        g2d.fillRect(0, 0,SCREEN_WIDTH, SCREEN_HEIGHT);
        
        g2d.setPaint(Color.BLACK);
        Font font = new Font(Font.SANS_SERIF, Font.BOLD,50);
        
        
        
        
        FontMetrics fm = g2d.getFontMetrics(font);
        String text = "Sujith";
        
              
        for (int i = 0; SCREEN_HEIGHT >= fm.stringWidth(text) ; i++) {
            font = new Font(Font.SANS_SERIF, Font.BOLD,i);
            fm = g2d.getFontMetrics(font);
        }
        g2d.setFont(font);
        g2d.drawString(text, 0, 400);
         System.out.println("Hellow");
    }
    
    public static void main(String[] args) {
        SamplePanel S = new SamplePanel();
        new MyFrame(S);
 
    }

}
