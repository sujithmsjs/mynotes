package util.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TextWriter {

    public static void main(String[] args) {
        String loc = "C:\\Users\\sujit\\OneDrive\\Desktop\\demo.txt";
        String text = " It's just a check";
        saveTextFile(loc, text);
    }
    
    public static void saveTextFile(String fileName, String text){
        File file = new File(fileName);
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(file);
            pw.println(text);
           
        
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        
        System.out.println(file.exists());
        
         pw.close();
        
    }

}
