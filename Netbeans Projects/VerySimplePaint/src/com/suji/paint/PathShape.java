package com.suji.paint;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

public class PathShape extends MyAbstractPath {

    
    
    private boolean isPathClosed;
    private String name;
    private Point mouseMovedTo;
    private Shape shape;
    private int pathType;

    public void clear(){
        dots.clear();
        path = new Path2D.Double();
        isPathClosed = false;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
    
    private void refreshPath() {
        path = new Path2D.Double(); 
        for (int i = 0; i < dots.size(); i++) {
            Point2D p = dots.get(i);
            if (i == 0) {
                path.moveTo(p.getX(), p.getY());
            } else {
                path.lineTo(p.getX(), p.getY());
            }
        }
        if (isPathClosed) {
            path.closePath();
            System.out.println("Path is closed. "+isPathClosed);
        }
    }
    
    public boolean isPathClosed(){
        
        return isPathClosed;
    }
    
    public void closePath(){
        isPathClosed = true;
        path.closePath();
    }

    public void setClosePath(boolean flag) {
        isPathClosed = flag;
        
    }

    public void draw(Graphics2D g) {
        if(isPathClosed){
            g.setPaint(color);
            g.fill(shape);
        }else{
            g.setStroke(new BasicStroke(3,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
            g.draw(path);
            if (!isPathClosed() && mouseMovedTo != null && !isEmpty()) {
                Point2D lastP = getLastDot();
                g.draw(new Line2D.Double(lastP, mouseMovedTo));
            }
        }
        
    }

    public void fill(Graphics2D g) {
        g.fill(path);
    }

    public int getSize() {
        return dots.size();
    }

    public void add(Point2D point) {
        if (!isPathClosed) {
            if (dots.contains(point)) {
                isPathClosed = true;
            } else {
                dots.add(Util.getExactPoint(new Point((int)point.getX(),(int) point.getY()),Canvas.UNIT_SIZE));
            }
            refreshPath();
        }
    }
    
    public void createShape() {
        System.out.println("##########################");
        System.out.println("Creating shape with "+dots.size()+" Dots");
        
        shape = path;
        String code = Util.getShapeCode(shape, "newPathShape");
        System.out.println(code);
    }

    public void add(Point point) {
        Point2D p = new Point2D.Double(point.x, point.y);
        add(p);
    }
    public void add(double x, double y) {
        Point2D p = new Point2D.Double(x, y);
        add(p);
    }

    public Point2D getLastDot() {

        if (dots.isEmpty()) {
            return null;
        } else {
            return dots.get(getSize() - 1);
        }

    }
    
    public  String getNewName(){
        String name = null;
        String input = JOptionPane.showInputDialog("Enter shape name");
        if(input != null && !Board.nameSet.contains(input) ){
            name = input;
        }else{
            input = null;
        }
        return name;
    }
    
    public void keyPressed(KeyEvent e, String name){
        System.out.println("Key typed.");
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                
                if(name!=null && dots.size()>2){
                    setClosePath(true);
                    createShape();
                    
                    this.name = name;
                    color = JColorChooser.showDialog(null, "Select shape Color", Color.WHITE); 
                    System.out.println("Setting path close");
                    
                }else{
                    clear();
                    System.out.println("Unable to create");
                }
                
                
                
                break;
            case KeyEvent.VK_ESCAPE:
                if(!isPathClosed){
                    clear();
                    System.out.println("Path is cleared.");
                }
                break;
        }
    }

    public boolean isEmpty() {
        return dots.isEmpty();
    }
    int countClicks = 0;
    public void mousePressed(MouseEvent e){
//        if (dots.size()==1) {
//          
//            add(e.getPoint());
//
//            System.out.println("Painting started.");
//        } else {
//            add(e.getPoint());
//        }
//        System.out.println(getSize());
        System.out.println(countClicks++);
        add(e.getPoint());
    }
    
    public void mouseMoved(MouseEvent e){
        if (dots.size()>0) {
            mouseMovedTo = Util.getExactPoint(e.getPoint(),Canvas.UNIT_SIZE);
            
           // repaint();
        }
    }

    
}
