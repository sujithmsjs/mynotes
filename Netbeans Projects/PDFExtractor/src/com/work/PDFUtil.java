package com.work;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.text.PDFTextStripper;
import util.str.StrUtil;


public class PDFUtil {

    public static String extractText(File file, int[] pages){
        
        return _extract(file,pages);
    }
    
    private static String _extract(File file){
        
        try {
            
            //Load PDF File.
            PDDocument document = Loader.loadPDF(file);
            
            // Check for Access Permission.
            AccessPermission ap = document.getCurrentAccessPermission();
            
            //Check it can be extracted.
            if (!ap.canExtractContent()) {
                throw new IOException("You do not have permission to extract text");
            }
            
            PDFTextStripper stripper = new PDFTextStripper();

            
            stripper.setSortByPosition(true);
            
            int totalPages = document.getNumberOfPages();
            
            for (int p = 1; p <= 5; ++p) {
                // Set the page interval to extract. If you don't, then all pages would be extracted.
                stripper.setStartPage(p);
                stripper.setEndPage(p);

                // let the magic happen
                String text = stripper.getText(document);

                // do some nice output with a header
                String pageStr = String.format("page %d:", p);
                System.out.println(pageStr);
                for (int i = 0; i < pageStr.length(); ++i) {
                    System.out.print("-");
                }
                System.out.println();
                System.out.println(text.trim());
                System.out.println();

                // If the extracted text is empty or gibberish, please try extracting text
                // with Adobe Reader first before asking for help. Also read the FAQ
                // on the website: 
                // https://pdfbox.apache.org/2.0/faq.html#text-extraction
            }     
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return null;
    }
    
     private static String _extract(File file, int[] pages){
        
        //Out put
        String str = null;
         
         
        try {
            
            //Load PDF File.
            PDDocument document = Loader.loadPDF(file);
            
            
            
            
            
            
            // Check for Access Permission.
            AccessPermission ap = document.getCurrentAccessPermission();
            
            //Check it can be extracted.
            if (!ap.canExtractContent()) {
                throw new IOException("You do not have permission to extract text");
            }
            
            PDFTextStripper stripper = new PDFTextStripper();

            
            stripper.setSortByPosition(true);
            
            int totalPages = document.getNumberOfPages();
            
           
           StringBuilder sb = new StringBuilder();
            
            for (int p = 1; p <= totalPages; p++) {
                // Set the page interval to extract. If you don't, then all pages would be extracted.
                stripper.setStartPage(p);
                stripper.setEndPage(p);

                // let the magic happen
                
                String text = stripper.getText(document);
                sb.append(text);  
                
                /*
                *@ = Tab
                #$ = Next line(No need to go to next line.)
                * = Simple Space
                 */
            
            }    
            
            for (int i = 0; i < sb.length(); i++) {

                    char ch = sb.charAt(i);
                    int ascii = sb.charAt(i);

                       // System.out.println(text.charAt(i)+" : "+(int)text.charAt(i));
                   
                    
                    switch(ascii){
                        case 9: // 9 : Horizontal Tab Replace with Space
                            
                            sb.replace(i, i+1,"*");
                            
                            break;
                        case 10: // 10: Line Feed.
                            
                            sb.replace(i, i+1,"$");
                            
                            break;
                        case 13: // 13: CArriage Return
                            
                            sb.replace(i, i+1,"#");
                            
                            break;
                        case 32: // 32: Space.
                            
                            sb.replace(i, i+1,"@");
                            
                            break;
                        default:
                            break;
                    }
            }
            
            
            str = StrUtil.replaceEverything(sb.toString(), "*@", " ");
            str = StrUtil.replaceEverything(str, "#$", " ");
            str = StrUtil.replaceEverything(str, "*", " ");
            
            
            
            
            
            
        } catch (IOException ex) {
            System.out.println(ex);
        }  
        return str;
    }
     
     
   private void cleanText(String text){
       
   }
}
