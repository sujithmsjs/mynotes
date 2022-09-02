
import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public class PathTestOk extends Canvas {

    PathShape path = new PathShape();

    public PathTestOk() {
        setFocusable(true);
        addMouseMotionListener(new ML());
        addMouseListener(new ML());
        addKeyListener(new ML());
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(0x1234567));

        path.draw(g2d);

    }

    class ML extends InputAdapter {

        @Override
        public void mouseMoved(MouseEvent e) {
            path.mouseMoved(e);
            repaint();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            path.mousePressed(e);
            repaint();
        }

        @Override
        public void keyPressed(KeyEvent e) {
            path.keyPressed(e);
            repaint();
        }

    }
    
//<editor-fold defaultstate="collapsed" desc="Main method">
    
    public static void main(String[] args) {
        PathTestOk canvas = new PathTestOk();
        MyFrame frame = new MyFrame(canvas, "Very Simple Painting");
        frame.add(canvas);
        frame.setVisible(true);
    }
//</editor-fold>
}
