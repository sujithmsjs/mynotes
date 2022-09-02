package game.loop;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.awt.geom.Ellipse2D;


public class Bullet implements ActionListener{
    
    Timer t;
    int DELAY = 10;
    Ellipse2D bullet;
    int xVelocity = 10;
    boolean isDestroyed = false;
    
    public boolean isDestroyed(){
        return isDestroyed;
    }
    
    Bullet(double x, double y){
        t = new Timer(DELAY,this);
        bullet = new Ellipse2D.Double(x, y, 10, 10);
    }
    public void start(){
        t.start();
    }
    
    public void draw(Graphics2D g){
        g.fill(bullet);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(bullet.getX()<= Canvas.SCREEN_SIZE.width){
            bullet.setFrame(bullet.getX() + xVelocity, bullet.getY(), bullet.getWidth(), bullet.getHeight());
            
            System.out.println("Bullet at "+bullet.getX());
        }else{
            isDestroyed = true;
            System.out.println("bullet Destroyed..." + bullet.getX() + " " + this);
            t.stop();
        }
        
    }
 
}
