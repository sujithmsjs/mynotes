package com.suji.pro1.note.demo;

import java.io.File;
import java.io.IOException;
import java.util.Date;


public class AutoSave {

    public static void main(String[] args) throws IOException {
        
        File file = new File("C:\\Users\\sujit\\OneDrive\\Desktop");
        System.out.println(file.isFile());
        System.out.println(file.isDirectory());
        
        
        
      //  System.out.printf("%1$s%2$tb_%2$td_%2$tH%2$tM", "Due date:", new Date());
       String dateFormat = String.format("%1$s%2$tb %2$td %2$tH%2$tM %2$tS.txt", "", new Date());
        System.out.println(dateFormat);
        //System.out.println(new Date());
    
    }

}
