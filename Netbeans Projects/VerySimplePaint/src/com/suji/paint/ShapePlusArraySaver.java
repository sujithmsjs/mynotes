package com.suji.paint;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;


public class ShapePlusArraySaver {

        
    private ArrayList<ShapePlus> sp;

    
    ShapePlusArraySaver(ArrayList<ShapePlus> sp) {
        this.sp = sp;
    }
    
    public Point findMin(){
        
        Point min =new Point(0, 0);
        if(!sp.isEmpty()){
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
    
    
    
    public Point findMax(){
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
    

    
    private void digest(){
        Point min = findMin();
        Point max = findMax();
        System.out.println("Min :"+findMin());
        System.out.println("Max :"+findMax());
        
        int width = max.x - min.x;
        System.out.println("width = " + width);
        int height = max.y - min.y;
        System.out.println("height = " + height);
        
        save(min.x,min.y,width,height);
    }

    public void draw(Graphics2D g){
        if (!sp.isEmpty()) {
            System.out.println(sp.size()+" shapes were drawn on screen.");
            
            ListIterator list = sp.listIterator(sp.size());
            
            while (list.hasPrevious()) {
                
                ShapePlus s = (ShapePlus) list.previous();
                
                s.drawForSave(g);
            }
        }
    }
    
    
    private void save(int x, int y ,int width,int height){
        try{

            BufferedImage bi = new BufferedImage(2000,2000, BufferedImage.TYPE_INT_ARGB);
            
            Graphics2D g = bi.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    
            draw(g);
            
            BufferedImage sub = bi.getSubimage(x, y, width, height);
            
            File file = saveFile();
            
         //   String filePath = "C:\\Users\\sujit\\OneDrive\\Desktop\\Java created\\myPaint.png";
            
            if (ImageIO.write(sub, "PNG",file)) {
                System.out.println("Image saved.");
            }else{
                System.out.println("Image not saved.");
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }

    void doSave() {
       digest();
    }
    
    private File saveFile() {
        File file = null;

        //Createing JFileChooser
        JFileChooser fc = new JFileChooser();

        // Setting up Current Directory.
        fc.setCurrentDirectory(new File("C:\\Users\\sujit\\OneDrive\\Desktop\\Java created"));

        //Handle open button action.
        int returnVal = fc.showSaveDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            //This is where a real application would open the file.
        }

        return file;
    }
}
