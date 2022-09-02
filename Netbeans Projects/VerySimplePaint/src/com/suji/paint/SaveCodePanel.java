package com.suji.paint;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.swing.*;

public class SaveCodePanel extends JPanel {

    private ArrayList<ShapePlus> sp;
    private final Dimension SCREEN_SIZE = new Dimension(800, 600);

    public SaveCodePanel(ArrayList<ShapePlus> sp) {
        this.sp = sp;
        setPreferredSize(SCREEN_SIZE);
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        AffineTransform a = new AffineTransform();
        a.scale(0.1, 0.1);

        Ellipse2D a1 = new Ellipse2D.Double(0, 0, 100, 100);
        Ellipse2D a2 = new Ellipse2D.Double(0, 100, 200, 200);

        g2d.transform(a);

        g2d.fill(a1);
        g2d.fill(a2);

        Shape a1_2 = a.createTransformedShape(a1);
        Shape a2_2 = a.createTransformedShape(a2);

    }

    public Point findMin() {
        Point min = new Point(0, 0);
        if (!sp.isEmpty()) {
            min.x = sp.get(0).getShape().getBounds().x;
            min.y = sp.get(0).getShape().getBounds().y;
        }

        for (ShapePlus shape : sp) {
            Rectangle r = shape.getShape().getBounds();

            if (r.x < min.x) {
                min.x = r.x;
            }
            if (r.y < min.y) {
                min.y = r.y;
            }
        }
        return min;
    }

    public Point findMax() {
        Point max = new Point(0, 0);
        if (!sp.isEmpty()) {
            max.x = (int) sp.get(0).getShape().getBounds().getMaxX();
            max.y = (int) sp.get(0).getShape().getBounds().getMaxY();
        }

        for (ShapePlus shape : sp) {
            Rectangle r = shape.getShape().getBounds();

            if (r.getMaxX() > max.x) {
                max.x = (int) r.getMaxX();
            }
            if (r.getMaxY() > max.y) {
                max.y = (int) r.getMaxY();
            }
        }
        return max;
    }

    public void digest(String className) {
        
        StringBuilder autoCode = new StringBuilder();
        StringBuilder autoCode2 = new StringBuilder();
        
        
       
        
         
        autoCode.append("import java.awt.Color;\n");
        autoCode.append("import java.awt.Rectangle;\n");
        autoCode.append("import java.awt.geom.*;\n");
        autoCode.append("import java.util.List;\n");
        autoCode.append("\n");
        autoCode.append("\n\n\npublic class ").append(className).append(" extends ").append(className).append("_ShapeLoader{\n\n");
        autoCode.append("\t// Write your code here.\n");
        autoCode.append("\n}\n\n\n\n\n");
        autoCode.append("class ").append(className).append("_ShapeLoader extends Sprite implements ShapesLoader{\n\n");
        autoCode.append("\tpublic ").append(className).append("_ShapeLoader() {\n");
        autoCode.append("\t\tloadShapes(this);\n");
        autoCode.append("\t}\n\n");
        autoCode.append("\t@Override\n");
        autoCode.append("\tpublic void initShapes(List<MShape> shapes) {\n");
        
        Point max = findMax();
        Point min = findMin();
        double width = max.x - min.x;
        double height = max.y - min.y;
        
      //  autoCode.append("\tPoint min = new Point(").append(min.x).append(",").append(min.y).append(");\n");
     //   autoCode.append("\tint width = ").append(width).append(";");
     //   autoCode.append("\tint height = ").append(height).append(";");
        
      //  autoCode.append("\t\tRectangle re7 = new Rectangle(").append(min.x).append(",").append(min.y).append(",").append(width).append(",").append(height).append(");\n");
      //  autoCode.append("\t\tshapes.add(new MShape(").append("re7").append(",").append(getPaintCode(Color.darkGray)).append("));\n\n");
        
   
       // AffineTransform af = new AffineTransform();
     
        ListIterator list = sp.listIterator(sp.size());
        while (list.hasPrevious()) {
            ShapePlus shapePlus = (ShapePlus) list.previous();
       // for (ShapePlus shapePlus : sp) {
            String name = shapePlus.getName();
            
            
            if (shapePlus.getShape() instanceof Arc2D) {
                Arc2D arc = (Arc2D) shapePlus.getShape();
                double x = arc.getX();
                double y = arc.getY();
                double w = arc.getWidth();
                double h = arc.getHeight();
                double start = arc.getAngleStart();
                double extent = arc.getAngleExtent();
                int type = arc.getArcType();

                //Code writting.
                StringBuilder code = new StringBuilder();
                
                autoCode.append("\t\tArc2D ").append(name).append(" = new Arc2D.Double(").append(x).append(", ").append(y).append(", ").append(w).append(", ").append(h).append(", ").append(start).append(", ").append(extent).append(", ").append(type).append(");\n");
                autoCode.append("\t\tshapes.add(new MShape(").append(name).append(",").append(getPaintCode(shapePlus.getPaint())).append("));\n\n");
                
                code.append("g2d.fill(").append(name).append(");\n\n");
               
            } else if (shapePlus.getShape() instanceof Ellipse2D) {
                Ellipse2D ellipse = (Ellipse2D) shapePlus.getShape();
                double x = ellipse.getX();
                double y = ellipse.getY();
                double w = ellipse.getWidth();
                double h = ellipse.getHeight();

                //Code writting.
                StringBuilder code = new StringBuilder();
                autoCode.append("\t\tEllipse2D ").append(name).append(" = new Ellipse2D.Double(").append(x).append(", ").append(y).append(", ").append(w).append(", ").append(h).append(");\n");
                autoCode.append("\t\tshapes.add(new MShape(").append(name).append(",").append(getPaintCode(shapePlus.getPaint())).append("));\n\n");

              
                
                
                
                
            } else if (shapePlus.getShape() instanceof Rectangle2D) {
                Rectangle2D rect = (Rectangle2D) shapePlus.getShape();
                double x = rect.getX();
                double y = rect.getY();
                double w = rect.getWidth();
                double h = rect.getHeight();

                //Code writting.
                StringBuilder code = new StringBuilder();
                autoCode.append("\t\tRectangle2D ").append(name).append(" = new Rectangle2D.Double(").append(x).append(", ").append(y).append(", ").append(w).append(", ").append(h).append(");\n");
                autoCode.append("\t\tshapes.add(new MShape(").append(name).append(",").append(getPaintCode(shapePlus.getPaint())).append("));\n\n");
                
       
                
                
                
                
                
            } else if (shapePlus.getShape() instanceof RoundRectangle2D) {
                RoundRectangle2D rrect = (RoundRectangle2D) shapePlus.getShape();
                
                double x = rrect.getX();
                double y = rrect.getY();
                
                double w = rrect.getWidth();
                double h = rrect.getHeight();
                double aw = rrect.getArcWidth();
                double ah = rrect.getArcHeight();

               
                
                //Code writting.
                StringBuilder code = new StringBuilder();
                autoCode.append("\t\tRoundRectangle2D ").append(name).append(" = new RoundRectangle2D.Double(").append(x).append(", ").append(y).append(", ").append(w).append(", ").append(h).append(",").append(aw).append(",").append(ah).append(");\n");
                autoCode.append("\t\tshapes.add(new MShape(").append(name).append(",").append(getPaintCode(shapePlus.getPaint())).append("));\n\n");
                
                code.append("g2d.fill(").append(name).append(");\n\n");

                
                
            } else if (shapePlus.getShape() instanceof Shape) {
                
                autoCode.append(shapeCode(shapePlus));
                autoCode.append("\t\tshapes.add(new MShape(").append(name).append(",").append(getPaintCode(shapePlus.getPaint())).append("));\n\n");
            } 
            
            
            
        }
        autoCode.append("\t}\n");
        autoCode.append("}");
        System.out.println("-------------A U T O  G E N E R A T E  C O D E----------------------");
        System.out.println(autoCode);
        System.out.println("-------------A U T O  G E N E R A T E  C O D E----------------------");
        System.out.println(autoCode2);
        
        
        boolean flag = TextSaverUtil.saveText(autoCode.toString(), className+".java" );
        
        if(flag){
        JOptionPane.showMessageDialog(null, "Code saved successfully");
        }else{
        JOptionPane.showMessageDialog(null, "Some gone wrong, Code not saved");
        }
    }
    
    public void saveDialog(){
        File file = new File("");
        JFileChooser fc = new JFileChooser();
        int opt = fc.showSaveDialog(null);
        
        
    }

    public String shapeCode(ShapePlus shapePlus) {
        String name = shapePlus.getName();
        StringBuilder result = new StringBuilder();
       

        Shape shape = shapePlus.getShape();

        result.append(getPolyCoords(shape, name));
 

        return result.toString();
    }


    private StringBuilder getPolyCoords(Shape shape, String name) {

        StringBuilder result = new StringBuilder();
        boolean first = true;
        float[] coords = new float[2];

        PathIterator pi = shape.getPathIterator(null, 1.0);

        result.append("\t\tPath2D " + name + " = new Path2D.Double();\n");

        while (!pi.isDone()) {
            pi.currentSegment(coords);
            if (first) {
                first = false;
                double x = coords[0];
                double y = coords[1];

                result.append("\t\t\t"+name + ".moveTo(" + x + ", " + y + ");\n");

            } else {
                double x = coords[0];
                double y = coords[1];

                result.append("\t\t\t"+name + ".lineTo(" + x + ", " + y + ");\n");
            }

            pi.next();
        }
        result.append("\t\t\t"+name + ".closePath();\n");

        return result;
    }

    public void save() {
        String input = JOptionPane.showInputDialog("Enter class name");
        if(input != null && input.length()>2){
            digest(input);
        }
        

    }

    private String getPaintCode(Paint paint) {
        StringBuilder color = new StringBuilder();
        
        
        if(paint !=null && paint instanceof Color){
            Color c = (Color) paint;
            int red =  c.getRed();
            int green =  c.getGreen();
            int blue =  c.getBlue();
            color.append("new Color(").append(red).append(",").append(green).append(",").append(blue).append(") ");
        }else{
            color.append("Color.WHITE");
        }
        return color.toString();
    }


}

class Code__{
    public String code = "";
}
