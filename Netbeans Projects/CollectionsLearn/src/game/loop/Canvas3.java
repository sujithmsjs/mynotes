package game.loop;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Arc2D;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;


public class Canvas3 extends JPanel implements Runnable {

    public static final Dimension SCREEN_SIZE = new Dimension(800,700);
    private SpaceShip ship;
    
    private Thread gameLoop;
    //Firing properties
    private long lastFiredAt;
    private long gunReloadingTime = (long) (1_000_000_000 * 0.25); 
 
    private java.util.List<Missile> bullets = new Vector<>();
    
    public Canvas3() {
        setPreferredSize(SCREEN_SIZE);
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new KL());
        ship = new SpaceShip(0,0);
        
      gameLoop = new Thread(this);
      gameLoop.start();
    }
    

    public void doDrawing(Graphics2D g){
        for (Missile missile : bullets) {
             missile.drawImage(g);
        }
        ship.drawImage(g);
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        doDrawing(g2d);
    }
    
    public void checkCollision(){
        
        for (Missile bullet : bullets) {
            if(bullet.isDestroyed){
                bullets.remove(bullet);
                break;
            }
        }
        
        if(ship.getX()<0){
           ship.x = 0;
        }
        
        if(ship.getY() < 0){
            ship.y = 0;
        } 
        
        if(ship.getMaxX()>SCREEN_SIZE.width){
           ship.x = SCREEN_SIZE.width - ship.width;
        }
        if(ship.getMaxY()>SCREEN_SIZE.height){
           ship.y = SCREEN_SIZE.height - ship.height;
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
                ship.move();
                fire();
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
    

    
    public void fire() {
        
        if(ship.isIsFiring() && (System.nanoTime()-lastFiredAt)>gunReloadingTime){
            Missile b = new Missile( ship.getX()+50 , ship.getCenterY() - Missile.MISSILE_SIZE.height/2);
            bullets.add(b);
            lastFiredAt = System.nanoTime();
            b.start();
        }
    }
    
    
    class KL extends KeyAdapter{

        @Override
        public void keyReleased(KeyEvent e) {
            ship.keyReleased(e);
        }
            

        @Override
        public void keyPressed(KeyEvent e) {
           ship.keyPressed(e);
             
        }
        
    }
    
 

    

}


class MyFrame3 extends JFrame{

    Canvas3 c;
    
    public MyFrame3() {
        super("Game loop test");
        c = new Canvas3();
        add(c);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }
    
    public static void main(String[] args) {
        new MyFrame3().setVisible(true);
    }
    
}