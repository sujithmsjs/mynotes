package learn.swings.file;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class SaveFileDemo {

    public static void main(String[] args) {
       
        JFileChooser fc = new JFileChooser("C:\\Users\\sujit\\OneDrive\\Desktop\\Java created");
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fc.setDialogTitle("Select a directory");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JAVA CODE","java");
      //  jc.addChoosableFileFilter(new FileNameExtensionFilter("*.java", "java"));
     //   jc.setFileFilter(filter);
        
        
      //  int option = jc.showDialog(null, "Get it");
        int option = fc.showSaveDialog(null);
        
        if(option == JFileChooser.APPROVE_OPTION){
            File f = fc.getSelectedFile();
            System.out.println(f.getPath());
            System.out.println(f.getName());
        }else{
            System.out.println("Nothing is selected.");
        }
    }
    


}
