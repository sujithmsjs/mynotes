package learn.animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;
import learn.animation.util.BGUtil;


public class RadialGradientPaintDemo extends JPanel {

    private final Dimension SCREEN_SIZE  = new Dimension(500,500);
    Ellipse2D.Double oval = new Ellipse2D.Double(100, 100, 100, 100);
    
    private final Paint BACK_GROUND;

    public RadialGradientPaintDemo() {
        BACK_GROUND = Color.black;
        
        setBackground(Color.BLACK);  
        
        setPreferredSize(SCREEN_SIZE);
        
        setFocusable(true);
    }


    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setPaint(BGUtil.getRadialGradientPaint(oval)); 
        
        g2d.fill(oval);

        
    }

    public static void main(String[] args) {
        RadialGradientPaintDemo S = new RadialGradientPaintDemo();
        new MyFrame(S);

    }


}
