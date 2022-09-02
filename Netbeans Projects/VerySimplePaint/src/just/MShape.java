package just;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;


public class MShape {

    private Shape shape;
    private Color color;
    private boolean visible;

    
    public MShape(Shape shape, Color color) {
        this.shape = shape;
        this.color = color;
        this.visible = true;
    }
    public MShape(Shape shape, Color color,boolean visible) {
        this.shape = shape;
        this.color = color;
        this.visible = visible;
    }
    
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
   
    public void fill(Graphics2D g2d) {
        if(visible){
            g2d.setColor(color);
            g2d.fill(shape);
        }
    }
    
    public void draw(Graphics2D g2d) {
        if(visible){
            g2d.setColor(color);
            g2d.draw(shape);
        }
    }

    public Shape getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    public Rectangle2D getBounds2D(){
       return shape.getBounds2D();
    }
    

    @Override
    public String toString() {
        return "MShape{" + "shape=" + shape.getBounds() + ", color=" + color + ", visible=" + visible + '}';
    }

   
}
