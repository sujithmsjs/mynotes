package com.suji.util;

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


public class Sprite3 extends RectangularShape{

    private List<MShape> shapes;
    private ShapesLoader loader;
    private boolean visible = true;
    private int size;
    
    
    public Sprite3() {
        shapes = new ArrayList<>();   
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    
    
    public void desc(){
        for (MShape shape : shapes) {
            System.out.println(shape);
        }
    }
    
    
    /**
     * 
     * @return Returns the area
     */
    public Shape getArea(){
        return shapes.get(size - 1).getShape();
    }

    public void loadShapes(ShapesLoader loader) {
        this.loader = loader;
        
        //Loading Shapes
        loader.initShapes(shapes); 
        
       
        
        //Creating Area.
        Area area = new Area();
        for (int i = 0; i < shapes.size() ; i++) {
            area.add(new Area(shapes.get(i).getShape()));
        }
        shapes.add(new MShape(area,Color.WHITE,false));
        
        //Init Size
        size = shapes.size();
        System.out.println(shapes.size());
    }
    
   
    
    public void changeColorAt(int index, Color color){
        shapes.get(index).setColor(color);
    }
    
    public MShape get(int index){
        return shapes.get(index);
    }
    
    public int size(){
        return size;
    }
       
    public Sprite3 duplicate(){
        Sprite3 s = new Sprite3();
        s.loadShapes(loader);
        return s;
    }
    
    
    public void draw(Graphics2D g2d) {
        if(visible){
            for (MShape shape : shapes) {
                shape.fill(g2d);
            }
        }
      //  System.out.println(getBounds());
    }
    
    public void drawFrame(Graphics2D g2d){
        g2d.draw(getBounds2D());
    }
    
    
    
    public void moveTo(double px, double py) {

        Rectangle2D rect = getBounds();
        for (MShape shape : shapes) {

            AffineTransform at = new AffineTransform();
            double nx = px - rect.getX();
            
            double ny = py - rect.getY();
           
            at.translate(nx, ny);
            
            shape.setShape(at.createTransformedShape(shape.getShape()));
            //System.out.println("moveTo is called.");
        }
    }
    
    public void setX(double x){
        moveTo(x, getY());
    }
    
    public void setY(double y){
        moveTo(getX(),y);
    }
    
    public void setSize(double width, double height) {
        Rectangle2D rect = getArea().getBounds2D();
        for (MShape shape : shapes) {
            
            AffineTransform at = new AffineTransform();
            double w =  (1 / rect.getWidth()  ) * width;
            double h =  (1 / rect.getHeight() ) * height;

            
            
            at.scale(w, h);
          //  System.out.println(w);
          //  System.out.println(h);
            shape.setShape(at.createTransformedShape(shape.getShape()));
        }
    }
    
    @Override
    public double getX() {
        return getBounds2D().getX();
    }

    @Override
    public double getY() {
        return getBounds2D().getY();
    }

    @Override
    public double getWidth() {
       return getBounds2D().getWidth();
    }

    @Override
    public double getHeight() {
       return getBounds2D().getHeight();
    }

    @Override
    public boolean isEmpty() {
        return shapes.isEmpty();
    }

    @Override
    public void setFrame(double x, double y, double width, double height) {
        Rectangle r = shapes.get(0).getShape().getBounds();
        if (r.width == width && r.height == height) {
            
            System.err.println("No change");
            
        } else {
            setSize(width, height);
        }
        moveTo(x, y);
        System.out.println("setFrameCalled");
    }

    @Override
    public Rectangle2D getBounds2D() {
        return getArea().getBounds2D();
    }

    @Override
    public boolean contains(double x, double y) {
        return getArea().contains(x, y);
    }

    @Override
    public boolean intersects(double x, double y, double w, double h) {
        return getArea().intersects(x, y, w, h);
    }

    @Override
    public boolean contains(double x, double y, double w, double h) {
        return getArea().contains(x, y, w, h);
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at) {
        return getArea().getPathIterator(at);
    }
}
