package learn.animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JPanel;

public class GradientSelector02 extends JPanel {

    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 600;
    public static final int UNIT_SIZE = 10;
    private Random ran;
    
    //Rect Utils
    int x,y;
    int width = SCREEN_WIDTH;
    int height = SCREEN_HEIGHT;
    
    //Gradient Utils
    private static final int INC_AMOUNT = 5;
    private Color bg = Color.WHITE;
    private Color fg = Color.BLACK;
    boolean cyclic = true;
    private int val1;
    private int val2;
    private int val3;
    private int val4;

    public GradientSelector02() {
        initUI();
        System.out.println("Added.");
    }

    private void initUI() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setFocusable(true);
        addKeyListener(new MyKeyAdapter());
        ran = new Random();
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        GradientPaint gradientPaint = new GradientPaint(val1, val2,
                bg, val3, val4, fg, false);

        g2d.setPaint(gradientPaint);
        g2d.fillRect(x, y, width, height);

        g2d.dispose();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    public static void main(String[] args) {
        new MyFrame(new GradientSelector02());

    }

    public class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    val1 -= INC_AMOUNT;
                    break;
                case KeyEvent.VK_RIGHT:
                    val1 += INC_AMOUNT;
                    break;

                case KeyEvent.VK_DOWN:
                    val2 -= INC_AMOUNT;
                    break;
                case KeyEvent.VK_UP:
                    val2 += INC_AMOUNT;
                    break;
                case KeyEvent.VK_A:
                    val3 -= INC_AMOUNT;
                    break;
                case KeyEvent.VK_D:
                    val3 += INC_AMOUNT;
                    break;

                case KeyEvent.VK_S:
                    val4 -= INC_AMOUNT;
                    break;
                case KeyEvent.VK_W:
                    val4 += INC_AMOUNT;
                    break;
                case KeyEvent.VK_B:
                    bg = new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256));
                    break;
                case KeyEvent.VK_F:
                    fg = new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256));
                    break;
                 case KeyEvent.VK_C:
                    cyclic = !cyclic;
                 break;
            }
            
            int bgr = bg.getRed();
            int bgg = bg.getGreen();
            int bgb = bg.getBlue();
           
            int fgr = bg.getRed();
            int fgg = bg.getGreen();
            int fgb = bg.getBlue();
            
            
            System.out.println("GradientPaint g = new GradientPaint(" + val1 + ", " + val2 + ","
                    + "new Color("+bgr+","+bgg+","+bgb+")," + val3 + "," + val4 + ",new Color("+fgr+","+fgg+","+fgb+"),"+cyclic+");");
            repaint();
        }
    }

}
