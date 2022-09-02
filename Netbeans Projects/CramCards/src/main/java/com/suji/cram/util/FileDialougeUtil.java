package com.suji.cram.util;

import java.io.File;
import java.util.Arrays;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


public class FileDialougeUtil {

    public static File openFile(JFrame frame, String path){
        
        JFileChooser fc = new JFileChooser();
        File currentFile = new File(path);
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files Files", "csv", "csv");
        fc.setFileFilter(filter);
        fc.setSize(800,400);
        fc.addChoosableFileFilter(filter);
        fc.setAcceptAllFileFilterUsed(false);
        fc.setCurrentDirectory(currentFile.getParentFile());
        
        int state = fc.showOpenDialog(frame);
        
        
        File chosenFile = null;
        
        if(state == JFileChooser.APPROVE_OPTION){
            chosenFile = fc.getSelectedFile();
        }
        return chosenFile;
    }
    
    public static File saveFile(){
        return saveFile(null, "");
    }
    
    public static File saveFile(JFrame frame){
        return saveFile(frame, "");
    }
    
    public static File saveFile(JFrame frame, String path){
        
        
        JFileChooser fc = new JFileChooser();
        File currentFile = new File(path);
        
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files Files", "csv", "csv");
        fc.setFileFilter(filter);
        fc.addChoosableFileFilter(filter);
        fc.setAcceptAllFileFilterUsed(false);
        fc.setCurrentDirectory(currentFile.getParentFile());
       // fc.setSelectedFile(currentFile);
        
        

        int state = fc.showSaveDialog(frame);
        System.out.println("State: "+state);
        
        File chosenFile = null;

        if (state == JFileChooser.APPROVE_OPTION) {
            chosenFile = fc.getSelectedFile();
             
            boolean isAlreadyExists = Arrays.asList(chosenFile.getParentFile().listFiles()).contains(chosenFile);
            System.out.println("is Already Exists? : "+isAlreadyExists);
            if(isAlreadyExists){
                int selectedOption = JOptionPane.showConfirmDialog(frame, "Do you want to REPLACE?", "Already Existed", JOptionPane.ERROR_MESSAGE);
                if(selectedOption == JOptionPane.NO_OPTION){
                    chosenFile = null;
                }
            }
        }
        return chosenFile;
    }
}
