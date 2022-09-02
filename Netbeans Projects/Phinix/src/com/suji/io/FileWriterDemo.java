package com.suji.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FileWriterDemo {

    public static void main(String[] args) throws IOException {
        
        String pathname = "C:\\Users\\sujit\\OneDrive\\Desktop\\JavaFiles\\demo.txt";
        File file = new File(pathname);
        
        //file.createNewFile()
        System.out.println(file.exists());
        System.out.println(file.length()); // The length, in bytes

        //FileWriter(String fileName, boolean append)
        FileWriter out = new FileWriter(file,true);
        out.write("Sujith!");
        out.close();
        
        System.out.println(file.length());
        
    }

}
