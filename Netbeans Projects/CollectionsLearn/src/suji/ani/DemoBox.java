package suji.ani;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

class DemoBoxPanel extends JPanel implements Runnable{

    //USER Settings
    public final Rectangle.Double box = new Rectangle.Double(0, 0, 30, 30);
    public static final Dimension SCREEN_SIZE = new Dimension(500, 800);
    Thread gameThread;
    
    int xDirection = 1;
    int yDirection = 1;
    int xVelocity = 0;
    int yVelocity = 0;
    int step =10 ;
    
    Image image;
    Graphics graphics;

    public DemoBoxPanel() {
        //Settings
        setPreferredSize(SCREEN_SIZE);
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(new KL());

        //Timer
        gameThread = new Thread(this);
        gameThread.start();
        
        newDemoBox();
        
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.WHITE);
        g2d.fill(box);
    }
    
    private void setXDirection(int xDirection) {
        xVelocity = xDirection * step;
    }
    
    private void setYDirection(int yDirection) {
        yVelocity = yDirection * step;
    }
    

    public void move() { 
        box.x += xVelocity;
        box.y += yVelocity;
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
          //  draw(g);
    }

    public void newDemoBox() {
        box.x = (SCREEN_SIZE.width/2) - (box.width/2);
        box.y = (SCREEN_SIZE.height/2) - (box.height/2);   
    }

    @Override
    public void run() {
        //Game loop
        long lastTime = System.nanoTime();

        double amountOfTicks = 120.0;
        double ns = 1_000_000_000 / amountOfTicks;
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                delta--;
                
                move();
                repaint();
                
            }
        }
    }

    private class KL extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()){
                
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

//<editor-fold defaultstate="collapsed" desc="DemoBox Class">
public class DemoBox extends JFrame {

    DemoBoxPanel boxPanel;

    public DemoBox() {
        boxPanel = new DemoBoxPanel();
        add(boxPanel);
        setTitle("Ballon Animation");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new DemoBox().setVisible(true);
    }

}
//</editor-fold>
