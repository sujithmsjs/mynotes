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

public class Sprite {

    private List<MShape> shapes;
    private ShapesLoader loader;
    private boolean visible = true;
    private boolean loaded;
    private boolean isDimensinsSetted;
    private int size;

    public Sprite() {
        shapes = new ArrayList<>();
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void desc() {
        for (MShape shape : shapes) {
            System.out.println(shape);
        }
    }

    public void loadShapes(ShapesLoader loader) {
        if (!loaded) {
            this.loader = loader;

            loader.initShapes(shapes);

            Area area = new Area();
            for (int i = 0; i < shapes.size(); i++) {
                area.add(new Area(shapes.get(i).getShape()));
            }
            shapes.add(new MShape(area, Color.WHITE, false));

            size = shapes.size();

            loaded = true;
        }
    }
    
    public boolean isCollideWith(Sprite shape){
        Area a = new Area(getArea());
        //Area a2  = new Area(shape.getArea());
        return a.intersects(shape.getBounds());
    }

    public void setColorAt(int index, Color color) {
        shapes.get(index).setColor(color);
    }

    public MShape get(int index) {
        return shapes.get(index);
    }

    public int size() {
        return size;
    }

    public Sprite duplicate() {
        Sprite s = new Sprite();
        s.loadShapes(loader);
        return s;
    }

    public void draw(Graphics2D g2d) {
        if (visible) {
            for (MShape shape : shapes) {
                shape.fill(g2d);
            }
        }
    }

    public Shape getArea() {
        return shapes.get(size - 1).getShape();
    }

    public Rectangle getBounds() {
        return getArea().getBounds();
    }

    public Rectangle getDetails(int[] args) {
        Area a = new Area();

        for (int arg : args) {
            a.add(new Area(shapes.get(arg).getShape()));
        }
        return a.getBounds();
    }

    private void moveTo00(int px, int py) {
        Rectangle rect = getBounds();
        for (MShape shape : shapes) {
            AffineTransform at = new AffineTransform();
            int nx = px - rect.x;
            int ny = py - rect.y;
            at.translate(nx, ny);
            shape.setShape(at.createTransformedShape(shape.getShape()));
        }

        if (px != getX() || getY() != py) {
            StringBuilder sd = new StringBuilder();
            System.out.println(sd.toString());
            System.out.println("Error in moveTo()");
            System.exit(0);
        }
    }

    public void moveTo(int px, int py) {
        if (px != getX() || getY() != py) {
            moveTo00(px, py);
        }
    }

    public void check(final int x, final int y) {
        final int nX = getX() + x;
        final int nY = getY() + y;
        final int gY = getY();
        System.out.println();
        
        setX(nY);
        
        if (getX() == nX && getY() == gY) {
            System.out.println("nX = " + nX + "; nY = " + y + "; getX(): " + getX() + "; getY(): " + getY());
        }else{
            System.out.println("Error X : nX = " + nX + "; nY = " + y + "; getX(): " + getX() + "; getY(): " + getY());
            System.exit(0);
        }

        setY(nY);
        
        if (getX() == nX && getY() == nY) {
            System.out.println("nX = " + nX + "; nY = " + nY + "; getX(): " + getX() + "; getY(): " + getY());
        }else{
            System.out.println("Error Y:  nX = " + nX + "; nY = " + nY + "; getX(): " + getX() + "; getY(): " + getY());
            System.exit(0);
        }
    }

    public int getX() {
        return getBounds().x;
    }

    public int getY() {
        return getBounds().y;

    }

    public void setX(int x) {
        int last = getY();
        moveTo(x, last);
        if (last != getY()) {
            System.out.println("setX() Error!");
            System.exit(0);
        }
    }

    public void setY(int y) {
        int last = getX();
        moveTo(last, y);
        if (last != getX()) {
            System.out.println("setY() Error!");
            System.exit(0);
        }
    }
    
    public int getWidth(){
        return getBounds().width;
    }
    public int getHeight(){
        return getBounds().height;
    }

    public void setSize(int width, int height) {
        Rectangle2D rect = getArea().getBounds2D();
        for (MShape shape : shapes) {

            AffineTransform at = new AffineTransform();
            double w = (1 / rect.getWidth()) * width;
            double h = (1 / rect.getHeight()) * height;

            at.scale(w, h);
            shape.setShape(at.createTransformedShape(shape.getShape()));
        }
    }

    public void setFrame(int x, int y, int width, int height) {
        if (!isDimensinsSetted) {
            Rectangle r = shapes.get(0).getShape().getBounds();
            if (r.width == width && r.height == height) {
                System.err.println("No change");
            } else {
                setSize(width, height);
            }
            moveTo(x, y);
            System.out.println("setFrameCalled");
            isDimensinsSetted = true;
        } else {
            System.out.println("Frame can't be called secound time.");
        }

        if (x != getBounds().x || y != getBounds().y /* || width != getBounds().width || height != getBounds().height */) {
            System.out.println("Requested: x:" + x + "    y:" + y + "   width:" + width + "    height:" + height + "");
            Rectangle b = getBounds();

            System.out.println("Requested: x:" + b.x + "    y:" + b.y + "   width:" + b.width + "    height:" + b.height + "");
            System.out.println("Error is setFrame method.");
            System.exit(0);
        }

        if (width != getBounds().width || height != getBounds().height) {
            System.out.println("Requested: x:" + x + "    y:" + y + "   width:" + width + "    height:" + height + "");
            Rectangle b = getBounds();
            System.out.println("Requested: x:" + b.x + "    y:" + b.y + "   width:" + b.width + "    height:" + b.height + "");
            System.out.println("Error is setFrame method.");
        }

    }
}
