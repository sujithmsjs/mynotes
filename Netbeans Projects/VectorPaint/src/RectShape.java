
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import javax.swing.JColorChooser;
import javax.swing.JPanel;

public class RectShape extends RectangularShape implements Shape {

    //
    public static final int MOVE_TOOL = 1;
    public static final int RESIZE_TOP = 2;
    public static final int RESIZE_DOWN = 3;
    private int tool;

    private JPanel parent;
    private Shape shape;

    private Rectangle2D xyRect;
    private Rectangle2D whRect;



    private Paint paint;
    private boolean isSelected;
    private Point2D isPressedAt;

    //Alpha Composite values.
    private AlphaComposite composite;
    private float opacity;
    private int compositingRule;
    private boolean isClicked;
    private boolean isHolded;
    private boolean isMouseEntered = false;


    /*
    private AffineTransform transform;
    private Point translate;
    private Dimension scale;
    private float rotate;
    private Point shear;
     */
    public Rectangle2D getxyRect() {
        return xyRect;
    }

    public Rectangle2D getwhRect() {
        return whRect;
    }

    public Rectangle2D getXYCorner() {
        return xyRect;
    }

    public Rectangle2D getWHCorner() {
        return whRect;
    }

    public RectShape(Shape shape, JPanel parent) {
        this.parent = parent;
        this.shape = shape;
        opacity = 1f;
        paint = Color.RED;

        composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity);
        setCorners();

    }

    private void setColor(Paint paint) {
        this.paint = paint;
    }

    private void setCorners() {

        Rectangle2D rect = getBounds2D();

        final int CORNER_SIZE = 10;

        xyRect = new Rectangle2D.Double(rect.getX() - CORNER_SIZE, rect.getY() - CORNER_SIZE, CORNER_SIZE * 2, CORNER_SIZE * 2);
        whRect = new Rectangle2D.Double(rect.getWidth() + rect.getX() - CORNER_SIZE, rect.getHeight() + rect.getY() - CORNER_SIZE, CORNER_SIZE * 2, CORNER_SIZE * 2);
    }

    private void drawFrame(Graphics2D g) {
        if (isSelected) {
            Rectangle2D rect = getBounds2D();

            setCorners();
            g.setColor(Color.WHITE);

            g.fill(xyRect);

            g.fill(whRect);

            g.draw(rect);
        }
    }

    private void drawSimpleFrame(Graphics2D g) {
        g.setColor(Color.RED);
        Rectangle2D rect = getBounds2D();
        g.draw(rect);
    }

    public void draw(Graphics2D g) {

        //Reset values
        AlphaComposite resetCompsite = (AlphaComposite) g.getComposite();
        Paint resetPaint = g.getPaint();

        g.setComposite(composite);

        g.setComposite(composite);
        g.setPaint(paint);
        g.fill(shape);

        if (isSelected) {
            drawFrame(g);
        }
        if (!isSelected && isMouseEntered) {
            drawSimpleFrame(g);
        }

        //Reseting
        g.setComposite(resetCompsite);
        g.setPaint(resetPaint);
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
        setCorners();
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public void setOpacity(float alpha) {
        composite = composite.derive(alpha);
    }

//<editor-fold defaultstate="collapsed" desc="Overide Methods">
    @Override
    public Rectangle getBounds() {
        return shape.getBounds();
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
    public boolean contains(Point2D p) {
        return shape.contains(p);
    }

    @Override
    public boolean intersects(double x, double y, double w, double h) {
        return shape.intersects(x, y, w, h);
    }

    @Override
    public boolean intersects(Rectangle2D r) {
        return shape.intersects(r);
    }

    @Override
    public boolean contains(double x, double y, double w, double h) {
        return shape.contains(x, y, w, h);
    }

    @Override
    public boolean contains(Rectangle2D r) {
        return shape.contains(r);
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at) {
        return shape.getPathIterator(at);
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at, double flatness) {
        return shape.getPathIterator(at, flatness);
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
        return shape.getBounds2D().getHeight();
    }

    @Override
    public boolean isEmpty() {
        return shape.getBounds2D().isEmpty();
    }

    @Override
    public void setFrame(double x, double y, double w, double h) {
        shape.getBounds2D().setFrame(x, y, w, h);
    }
//</editor-fold>

    void mouseDoubleClicked(MouseEvent e) {
        if (contains(e.getX(), e.getY())) {
            Color rsColor = JColorChooser.showDialog(null, "ChooseColor", Color.BLACK);
            setPaint(rsColor);
        }
    }

    boolean isResizing() {
        if (xyRect.contains(isPressedAt)) {
            isXYResizing = true;
            isWHResizing = false;
            return true;
        } else if (whRect.contains(isPressedAt)) {
            isXYResizing = false;
            isWHResizing = true;
            return true;
        } else {
            return false;
        }
    }

    void setTool(int x, int y) {
        isPressedAt = new Point2D.Double(x, y);

        if (xyRect.contains(x, y)) {
            tool = RESIZE_TOP;

        } else if (whRect.contains(x, y)) {
            tool = RESIZE_DOWN;

        }
        if (shape.contains(isPressedAt)) {
            tool = MOVE_TOOL;
            isSelected = true;
            isHolded = true;

        }
    }

    boolean isClicked() {
        return isSelected;
    }

    void setHolded(boolean isHolded) {
        this.isHolded = isHolded;
    }

    boolean isHolded() {
        return isHolded;
    }

    boolean topCornerContains(double x, double y) {
        return xyRect.contains(x, y);
    }

    boolean downCornerContains(double x, double y) {
        return whRect.contains(x, y);
    }

    void changeCursor(int x, int y) {

        if (xyRect.contains(x, y)) {

            System.out.println("Working" + xyRect);

            parent.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));

        } else if (whRect.contains(x, y)) {

            parent.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));

        } else if (shape.contains(x, y)) {
            
            parent.setCursor(new Cursor(Cursor.MOVE_CURSOR));
        } else {
            parent.setCursor(Cursor.getDefaultCursor());
        }
    }

    void mouseDragged(MouseEvent e) {

        int x = e.getX();
        int y = e.getY();
        
        if (isHolded() && isSelected()) {

            switch (getTool()) {
                case MOVE_TOOL:
                    setFrame(x - getWidth() / 2, x - getHeight() / 2, getWidth(), getHeight());
                    break;
                case RESIZE_TOP:
                    System.out.println("Top resizing...");
                    int nW = (int) (getX() - x);
                    int nH = (int) (getY() - y);
                    
                    
                    setFrame(e.getX(), e.getY(), getWidth()+nW, getHeight()+nH);
                    break;
                case RESIZE_DOWN:
                    break;
            }

        }

    }

    void setMouseEntered(boolean isMouseEntered) {
        this.isMouseEntered = isMouseEntered;
    }

    //<editor-fold defaultstate="collapsed" desc="Mouse Pressed">
    void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if (contains(x, y)) {
            if (topCornerContains(x, y)) {  //Resize Top
                parent.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
                setHolded(true);

            } else if (downCornerContains(x, y)) { //Resize Botton

                parent.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
                setHolded(true);

            } else { // Move Tool
                parent.setCursor(new Cursor(Cursor.MOVE_CURSOR));
                setHolded(true);
            }
            setSelected(true);

            setSelected(true);
        } else {
            setSelected(false);
        }
    }
//</editor-fold>

    private int getTool() {
        return tool;
    }

}
