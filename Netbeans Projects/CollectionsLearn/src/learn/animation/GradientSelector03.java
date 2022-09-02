package learn.animation;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.geom.Point2D;
import java.util.Random;
import javax.swing.JPanel;

public class GradientSelector03 extends JPanel {

    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 600;
    public static final int UNIT_SIZE = 10;
    private Random ran;

    //Rect Utils
    int x, y;
    int width = SCREEN_WIDTH;
    int height = SCREEN_HEIGHT;

    //Gradient Utils
    private Color color1 = Color.WHITE;
    private Color color2 = Color.BLACK;
    private Point2D pt1;
    private Point2D pt2;
    GradientPaint gradientPaint;

    //Gradient Line
    int x1, x2, y1, y2;
    boolean isMouseDragging;

    public GradientSelector03() {
        initUI();
        System.out.println("Added.");
    }

    private void initUI() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setFocusable(true);

        //Adding Listeners
        addMouseListener(new MyMouseListener());
        addMouseMotionListener(new MyMouseListener());
        addKeyListener(new MyKeyAdapter());

        ran = new Random();
        pt1 = new Point2D.Double(0, 0);
        pt2 = new Point2D.Double(SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g.create();

        if (!isMouseDragging) {
            gradientPaint = new GradientPaint(pt1, color1, pt2, color2);
            System.out.println("new Gradient Setted.");
        }
        g2d.setPaint(gradientPaint);
        g2d.fillRect(x, y, width, height);

        g2d.setPaint(Color.BLACK);
        g2d.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.CAP_ROUND));
        if(isMouseDragging)
            g2d.drawLine(x1, y1, x2, y2);
        g2d.dispose();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    public static void main(String[] args) {
        new MyFrame(new GradientSelector03());
        
    }

    class MyMouseListener extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("Mouse Pressed");
            Point p = e.getPoint();
            x1 = p.x;
            y1 = p.y;
            pt1 = new Point2D.Double(x1, y1);
            isMouseDragging = true;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            Point p = e.getPoint();
            x2 = p.x;
            y2 = p.y;
            pt2 = new Point2D.Double(x2, y2);
            isMouseDragging = false;
            if (!isMouseDragging) {
                repaint();
                showDesc();
            }

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);
            Point p = e.getPoint();
            x2 = p.x;
            y2 = p.y;
            repaint();
        }

        private void showDesc() {
            int bgr = color1.getRed();
            int bgg = color1.getGreen();
            int bgb = color1.getBlue();

            int fgr = color1.getRed();
            int fgg = color1.getGreen();
            int fgb = color1.getBlue();
            
            
            System.out.println("gradientPaint = new GradientPaint(new Point2D.Double("+pt1.getX()+", "+pt1.getY()+"),new Color("+bgr+","+bgg+","+bgb+"), new Point2D.Double("+pt2.getX()+", "+pt2.getY()+"), new Color("+fgr+","+fgg+","+fgb+"));");
           // System.out.println("GradientPaint g = new GradientPaint(" + val1 + ", " + val2 + ","
                //    + "new Color("+bgr+","+bgg+","+bgb+")," + val3 + "," + val4 + ",new Color("+fgr+","+fgg+","+fgb+"),"+cyclic+");");
        }

    }

    public class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {

                case KeyEvent.VK_B:
                    color1 = new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256));
                    break;
                case KeyEvent.VK_F:
                    color2 = new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256));
                    break;
            }
            repaint();
        }
    }
    
    

}
