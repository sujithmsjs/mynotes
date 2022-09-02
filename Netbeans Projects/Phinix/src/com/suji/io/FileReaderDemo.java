package com.suji.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderDemo {

    public static void main(String[] args) throws IOException {

        String pathname = "C:\\Users\\sujit\\OneDrive\\Desktop\\JavaFiles\\demo.txt";
        File file = new File(pathname);

        //file.createNewFile()
        System.out.println(file.exists());
        System.out.println(file.length()); // The length, in bytes

        
        String text = readFileText(file);
        System.out.println(text);
    }
    
    public static String readFileText1(File file) throws IOException{
        //File Reader
        FileReader in = new FileReader(file);

        char ch[] = new char[100];

        in.read(ch);

        System.out.println(ch);
        System.out.println(ch.length);

        String outputText = new String(ch);
        System.out.println(outputText);
        System.out.println(outputText.length());
        System.out.println(outputText.trim().length());

        in.close();
        
        return outputText;
    }

    public static String readFileText(File file) throws FileNotFoundException, IOException {

        StringBuilder output = new StringBuilder();
        
        FileReader in = new FileReader(file);
        BufferedReader br = new BufferedReader(in);
        
        
        String line;
        while ((line = br.readLine()) != null) {
            
            output.append( br.readLine());
            
        }
        return output.toString();
    }

}
