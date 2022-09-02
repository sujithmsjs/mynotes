
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import javax.swing.JColorChooser;
import javax.swing.JPanel;


public class ShapePlus {
    
    private Shape shape;
    private JPanel parent;
    private Paint paint = Color.WHITE;
    private float opacity;
    private double angle;

    public double getAngle() {
        return angle;
    }
    
    public void setAngle(double angle) {
        
        AffineTransform af = new AffineTransform();
        af.rotate(Math.toRadians(angle), shape.getBounds2D().getCenterX(), shape.getBounds2D().getCenterY());
        shape = new RectShape(af.createTransformedShape(shape));
        
    }
    
    
    
    public float getOpacity() {
        return opacity;
    }

    public void setOpacity(float opacity) {
        
        this.opacity = opacity;
        
    }
    
    private boolean isMouseEntered;
    private boolean isSelected;
    private boolean isHolded;

    public ShapePlus(Shape shape) {
        this.shape = shape;
        this.parent = parent;
    }
    
    public ShapePlus(Shape shape,Paint color) {
        this.shape = shape;
        this.paint = color;
        this.parent = parent;
    }

    void drawMe(Graphics2D g2d) {
        if(opacity > 0){
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,opacity));
        }
        
        g2d.setPaint(paint);
        g2d.fill(shape);
        
        
        if (isMouseEntered) {
            g2d.setColor(Color.yellow);
            g2d.draw(shape.getBounds2D());
        }
    }

    boolean mouseMoved(MouseEvent e) {
        isMouseEntered = shape.contains(e.getX(), e.getY());
        return isMouseEntered;
    }

    boolean contains(double x, double y) {
        return shape.contains(x, y);
    }

    void setSelected(boolean b) {
        isSelected = b;
    }

    void setHold(boolean b) {
        isHolded = b;
    }

    boolean isHold() {
       return isHolded;
    }
    
    public Shape getShape(){
        return shape;
    }
    
    public void move(){

        
    }
    public void move(int x, int y){
        
    }

    public boolean isRectangle() {
        System.out.println("Rect Test");
        boolean isRectangle = false;
        try{
            RectangularShape r = (RectangularShape) shape;
            isRectangle = true;
        }catch(ClassCastException e){
            System.out.println(e.getMessage());
        }
        return isRectangle;
    }

    void changeColor() {
       paint =  JColorChooser.showDialog(null, "Select a color", Color.WHITE);
       
    }

      public  String getShapeName() {
       return shape.getClass().getSuperclass().getSimpleName();
    }

    
    
    

}
