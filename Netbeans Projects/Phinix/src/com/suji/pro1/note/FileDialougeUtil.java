package com.suji.pro1.note;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


public class FileDialougeUtil {

    private static final String PATH = "C:\\Users\\sujit\\OneDrive\\Desktop\\Check epub.txt";
    
    public static void main(String[] args) throws IOException {
        File file = saveFile();
        System.out.println(file);
    }
    
    public static File openFile(JFrame frame) throws IOException {
        
        JFileChooser fc = new JFileChooser();
        File currentFile = new File(PATH);
        
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt", "java");
        fc.setFileFilter(filter);
        fc.addChoosableFileFilter(filter);
        fc.setAcceptAllFileFilterUsed(false);
        fc.setCurrentDirectory(currentFile.getParentFile());
        fc.setSelectedFile(currentFile);
        
        int state = fc.showOpenDialog(frame);
        
        File chosenFile = null;
        
        if(state == JFileChooser.APPROVE_OPTION){
            chosenFile = fc.getSelectedFile();
//            System.out.println(chosenFile);
//            String fileText = TextFileUtil.readFileText(chosenFile);
//            System.out.println(fileText);
        }
        return chosenFile;
    }
    
    public static File saveFile(){
        return saveFile(null, PATH);
    }
    
    public static File saveFile(JFrame frame){
        return saveFile(frame, PATH);
    }
    
    public static File saveFile(JFrame frame, String path){
        
        JFileChooser fc = new JFileChooser();
        File currentFile = new File(PATH);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt", "java");
        fc.setFileFilter(filter);
        fc.addChoosableFileFilter(filter);
        fc.setAcceptAllFileFilterUsed(false);
        fc.setCurrentDirectory(currentFile.getParentFile());
        fc.setSelectedFile(currentFile);
        

        int state = fc.showSaveDialog(null);
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
