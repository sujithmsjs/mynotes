package com.suji.paint;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;


public class TextSaverUtil {
 
    public static boolean saveText(String text, String fileName){
        boolean isSaved = false;
        
        JFileChooser fc = new JFileChooser("C:\\Users\\sujit\\OneDrive\\Desktop\\Java created");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setDialogTitle("Select a directory");
        
        
        int option = fc.showSaveDialog(null);

        if (option == JFileChooser.APPROVE_OPTION) {
            String folder = fc.getSelectedFile().getPath();
            
            folder = folder.concat("\\").concat(fileName);
            
            File file = new File(folder);
            System.out.println(file.getPath());
            
            isSaved = saveText(file, text) & file.exists();
        } else {
            System.out.println("Nothing is selected.");
        }
        
        return isSaved;
    }
    
    
    public static boolean saveText(File file, String text){
        boolean isSaved = false;
        FileWriter fw = null;
        try {
            System.out.println();
            fw = new FileWriter(file, false);
            PrintWriter pw = new PrintWriter(fw, false);
            pw.print(text);
            
            pw.flush();
            pw.close();
            fw.close();
            
            isSaved = true;
        } catch (IOException ex) {
            System.out.println(ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                
            }
        }
        return isSaved;
    }

}
