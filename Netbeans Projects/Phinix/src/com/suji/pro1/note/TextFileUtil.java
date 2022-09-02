package com.suji.pro1.note;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TextFileUtil {

    public static boolean writeFile(File f, String text) throws IOException {

        PrintWriter out = new PrintWriter(new FileWriter(f, false));
        out.write(text);
        out.close();
        
        boolean flag = f.exists();
        
        return flag;

    }
    
    public static String readFileText(File file) throws FileNotFoundException, IOException {

        StringBuilder output = new StringBuilder();

        FileReader in = new FileReader(file);
        BufferedReader br = new BufferedReader(in);

        String line;
        while ((line = br.readLine()) != null) {

            output.append(line);

        }
        
        in.close();
        br.close();
        
        return output.toString();
    }
    
    

}
