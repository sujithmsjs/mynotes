package game.loop;

import com.zetcode.tetris.Shape;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.RectangularShape;


public class Sprite{

    private Color color;
    private RectangularShape shape;

    Sprite(RectangularShape shape){
        this.shape = shape;
    }

    public Sprite() {
    
    }
    
    
    
    public void moveTo(int x, int y){
        shape.setFrame(x, y, shape.getX(), shape.getY());
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    
    
   
    
}
