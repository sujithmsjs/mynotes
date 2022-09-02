package com.work;

import java.io.*;
import util.file.TextWriter;


public class NewMain {

    public static void main(String[] args) {
        
        String loc = "S:\\My Books\\Essentialism.pdf";
        String output = "C:\\Users\\sujit\\OneDrive\\Desktop\\demo.txt";
        
        File file = new File(loc);
        
        System.out.println(file.exists());
        
        
        int[] pages = {15,16,17,18};
        
        
       String text = PDFUtil.extractText(file, pages);
       
       TextWriter.saveTextFile(output, text);
       System.out.println(text);
    
    }

}
