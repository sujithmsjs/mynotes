package com.suji.pro1.note;

import java.awt.Color;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javax.swing.JTextArea;

public class Theme {

    private JTextArea area;

    public static final String DEFAULT = "Default";
    public static final String BG_COLOR = "bgColor";
    public static final String FG_COLOR = "fgColor";
    public static final String FONT_NAME = "fontName";
    public static final String FONT_SIZE = "fontSize";
    public static final String FONT_STYLE = "fontStyle";
    public static final String THEME_NAME = "theamName";

    public Theme(JTextArea area) {
        this.area = area;

    }

    public boolean saveTheme(String themeName) {
        
        Preferences pref = Preferences.userNodeForPackage(Theme.class).node(themeName);
        Font font = area.getFont();

        pref.put(THEME_NAME, themeName);
        pref.putInt(FG_COLOR, area.getForeground().getRGB());
        pref.putInt(BG_COLOR, area.getBackground().getRGB());

        pref.putFloat(FONT_SIZE, font.getSize());
        pref.put(FONT_NAME, font.getName());
        pref.putInt(FONT_STYLE, font.getStyle());
        
        System.out.println("Theme: "+themeName+" Successfully saved.");
        
        return true;
    }
    
    public void loadDefaultSettings(){
        setTheme(DEFAULT);
        System.out.println("Default Settings Loaded.");
    }
    
    public void updateDefaultSettings(){
        saveTheme(DEFAULT);
    }
    
    public void setTheme(String themeName){
        
        Preferences pref = Preferences.userNodeForPackage(Theme.class).node(themeName);
        Font font = area.getFont();
        
        Color fg = new Color(pref.getInt(FG_COLOR, area.getForeground().getRGB()));
        Color bg = new Color(pref.getInt(BG_COLOR, area.getBackground().getRGB()));
        
        float size = pref.getFloat(FONT_SIZE, font.getSize());
        String fontName = pref.get(FONT_NAME, font.getName());
        int style = pref.getInt(FONT_STYLE, font.getStyle());

        Font newFont = new Font(fontName, style, (int) size);
        
        area.setBackground(bg);
        area.setForeground(fg);
        area.setFont(newFont);
        
    }
    
    public String[] getAllThemes() {
        
        Preferences pref = Preferences.userNodeForPackage(Theme.class);
        //pref.removeNode();
        String[] themes = null;
        try {
            
            themes = pref.childrenNames();
            
        } catch (BackingStoreException ex) {
            System.out.println(ex);
        }
        
        return themes;
    }

    public static void main(String[] args) throws BackingStoreException {
        JTextArea ta = new JTextArea();
        Theme t = new Theme(ta);
        
        t.loadDefaultSettings();
        
      
        
        
        
        
        
    }

}
