package com.suji.reg;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegDemoWithPrint {

    private static PrintWriter pw;
    
    static{
        
        try {
            pw = new PrintWriter(new FileWriter("C:\\Users\\sujit\\OneDrive\\Desktop\\Articles\\RegEx.txt", true));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        
    }

    
    
    
    public static void main(String[] args) throws FileNotFoundException {
        String que = "Parenthises Matching. \\([^)]+\\)"; 
        String p = "[\\s]*[.][\\s]*"; // 
       // String m = "Sujith Suma Diyva Vinni Sumanth Anitha Chanti Vishlesh";
        String m = "";
        
        Pattern ptrn = Pattern.compile(p);
        Matcher mat = ptrn.matcher(m);
        
        println("Que :"+que);
        println("RegExp :"+p);
        println("Text: "+m);
        println("Output:");
        
        int countMat = 0;
        while (mat.find()) {
            countMat++;
            println(mat.start() + "-" + mat.end() + " : " + mat.group());

        }
        println("Matches : " + countMat);
        println("");
        pw.close();
    }
    public static void println(String str){
        System.out.println(str);
        pw.println(str);
    }
    

}
