
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.*;
import javax.swing.Timer;

public class Board7 extends Canvas implements ActionListener {

    ShapePlus shape;
    PathShape path;

    AffineTransform af = new AffineTransform();
    Timer t;
    Dimension d = new Dimension(100, 200);

    double angle;

    public Board7() {
        draw();
        setFocusable(true);
        addKeyListener(new KA());
        // t = new Timer(60,this);
        //  t.start();

    }

    private void draw() {
        path = new PathShape();
        path.add(0, 0);
        path.add(200, 100);
        path.add(0, 200);
        path.closePath();

        shape = new ShapePlus(path);
        shape.setAngle(30);

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(0x1234567));
        shape.drawMe(g2d);
        //  path.draw(g2d);

    }

    public static void main(String[] args) {
        Board7 canvas = new Board7();
        MyFrame frame = new MyFrame(canvas, "Very Simple Painting");
        frame.add(canvas);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        repaint();
    }

    class KA extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:

                    break;
                case KeyEvent.VK_RIGHT:

                    break;
            }
            repaint();

        }

    }
}
