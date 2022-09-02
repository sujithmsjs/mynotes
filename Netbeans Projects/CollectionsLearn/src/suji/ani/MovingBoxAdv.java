package suji.ani;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

class MovingBoxAdvPanel extends JPanel implements ActionListener {

    //USER Settings
    public final Rectangle.Double box = new Rectangle.Double(0, 0, 30, 30);
    public static final Dimension SCREEN_SIZE = new Dimension(500, 800);
    private static final Random random = new Random();

    private static Director direction = new Director(Director.STOP);
    private static int step = 1;
    /*
    DIRECTION X STEP = DISPLACEMENT.
    DIRECTION works as a HEAD.
    STEP words as a LEG.
     */

    private Timer timer;

    public MovingBoxAdvPanel() {
        //Initialization
        timer = new Timer(5, this);

        //Settings
        setPreferredSize(SCREEN_SIZE);
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(new KL());

        newMovingBoxAdv();
        timer.start();
    }

    public void draw(Graphics2D g) {
        g.setPaint(Color.RED);
        g.fill(box);
        System.out.println("Paint");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int xDisplacement = (int) (box.x + (step * direction.x));
        box.x = xDisplacement;

        int yDisplacement = (int) (box.y + (step * direction.y));
        box.y = yDisplacement;

        //Check ccllides
        if (box.x <= 0) {
            direction.x *= -1; // Simply Flipping
        } else if (box.x >= SCREEN_SIZE.width - box.width) {
            direction.x *= -1; // Simply Flipping
        }

        //Check ccllides
        if (box.y <= 0) {
            direction.y *= -1; // Simply Flipping
        } else if (box.y >= SCREEN_SIZE.height - box.height) {
             direction.y *= -1; // Simply Flipping
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        draw(g2d);
    }

    public void newMovingBoxAdv() {
        box.x = (SCREEN_SIZE.width + box.width) / 2;
        box.y = (SCREEN_SIZE.height + box.height) / 2;
    }

    private class KL extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("Key pressed");

            System.out.println(direction);

            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    direction.turnSlightLeft();
                    break;

                case KeyEvent.VK_RIGHT:
                    direction.turnSlightRight();
                    break;
                case KeyEvent.VK_UP:
                    direction.setDirection(Director.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    direction.setDirection(Director.DOWN);
            }
            repaint();
        }
    }

}

//<editor-fold defaultstate="collapsed" desc="MovingBoxAdv Class">
public class MovingBoxAdv extends JFrame {

    MovingBoxAdvPanel boxPanel;

    public MovingBoxAdv() {
        boxPanel = new MovingBoxAdvPanel();
        add(boxPanel);
        setTitle("Ballon Animation");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new MovingBoxAdv().setVisible(true);
    }

}
//</editor-fold>
