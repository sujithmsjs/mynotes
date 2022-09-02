package com.suji.ui.xhtml;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TextFileUtil {

    public static void saveAsHTML(String fileName, String printText) {
        saveAsHTML(fileName, new StringBuilder(printText));
    }

    public static StringBuilder addXhtmlheaders(StringBuilder printText) {
        printText.insert(0, "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
                + "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\"\n"
                + "  \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">\n"
                + "\n"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                + "<head>\n"
                + "  <title></title>\n"
                + "</head>\n"
                + "\n"
                + "<body>");
        printText.append("</body>\n"
                + "</html>");
        return printText;
    }

    public static void saveAsHTML(String fileName, StringBuilder printText) {

        saveText(fileName, addXhtmlheaders(printText));

    }

    public static void saveText(String fileName, StringBuilder printText) {
        FileWriter fr = null;
        PrintWriter pw = null;
        String dir = "C:\\Users\\sujit\\OneDrive\\Desktop\\Articles\\";

    }
    
    public static void saveXhtmlFile(String dir, String fileName, String printText) {
        saveText(dir, fileName, addXhtmlheaders(new StringBuilder(printText)));
    }
    
    public static void saveXhtmlFile(String dir, String fileName, StringBuilder printText){
        saveText(dir,fileName, addXhtmlheaders(printText));
    }

    public static void saveText(String dir, String fileName, StringBuilder printText) {
        FileWriter fr = null;
        PrintWriter pw = null;
        try {

            File file = new File(dir + "\\"+fileName);
            fr = new FileWriter(file);
            pw = new PrintWriter(fr);

            pw.print(printText);
            // System.out.println(sb);
            System.out.println("File Saved Successfully...");
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        } finally {
            try {
                fr.close();
                pw.close();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }
    
   
    
    public static String getFileText(){
        return getFileText("C:\\Users\\sujit\\OneDrive\\Desktop\\Articles\\Text HTMl txt.txt ");
    }
    
    public static String getFileText(String loc) {

        FileReader fr = null;
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();

        try {

            File file = new File(loc);
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String line = br.readLine();

            while (line != null) {
                sb.append(line).append("\n");
                line = br.readLine();
            }
            // System.out.println(sb);

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        } finally {
            try {
                fr.close();
                br.close();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
        return sb.toString();
    }

}
