package learn.animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import javax.swing.JPanel;
import learn.animation.util.BGUtil;

public class FontMetricDemo extends JPanel {

    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 600;
    public static final int UNIT_SIZE = 10;

    public FontMetricDemo() {
        initUI();
    }

    private void initUI() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        setBG(g2d);
        
        Font font = new Font("Ink Free", Font.BOLD, 45);
        g.setFont(font);
        g.getFontMetrics();
        String text = "Sujith";
//        FontMetrics fm = g2d.getFontMetrics();
//        int n = fm.stringWidth(text);
//        System.out.println(n);
        g2d.setPaint(Color.BLACK);
        g2d.drawString(text, 0, 0);
        
        }
         
    

    public static void main(String[] args) {
        new MyFrame(new FontMetricDemo());

    }

    private void setBG(Graphics2D g) {
        Paint paint = BGUtil.g;
        g.setPaint(paint);
        g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

}
