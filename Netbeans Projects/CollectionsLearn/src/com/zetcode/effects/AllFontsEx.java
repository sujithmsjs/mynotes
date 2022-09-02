package com.zetcode.fonts;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

public class AllFontsEx {
    public static Font[] fonts;
    
    
    static {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        fonts = ge.getAllFonts();

        for (Font font : fonts) {
            System.out.print(font.getFontName() + " : \n");
            // System.out.println(font.getFamily());
        }
    }
    
    
    
    
    public static void main(String[] args) {

      
    }
}
