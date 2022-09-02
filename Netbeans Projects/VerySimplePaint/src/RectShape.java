
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;


public class RectShape extends RectangularShape {
    private Shape shape;
    
    public RectShape(Shape shape) {
        this.shape = shape;
    }

    @Override
    public double getX() {
       return shape.getBounds2D().getX();
    }

    @Override
    public double getY() {
       return shape.getBounds2D().getY();
    }

    @Override
    public double getWidth() {
      return shape.getBounds2D().getWidth();
    }

    @Override
    public double getHeight() {
      return  shape.getBounds2D().getHeight();
    }

    @Override
    public boolean isEmpty() {
        return shape.getBounds2D().isEmpty();
    }

    @Override
    public void setFrame(double x, double y, double w, double h) {
       shape = (Shape) Util.getShape(shape, x, y, w, h, 0);
    }

    @Override
    public Rectangle2D getBounds2D() {
       return shape.getBounds2D();
    }

    @Override
    public boolean contains(double x, double y) {
       return shape.contains(x, y);
    }

    @Override
    public boolean intersects(double x, double y, double w, double h) {
      return  shape.intersects(x, y, w, h);
    }

    @Override
    public boolean contains(double x, double y, double w, double h) {
       return shape.getBounds2D().contains(x, y, w, h);
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at) {
      return  shape.getPathIterator(at);
    }

}
