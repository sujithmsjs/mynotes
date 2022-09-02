package com.suji.cram.main;


import com.suji.cram.gui.IndexFrame;
import java.awt.Font;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;


public class Cram {

    public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {

        FontUIResource fur = new FontUIResource("", Font.BOLD, 18);
        setUIFont(fur);

           
       // UIManager.setLookAndFeel( new NoireLookAndFeel());
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IndexFrame(null).setVisible(true);
            }
        });
    }

    private static void setUIFont(javax.swing.plaf.FontUIResource f) {

        java.util.Enumeration keys = UIManager.getDefaults().keys();
        UIDefaults ud = UIManager.getDefaults();
        javax.swing.plaf.ColorUIResource dark = new ColorUIResource(0, 0, 150);
        
        
        javax.swing.plaf.ColorUIResource white = new ColorUIResource(255, 255, 255);
        javax.swing.plaf.ColorUIResource ambar = new ColorUIResource(255, 191, 0);
       // rgb(255, 191, 0)
       
       List<String> uiList = new ArrayList<>();
       
       
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            
            uiList.add(key.toString());
            
            if (value instanceof javax.swing.plaf.FontUIResource) {
               // System.out.println(key + " : " + UIManager.get(value));
                UIManager.put(key, f);

            }
            
            
            if (key.toString().matches(".*[.]background")) {
                // System.out.println(key + " : " + UIManager.get(value));
                UIManager.put(key, Color.BLACK);
                //uiList.add(key.toString());
            }
            
             if(key.toString().matches(".*[.]foreground")){
                // System.out.println(key + " : " + UIManager.get(value));
                UIManager.put(key, Color.WHITE);
                //uiList.add(key.toString());
             }
             
          
 
           //System.out.println(key + " : " + value);
        }
        
        
        Collections.sort(uiList);
        for (String string : uiList) {
            System.out.println(string);
        }
        //Theme Setting.
        UIManager.put("TextField.foreground", ambar);
        UIManager.put("TextField.inactiveBackground", Color.BLACK);
        UIManager.put("Label.foreground", Color.GREEN);
        UIManager.put("TextField.caretForeground", Color.white);
         UIManager.put("OptionPane.messageForeground", Color.white);
    }
}
