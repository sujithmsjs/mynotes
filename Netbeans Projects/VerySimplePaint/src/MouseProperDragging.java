
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

public class MouseProperDragging extends Canvas {

    Rectangle2D rect = new Rectangle2D.Double(50, 50, 100, 100);
    boolean isKeyHolded = false;
    Point keyHoldedAt;

    int xGap;
    int yGap;
    
    //Mouse variables
    int n = 0;

    public MouseProperDragging() {
        setPreferredSize(SCREEN_SIZE);
        addMouseListener(new ML());
        addMouseMotionListener(new ML());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.fill(rect);
    }

    class ML extends InputAdapter {

   
       

        @Override
        public void mouseMoved(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {

            if (isKeyHolded) {
                
                
                System.out.println("xGap = " + xGap);
                
                System.out.println("yGap = " + yGap);
                

                rect.setFrame(e.getX() - xGap, e.getY() - yGap, rect.getWidth(), rect.getHeight());
                repaint();
                System.out.println("Mouse holded...");
            }
            System.out.println("Mouse dragging..." +(n++)+" " + isKeyHolded);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
            if (rect.contains(e.getX(), e.getY())) {
                //Key holded is True here.
                isKeyHolded = true;
             //   keyHoldedAt = e.getPoint();
                xGap = e.getX() - rect.getBounds().x;
                yGap = e.getY() - rect.getBounds().y;
                System.out.println("Mouse pressed on box..." + isKeyHolded);
                
            }else{
                
                System.out.println("Mouse pressed out size box...");
            }
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //Key holded is false here.
             System.out.println("Mouse released"+isKeyHolded);
            isKeyHolded = false;
           
        }

    }

    //<editor-fold defaultstate="collapsed" desc="Main Method">
    public static void main(String[] args) {
        MouseProperDragging canvas = new MouseProperDragging();
        MyFrame frame = new MyFrame(canvas, "Very Simple Painting");
        frame.add(canvas);
        frame.setVisible(true);
    }
//</editor-fold>
}
