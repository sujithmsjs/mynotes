package com.suji.pro1.note;

import javax.swing.JOptionPane;


public class InstantDialogs {

    public static void displayError(Exception e){
        JOptionPane.showMessageDialog(null, e, "Error!!!", JOptionPane.ERROR_MESSAGE);
    } 
    

}
