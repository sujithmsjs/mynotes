package suji.ani;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import java.util.*;


public class SpaceShip extends Sprite {

    private int dy = 1;
    private int velocity = 2;
    private int gravity = 3;
    
    public SpaceShip() {
        initShip();
    }
    
   public void initShip(){
       
       String filePath = "C:\\Users\\sujit\\OneDrive\\Desktop\\images\\ship.png";
       Image image = null;
       if(new File(filePath).exists()){
           image = new ImageIcon(filePath).getImage();
       }else{
           image = null;
       }
       
       setColor(Color.red);
       setImage(image);
       setLocation(200, 0);
       
   }
   
    public void move(){
        setGravity();
        setVelocity();
    }
    public void setGravity(){
        y += gravity*dy;
    }
    
    public void setVelocity(){
        y -= dy*velocity;
    }

    void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            dy = 1;
        }
    }

    void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_UP){
            dy = -1;
        }
    }
   
   
   
}
