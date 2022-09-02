package com.suji.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class PrintWriterDemo  {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        String pathname = "C:\\Users\\sujit\\OneDrive\\Desktop\\JavaFiles\\demo.txt";
        File file = new File(pathname);
    
        writeText(file);        

    }

    private static void writeText(File file) throws IOException {

        FileWriter fileWriter = new FileWriter(file, true);

        PrintWriter out = new PrintWriter(fileWriter);

        System.out.println("Before Length: " + file.length());

        out.println("This is Reandom Text");

        out.close();
        fileWriter.close();

        System.out.println("After Length: " + file.length());
        

    }

}
