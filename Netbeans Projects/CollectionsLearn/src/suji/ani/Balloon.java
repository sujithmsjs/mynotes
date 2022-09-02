package suji.ani;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;



class BalloonPanel extends JPanel implements ActionListener {

    public final Ellipse2D.Double balloon;
    public static final Dimension SCREEN_SIZE = new Dimension(500, 800);
    private static final Random random = new Random();

    private int xDir[] = {-3, -2, -1, 0, 1, 2, 3}; //5Directions
    private int yDir = -1;
    private Timer timer;

    public BalloonPanel() {
        balloon = new Ellipse2D.Double();
        timer = new Timer(10, this);

        setPreferredSize(SCREEN_SIZE);
        setBackground(Color.black);
        newBalloon();
        timer.start();
    }

    public void draw(Graphics2D g) {
        g.setPaint(Color.ORANGE);
        g.fill(balloon);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        balloon.y += yDir;
        balloon.x += xDir[random.nextInt(5)];

        //Boundires
        if (balloon.x <= 0) {
            balloon.x += 3;
        }
        if (balloon.x >= SCREEN_SIZE.width - balloon.width) {
            balloon.x -= 3;
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        draw(g2d);
    }

    public void newBalloon() {
        int xA = random.nextInt((int) (SCREEN_SIZE.width - balloon.width));
        balloon.setFrame(xA, SCREEN_SIZE.height, 30, 30);
    }

}

//<editor-fold defaultstate="collapsed" desc="Balloon Class">
public class Balloon extends JFrame {

    BalloonPanel balloonPanel;

    public Balloon() {
        balloonPanel = new BalloonPanel();
        add(balloonPanel);
        setTitle("Ballon Animation");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new Balloon().setVisible(true);
    }

}
//</editor-fold>
