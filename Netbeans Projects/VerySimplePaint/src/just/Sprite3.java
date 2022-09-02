package just;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.PathIterator;

import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.List;


public class Sprite3 /*extends RectangularShape*/{

    private List<MShape> shapes;
    private ShapesLoader loader;
    private Area area;
    private Rectangle rect;
    private int size;
    
    public void desc(){
        for (MShape shape : shapes) {
            System.out.println(shape);
        }
    }
    
    public Sprite3() {
        shapes = new ArrayList<>();   
    }    
    
    public MShape getAreaMShape(){
        return shapes.get(size - 1);
    }

    public void loadShapes(ShapesLoader loader) {
        this.loader = loader;
        
        //Creating whole area.
        area = new Area();
        //Creating the backgroud shape.
        rect = ShapeUtil.getRect(shapes);
        //Adding Rectagnle to the frame.
        shapes.add(new MShape(rect, Color.WHITE, false));
        
        loader.initShapes(shapes); 
        
        for (int i = 1; i < shapes.size() ; i++) {
            area.add(new Area(shapes.get(i).getShape()));
        }
        
        shapes.add(new MShape(area,Color.WHITE,false));
        size = shapes.size();
    }

    public MShape getBG(){
        return shapes.get(0);
    }
    
    public MShape get(int index){
        return shapes.get(index);
    }
    
    public int size(){
        return size;
    }
    
    public Shape getArea(){
        return shapes.get(size-1).getShape();
    }
       
    public Sprite3 duplicate(){
        Sprite3 s = new Sprite3();
        s.loadShapes(loader);
        return s;
    }
    
    
    public void draw(Graphics2D g2d) {
        for (MShape shape : shapes) {
            shape.fill(g2d);
        }
    }
    
    public void moveTo(int px, int py) {

        Rectangle r = shapes.get(0).getShape().getBounds();

        for (MShape shape : shapes) {

            AffineTransform at = new AffineTransform();
            int nx = px - r.x;
            int ny = py - r.y;
            at.translate(nx, ny);
            shape.setShape(at.createTransformedShape(shape.getShape()));

        }
    }
    public void setSize(int width, int height) {

        Rectangle r = shapes.get(0).getShape().getBounds();

        for (MShape shape : shapes) {

            AffineTransform at = new AffineTransform();
            int w = (int) ((1f / r.width) * width);
            int h = (int) ((1f / r.height) * height);

            at.scale(w, h);
            System.out.println(w);
            System.out.println(h);
            shape.setShape(at.createTransformedShape(shape.getShape()));
        }
    }
    public void setFrame(int x, int y, int width, int height) {
        Rectangle r = shapes.get(0).getShape().getBounds();
        if (r.width == width && r.height == height) {
            System.out.println("No change");
        } else {
            setSize(width, height);
        }
        moveTo(x, y);
    }
/*
    @Override
    public double getX() {
        return rect.getX();
    }

    @Override
    public double getY() {
        return rect.getY();
    }

    @Override
    public double getWidth() {
        return rect.getWidth();
    }

    @Override
    public double getHeight() {
        return rect.getHeight();
    }

    @Override
    public boolean isEmpty() {
       return shapes.isEmpty();
    }

    @Override
    public void setFrame(double x, double y, double w, double h) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rectangle2D getBounds2D() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains(double x, double y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean intersects(double x, double y, double w, double h) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains(double x, double y, double w, double h) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
*/
}
