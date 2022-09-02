

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


public class Sprite extends Rectangle {

    private Color color;
    private boolean visible;
    Image image;

    public Sprite(Color color, boolean visible, Image image, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.color = color;
        this.visible = visible;
        this.image = image;
    }

    public Sprite(Image image,int x,int y) {
        this(Color.WHITE,true,image,x,y,100,100);
    }
    public Sprite(){
        this(null,0,0);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    
    
    
    
    public final void drawImage(Graphics2D g){
        
        if(image!=null){
            g.drawImage(image, x, y, width, height, null);
            System.out.println("draw");
        }else{
            g.setColor(color);
            g.setStroke(new BasicStroke(3,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
            g.draw(this);
        }
        
    }

    @Override
    public String toString() {
        
        return "Sprite{" + "color=" + color + ", visible=" + visible + ", image=" + image + ", x=" + x + ", y=" + y + ", w=" + width + ", h=" + height + '}';
    }
    
    
}
