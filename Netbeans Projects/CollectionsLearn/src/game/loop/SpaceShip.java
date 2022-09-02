package game.loop;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.ImageIcon;


public class SpaceShip extends Rectangle {
    
    private Image image; 
    private int speed = 20;
    private Point direction;
    private Point velocity;
    private boolean isFiring;
    
    

    public int getSpeed() {
        return speed;
    }

    public Point getDirection() {
        return direction;
    }

    public boolean isIsFiring() {
        return isFiring;
    }
    
    public SpaceShip(int x, int y, int width, int height) {
        super(x, y, width, height);
        loadImage();
        direction = new Point(0,0);
        velocity = new Point(1,1);
    }
    
    public SpaceShip(int x, int y) { 
        this(x,y, 100,100);
        loadImage();
    }
    
    
    public void move() {
        velocity.x = speed * direction.x;
        velocity.y = speed * direction.y;
        setVelocity();
    }

    private void setVelocity() {
      x  += velocity.x;
      y  += velocity.y;
     //   System.out.println( "x: "+x+"; y:"+y);
    }

    
    private void loadImage(){
        String imageFile = "C:\\Users\\sujit\\OneDrive\\Desktop\\Game\\ship.png";
        ImageIcon img = new ImageIcon(imageFile);
        java.io.File file = new File(imageFile);
        System.out.println("is Spaceship image exists: " + file.exists());
        image = img.getImage();
    }
    
    public void drawImage(Graphics2D g){
        g.drawImage(image,x,y, width, height,null);  
    }
    
    
    public void keyReleased(KeyEvent e) {
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
            case KeyEvent.VK_SPACE:
                isFiring = false;
                break;
        }
    }
    
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
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
            case KeyEvent.VK_SPACE:
                isFiring = true;
                break;
        }
    }
}
