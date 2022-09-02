package com.suji.paint;


import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.List;


public abstract class MyAbstractPath extends RectangularShape implements Shape {

    protected java.util.List<Point2D> dots = new ArrayList<>();
    protected Path2D path = new Path2D.Double();
    protected Color color;

    public List<Point2D> getDots() {
        return dots;
    }

    public Path2D getPath() {
        return path;
    }

    public Color getColor() {
        return color;
    }
    
    
    
    
    public static void main(String[] args) {
        
    }

    @Override
    public Rectangle getBounds() {
       return  path.getBounds();
    }

    @Override
    public Rectangle2D getBounds2D() {
        return path.getBounds2D();
    }

    @Override
    public boolean contains(double x, double y) {
       return path.contains(x, y);
    }

    @Override
    public boolean contains(Point2D p) {
       return path.contains(p);
    }

    @Override
    public boolean intersects(double x, double y, double w, double h) {
        return path.intersects(x, y, w, h);
    }

    @Override
    public boolean intersects(Rectangle2D r) {
        return path.intersects(r);
    }

    @Override
    public boolean contains(double x, double y, double w, double h) {
        return path.contains(x, y, w, h);
    }

    @Override
    public boolean contains(Rectangle2D r) {
       return path.contains(r);
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at) {
       return path.getPathIterator(at);
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at, double flatness) {
        return path.getPathIterator(at, flatness);
    }

    @Override
    public double getX() {
        return path.getBounds2D().getX();
    }

    @Override
    public double getY() {
       return path.getBounds2D().getY();
    }

    @Override
    public double getWidth() {
       return path.getBounds2D().getWidth();
    }

    @Override
    public double getHeight() {
       return path.getBounds2D().getHeight();
    }

    @Override
    public boolean isEmpty() {
      return dots.isEmpty();
    }

    @Override
    public void setFrame(double x, double y, double w, double h) {
       path = (Path2D) Util.getShape(path, x, y, w, h, 0);
    }

}
