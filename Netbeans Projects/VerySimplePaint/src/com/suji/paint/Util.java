package com.suji.paint;



import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.JPanel;



public class Util {
    
    public static void setAngle(Shape shape, double theta) {
        AffineTransform af = new AffineTransform();
        
        af.rotate(Math.toRadians(theta), shape.getBounds2D().getCenterX(), shape.getBounds2D().getCenterY());
        
        shape = af.createTransformedShape(shape);
    }


    public static Rectangle2D getPointRect(Point point,int unitSize) {
        int xLeftGap = point.x % unitSize; //Before Gap
        int xRightGap = unitSize - xLeftGap;
        int x = (xLeftGap < xRightGap) ? point.x - xLeftGap : point.x + xRightGap;

        int yTopGap = point.y % unitSize; //Before Gap
        int yBottomGap = unitSize - yTopGap;
        int y = (yTopGap < yBottomGap) ? point.y - yTopGap : point.y + yBottomGap;

        return new Rectangle2D.Double(x - 10, y - 10, 20, 20);
    }

    public static Point getExactPoint(Point p, int unitSize) {
        int xLeftGap = p.x % unitSize; //Before Gap
        int xRightGap = unitSize - xLeftGap;
        int x = (xLeftGap < xRightGap) ? p.x - xLeftGap : p.x + xRightGap;

        int yTopGap = p.y % unitSize; //Before Gap
        int yBottomGap = unitSize - yTopGap;
        int y = (yTopGap < yBottomGap) ? p.y - yTopGap : p.y + yBottomGap;

        return new Point(x, y);
    }
    
    public static Shape getShape(){
        return new RoundRectangle2D.Double(0, 0, 100, 100, 30, 30);
    }

    static ShapePlus getShapePlus() {
       RoundRectangle2D rect = new RoundRectangle2D.Double(0, 0, 100, 100, 30, 30);
       return new ShapePlus(rect,"Rect");
    }
    
    
    public static Shape getRandomShape(int x,int y, int w,int h){
        
        Point p1 = getExactPoint(new Point(x,y),Canvas.UNIT_SIZE);
        Point p2 = getExactPoint(new Point(x+w,y+h),Canvas.UNIT_SIZE);
        Point p3 = new Point(p2.x-p1.x,p2.y-p1.y);
        
        Random random = new Random();
        int n = random.nextInt(4);
        Shape shape = null;
        
        switch(n){
            case ShapeCode.ARC:
                int startAngle = random.nextInt(30)+60;
                int endAngle = random.nextInt(360-startAngle)+ startAngle;
                shape = new Arc2D.Double(p1.x, p1.y, p3.x, p3.y, startAngle,endAngle, Arc2D.PIE);
                break;
                
            case ShapeCode.ELLIPSE:
                shape = new Ellipse2D.Double(p1.x, p1.y, p3.x, p3.y);  
                break;
   
            case ShapeCode.RECT:
                shape = new Rectangle2D.Double(p1.x, p1.y, p3.x, p3.y);
                break;
                
            case ShapeCode.ROUND_RECT:
                shape = new RoundRectangle2D.Double(p1.x, p1.y, p3.x, p3.y,p3.x/3,p3.y/3);
                break;
        }
        return shape;
    }
    
    public static ArrayList<ShapePlus> getShapes(int n){
        ArrayList<ShapePlus> list = new ArrayList<ShapePlus>();
        Random random = new Random();
        for(int i=0;i<n;i++){
            int w = random.nextInt(Canvas.SCREEN_SIZE.width/4);
            int h = random.nextInt(Canvas.SCREEN_SIZE.width/3);
            
            int x = random.nextInt(Canvas.SCREEN_SIZE.width-w);
            int y = random.nextInt(Canvas.SCREEN_SIZE.height -h);
            
          //  list.add(new ShapePlus(getRandomShape(x, y, w, h)));
        }
        return list;
    }
    
    
    public static Shape getShape(final Shape shape, final double x, final double y, final double w, final double h, final int angle) {
        Shape rotatedShape = null;
        //Rotate
        if (angle > 0) {
            AffineTransform rotate = new AffineTransform();
            rotate.rotate(Math.toRadians(angle), (shape.getBounds2D().getWidth() / 2) + shape.getBounds2D().getX(), (shape.getBounds2D().getHeight() / 2) + shape.getBounds2D().getY());
            rotatedShape = rotate.createTransformedShape(shape);
        }else{
            rotatedShape = shape;
        }
        //Scale
        AffineTransform scale = new AffineTransform();
        double wP = 1 / rotatedShape.getBounds2D().getWidth();
        double hP = 1 / rotatedShape.getBounds2D().getHeight();
        scale.scale(w * wP, h * hP);
        Shape scaledShape = scale.createTransformedShape(rotatedShape);
        
        //Transform
        AffineTransform transform = new AffineTransform();
        transform.setToTranslation(x - scaledShape.getBounds2D().getX(), y - scaledShape.getBounds2D().getY());
        return transform.createTransformedShape(scaledShape);
    }
    
    public static String getShapeCode(Shape shape,String name) {

        StringBuilder result = new StringBuilder();
        boolean first = true;
        float[] coords = new float[2];

        PathIterator pi = shape.getPathIterator(null, 1.0);

        result.append("Path2D "+name+" = new Path2D.Double();\n");

        while (!pi.isDone()) {
            pi.currentSegment(coords);
            if (first) {
                first = false;
                double x = coords[0];
                double y = coords[1];

                result.append("\t"+name+".moveTo(" + x + ", " + y + ");\n");

            } else {
                double x = coords[0];
                double y = coords[1];

                result.append("\t"+name+".lineTo(" + x + ", " + y + ");\n");
            }

            pi.next();
        }
        result.append(name+".closePath();\n");
        return result.toString();
    }
    
    public static void drawGridLines(Graphics2D g, Dimension size, int unit) {
        g.setColor(Color.darkGray);

        for (int i = 0; i < size.width / unit; i++) {
            g.drawLine(i * unit, 0, i * unit, size.height);
        }

        for (int i = 0; i < size.height / unit; i++) {
            g.drawLine(0, i * unit, size.width, i * unit);
        }
    }
    
}
