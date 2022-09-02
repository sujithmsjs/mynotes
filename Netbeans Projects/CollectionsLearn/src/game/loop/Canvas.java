package game.loop;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Arc2D;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;


public class Canvas extends JPanel implements Runnable {

    public static final Dimension SCREEN_SIZE = new Dimension(640,480);
    private Thread gameLoop;
    private Image image;
    private Arc2D arc;
    private int speed = 15;
    
    
    private Point direction = new Point(0,0);
    private Point velocity = new Point(1,1);
    
    
    private java.util.List<Bullet> bullets = new Vector<>();
    
    
    
    
    
    public Canvas() {
        setPreferredSize(SCREEN_SIZE);
        setBackground(Color.BLACK);
      //  loadImage();
        setFocusable(true);
        addKeyListener(new KL());
      arc = new Arc2D.Double(0, 0, 50, 50, 30, 300, Arc2D.PIE);
      gameLoop = new Thread(this);
      gameLoop.start();
    }
    
    public void loadImage(){
        String imageFile = "";
        java.io.File file = new File(imageFile);
        if(file.exists()){
            try {
                image = ImageIO.read(file);
                gameLoop.start();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }else{
            System.out.println("Image not found:(");
        }
    }
    
    public void doDrawing(Graphics2D g){
        g.setPaint(Color.RED);
        for (Bullet bullet : bullets) {
            bullet.draw(g);
            System.out.println("hellow");
        }
        g.setPaint(Color.YELLOW);
        g.fill(arc);
  
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        doDrawing(g2d);
    }
    
    public void checkCollision(){
        
        for (Bullet bullet : bullets) {
            if(bullet.isDestroyed){
                bullets.remove(bullet);
                break;
            }
        }
        
        if(arc.getX()<0){
            arc.setFrame(0, arc.getY(), arc.getWidth(), arc.getHeight());
        }
        
        if(arc.getY() < 0){
            arc.setFrame(arc.getX(), 0, arc.getWidth(), arc.getHeight());
        }
        
        if(arc.getMaxX()>SCREEN_SIZE.width){
            arc.setFrame(SCREEN_SIZE.width - arc.getWidth(), arc.getY(), arc.getWidth(), arc.getHeight());
        }
        if(arc.getMaxY()>SCREEN_SIZE.height){
            arc.setFrame(arc.getX(),SCREEN_SIZE.height - arc.getHeight(), arc.getWidth(), arc.getHeight());
        }
            
        
    }

    @Override
    public void run() {
        
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1_000_000_000 / amountOfTicks;
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                move();
                checkCollision();
                repaint();
                delta--;
                //System.out.println("Test");
            }
        }
        
        
        /*
        try {
            while(true){
                Thread.sleep( 1000/100);
                move();
                repaint();
            }
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        */
    }
    
    private void move() {
        velocity.x = speed * direction.x;
        velocity.y = speed * direction.y;
        setVelocity();
    }
    
    private void setVelocity(){
         arc.setFrame(arc.getX()+velocity.x, arc.getY()+velocity.y, arc.getWidth(), arc.getHeight());
    }
    
    class KL extends KeyAdapter{

        @Override
        public void keyReleased(KeyEvent e) {
            //<editor-fold defaultstate="collapsed" desc="Old code">
            /* --------------------------
            OLD CODE FOR REFERENCE.
            --------------------------
            switch(e.getKeyCode()){
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_LEFT:
            direction.x = 0;
            direction.y = 0;
            System.err.println("Mouse Released." + e.getKeyCode());
            }*/
//</editor-fold>

            switch (e.getKeyCode()) {
                case KeyEvent.VK_DOWN:
                    direction.y = 0;
                    break;
                case KeyEvent.VK_UP:
                    direction.y = 0;
                    break;
                case KeyEvent.VK_RIGHT:
                    direction.x = 0;
                    break;
                case KeyEvent.VK_LEFT:
                    direction.x = 0;
                    break;
            }
            
        }
            

        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()){
                case KeyEvent.VK_DOWN:
                        direction.y = 1;
                    break;
                case KeyEvent.VK_UP:        
                        direction.y = -1;
                    break;
                case KeyEvent.VK_RIGHT:                             
                        direction.x = 1;                    
                    break;
                case KeyEvent.VK_LEFT:                    
                        direction.x = -1;                    
                    break;
            }
            if(e.getKeyCode()==KeyEvent.VK_SPACE){
                Bullet b = new Bullet(arc.getCenterX(), arc.getCenterY());
                bullets.add(b);
                b.start();
            }   
        }
        
    }

    

}


class MyFrame extends JFrame{

    Canvas c;
    
    public MyFrame() {
        super("Game loop test");
        c = new Canvas();
        add(c);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }
    
    public static void main(String[] args) {
        new MyFrame().setVisible(true);
    }
    
}