package suji.ani;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.geom.Ellipse2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import learn.animation.util.BGUtil;

class DirectionOvalPanel extends JPanel implements Runnable {

    //USER Settings
    public final Ellipse2D.Double oval = new Ellipse2D.Double(0, 0, 30, 30);
    public static final Dimension SCREEN_SIZE = new Dimension(500, 500);
    public static final Paint BACK_GROUND = new GradientPaint(100,100,Color.BLACK,SCREEN_SIZE.width+100,SCREEN_SIZE.height+100,Color.RED);
    
    
    
    
    
    Thread gameThread;

    int xDirection = 1;
    int yDirection = 1;
    int xVelocity = 0;
    int yVelocity = 0;
    int step = 10;

    Image image;
    Graphics graphics;

    public DirectionOvalPanel() {
        //Settings
        setPreferredSize(SCREEN_SIZE);
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new KL());

    //    Timer
        gameThread = new Thread(this);
        gameThread.start();

        newDirectionOval();

    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawBackground(g2d);
        g2d.setPaint(Color.WHITE);
        g2d.fill(oval);
       
    }

    private void setXDirection(int xDirection) {
        xVelocity = xDirection * step;
    }

    private void setYDirection(int yDirection) {
        yVelocity = yDirection * step;
    }

    public void move() {
        oval.x += xVelocity;
        oval.y += yVelocity;
    }

    private void drawBackground(Graphics2D g) {
        g.setPaint(BACK_GROUND);
        g.fillRect(0, 0, SCREEN_SIZE.width, SCREEN_SIZE.height);
    }
    
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        image = createImage(getWidth(), getHeight());
        
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
//        //  draw(g);
    }

    public void newDirectionOval() {
        oval.x = (SCREEN_SIZE.width / 2) - (oval.width / 2);
        oval.y = (SCREEN_SIZE.height / 2) - (oval.height / 2);
    }

    @Override
    public void run() {
        //Game loop
        long lastTime = System.nanoTime();

        double amountOfTicks = 60.0;
        double ns = 1_000_000_000 / amountOfTicks;
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                delta--;


                move();
                checkCollisions();
                repaint();

            }
        }
    }

    private void checkCollisions() {
        if(oval.x <= 0){
           oval.x = 0;
        }
        if(oval.x >= SCREEN_SIZE.width - oval.width){
            oval.x = SCREEN_SIZE.width - oval.width;
        }
        
        if(oval.y <= 0){
            oval.y =0;
        }
        if(oval.y >= SCREEN_SIZE.height - oval.height){
            oval.y = SCREEN_SIZE.height - oval.height;
        }
    }

    private class KL extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {

                case KeyEvent.VK_UP:
                    setYDirection(-1);
                    setXDirection(0);
                    break;

                case KeyEvent.VK_DOWN:
                    setYDirection(1);
                    setXDirection(0);
                    break;

                case KeyEvent.VK_LEFT:
                    setYDirection(0);
                    setXDirection(-1);
                    break;

                case KeyEvent.VK_RIGHT:
                    setYDirection(0);
                    setXDirection(1);

                    break;

            }
            move();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            setYDirection(0);
            setXDirection(0);
        }

    }

}

//<editor-fold defaultstate="collapsed" desc="DirectionOval Class">
public class DirectionOval extends JFrame {

    DirectionOvalPanel ovalPanel;

    public DirectionOval() {
        ovalPanel = new DirectionOvalPanel();
        add(ovalPanel);
        setTitle("All Directions Check");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new DirectionOval().setVisible(true);
    }

}
//</editor-fold>
