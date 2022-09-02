package learn.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JProgressBar;


public class NewMain {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String fileName = "C:\\Users\\sujit\\OneDrive\\Desktop\\wall.jpg";
        File file = new File(fileName);
        if(file.exists()){
            System.out.println("File exists.");
            long length = file.length();
            System.out.println("length = " + length);
        }   
        FileInputStream fis = new FileInputStream(file);
        
        
        
        
        int b = 0;
        
        
        NewJFrame p = new NewJFrame();
        JProgressBar pro = p.getPro();
        //total=1000 finished=500 finished/total*100;
        p.setVisible(true);
        int total = fis.available();
        while((b=fis.read())!=-1){
            System.out.println((char)b);
            int remain = fis.available();
            int left = 100 - (int) ((float) remain/total*100);
            pro.setValue(left);
            
            System.out.println("available = " + left);
        }
        fis.close();
        
        
    }

}
