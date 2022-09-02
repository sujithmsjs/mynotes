package com.suji.paint;


import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;


public class DashBoard {
    private ArrayList<ShapePlus> sp;
    
    private Font font;
    private Font font2;
    
    public DashBoard(ArrayList<ShapePlus> sp) {
        this.sp = sp;
        font = new Font("Magneto",Font.PLAIN,30);
        font2 = new Font("Arial",Font.PLAIN,20);
    }
    
    public void draw(Graphics2D g){
        g.setFont(font);
        
        g.drawString("Shapes : "+sp.size(),20, 100);
        
    
    }

    public void drawDesc(ShapePlus shape,Graphics2D g){
        g.setFont(font2);
        int n = 25;
 
        int newLine=20;
        for (int i = 0; i < sp.size(); i++) { 
            g.drawString(sp.get(i).toString() , 20, 140+(newLine*i));
        }
        
    }
    
    
    public static void main(String[] args) {
        
    }
}
