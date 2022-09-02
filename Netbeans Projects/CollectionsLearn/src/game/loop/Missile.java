package game.loop;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.awt.geom.Ellipse2D;
import java.io.File;
import javax.swing.ImageIcon;



public class Missile extends Rectangle implements ActionListener {

    public static final Dimension MISSILE_SIZE = new Dimension(100,20);
    
    Timer t;
    Image image;
    int DELAY =4;
    int xVelocity = 10;
    boolean isDestroyed = false;

    public boolean isDestroyed() {
        return isDestroyed;
    }

    Missile(double x, double y) {
        super((int)x,(int)y,100,20);
        loadImage();
        t = new Timer(DELAY, this);
    }
    
    private void loadImage() {
        String imageFile = "C:\\Users\\sujit\\OneDrive\\Desktop\\Game\\missile.png";
        ImageIcon img = new ImageIcon(imageFile);
        java.io.File file = new File(imageFile);
        System.out.println("is Missile image exists : " + file.exists());
        image = img.getImage();
    }

    public void start() {
        t.start();
    }

    public void drawImage(Graphics2D g) {
        g.drawImage(image, x,y,width,height, null);
        g.draw(this);
       // System.out.println("Bullet painting...");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (x - width <= Canvas3.SCREEN_SIZE.width) {          
            x += xVelocity;
            
            System.out.println("missile Moving..." + x + " " + this);
        } else {
            isDestroyed = true;
            System.out.println("missile Destroyed..." + x + " " + this);
            t.stop();
        }
    }

}
