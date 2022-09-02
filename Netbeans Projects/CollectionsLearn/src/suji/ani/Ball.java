package suji.ani;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import learn.animation.util.BGUtil;

//<editor-fold defaultstate="collapsed" desc="Ball Class">
public class Ball extends JFrame {

    BallPanel ballPanel;
   

    public Ball() {
        ballPanel = new BallPanel();
       
        add(ballPanel);
        setTitle("Ballon Animation");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new Ball().setVisible(true);
    }

}
//</editor-fold>

class BallPanel extends JPanel implements ActionListener {

    //User Settings
    public final Ellipse2D.Double ball;
    public static final Dimension SCREEN_SIZE = new Dimension(400, 400);
    private static final int FLIPS = 30;
    private static final int DELAY = 5;
    private  Paint ballPaint;
    
    
    
    private static final Random random = new Random();
    private Timer timer;
    private int funFlipper=0;
    private final Point velocity;

    public BallPanel() {
        ball = new Ellipse2D.Double();

        timer = new Timer(DELAY, this);
        velocity = new Point(1,1);
                
        setPreferredSize(SCREEN_SIZE);
        setBackground(Color.black);
        newBall();
        timer.start();
    }

    public void draw(Graphics2D g) {
        g.setPaint(BGUtil.getRadialGradientPaint(ball));
         
        g.fill(ball);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        ball.x += velocity.x;
        
        if(ball.x >= (SCREEN_SIZE.width-ball.width)){
        velocity.x *= -1; // Fliping Velocity
        }
        if(ball.x <= 0){
        velocity.x *= -1; // Fliping Velocity
        }
        
        ball.y += velocity.y;
        
        if (ball.y >= (SCREEN_SIZE.height - ball.height)) {
            velocity.y *= -1; // Fliping Velocity
        }
        if (ball.y <= 0) {
            velocity.y *= -1; // Fliping Velocity
        }
        
        funFlipper++;
        
        if(funFlipper>=FLIPS){
            switch(random.nextInt(2)){
                case 1:
                    velocity.x *= -1; // Fliping Velocity
                    break;
                case 2:
                    velocity.y *= -1; // Fliping Velocity
                    break;
            }
            funFlipper=0;
        }
        
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        draw(g2d);
    }

    public void newBall() {
      //  int xA = random.nextInt((int) (SCREEN_SIZE.width - ball.width));
      int randomX = random.nextInt((int) (SCREEN_SIZE.width - ball.width));
        System.out.println("randomX = " + randomX);
      int randomY = random.nextInt((int) (SCREEN_SIZE.height - ball.height));
        System.out.println("randomY = " + randomY);
        
        ball.setFrame(randomX, randomY, 30, 30);
      
    }
}
