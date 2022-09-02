
package com.suji.cram.util;

import com.suji.cram.gui.AddFrame;
import com.suji.cram.model.Card;
import java.awt.Component;
import java.awt.Frame;
import javax.swing.JOptionPane;


public class DialogUtil {

    
    public static void main(String[] args) {
        Card card = new Card("Sujith", "Man", "dfdf");
        System.out.println("card "+card.isValid());
    }
    
    public static String demo(String str){
        String output = "Work";
        
        if(str.length() < 4){
            System.out.println("Before Return null");
            return null;
        }
        if(str.equalsIgnoreCase("exit")){
            return "Exit From return";
        }
        System.out.println("End of the method");
        return output;
    }
    
    
    
    
    public static void showMsg(Frame parent, String msg){
        JOptionPane.showMessageDialog(parent,msg ,"Message", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void showEx(Frame parent, Exception ex){
        JOptionPane.showMessageDialog(parent,ex ,ex.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
    }

    public static void showMsg(Frame parent, String title, String msg) {
       JOptionPane.showMessageDialog(parent,msg ,title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void showEx(Frame parent, String title, String msg) {
        JOptionPane.showMessageDialog(parent, msg, title, JOptionPane.ERROR_MESSAGE);
    }
    
    
}
