

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.util.Random;
import javax.swing.ImageIcon;


public class Star extends Sprite {

    private int speed = 4;
    
    Star(){
        initStar();       
    }
    
    public void initStar(){
        setColor(Color.RED);
        setBounds(x, y, 40, 40);
        
        String filePath = "C:\\Users\\sujit\\OneDrive\\Desktop\\Java created\\enimy.png";
        if (new File(filePath).exists()) {
            image = new ImageIcon(filePath).getImage();
        } else {
            image = null;
        }
        
        setImage(image);
    }
    
    public void move(){
        x-=speed;
        
        if(getMaxX()<=0){
            setVisible(false);
        }
    }
}
