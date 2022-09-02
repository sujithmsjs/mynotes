package com.zetcode;

import java.awt.Graphics2D;
import java.awt.RenderingHints;



public class Util {
    
    public static final String imageLoc = "C:\\Users\\sujit\\OneDrive\\Documents\\NetBeansProjects\\CollectionsLearn\\src\\com\\zetcode\\wolvorine.jpeg";
    
    public static void setRenderingHints(Graphics2D g){
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
       
        g.setRenderingHints(rh);
    }

}
