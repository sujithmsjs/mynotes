package com.suji.pro1.note;

import java.awt.FontMetrics;
import javax.swing.JTextArea;


public class TextAreaUtil {

    private JTextArea area;

    public TextAreaUtil(JTextArea tarea) {
        this.area = tarea;
    }
    
    public static String makeTitle(String text,String replaceWith){
        return makeTitle(text, ">", replaceWith);
    }
    
    public static String makeTitle(String text,String replaceIt, String replaceWith) {
        StringBuilder sb = new StringBuilder(text);

        int n = 0;

        while ((n = sb.indexOf(">", 0)) != -1) {

            //sb.insert(n, rp);
            sb.replace(n, n + 1, replaceWith.toUpperCase().trim()+":");
            System.out.println(n);

            n++;
        }

        for (int i = 0; i < text.length(); i++) {
            System.out.println("Index " + i + " :" + text.charAt(i));
        }

        System.out.println(sb);
        return sb.toString();
    }
    
    
    public void makeTitle(){
        
        try{

            String selText = area.getSelectedText();
            String totText = area.getText();

            int n = area.getSelectionStart();
            int s = totText.lastIndexOf(">", n);
            System.out.println(s);
            area.insert(selText + ": ", s);
            area.setCaretPosition(s + n + 1);

            System.out.println("s: " + s);

        }catch(Exception e){
            InstantDialogs.displayError(e);
            System.out.println(e);
        }
        
    }
    
    public String underLine(char ch){
        
        FontMetrics fm = area.getFontMetrics(area.getFont());
        
        int width = fm.stringWidth(area.getSelectedText());
        
        StringBuffer sb = new StringBuffer();
        
        while (fm.stringWidth(sb.toString()) < width) {
            sb.append(ch);
        }
        return sb.toString();
    }
    
    

}
