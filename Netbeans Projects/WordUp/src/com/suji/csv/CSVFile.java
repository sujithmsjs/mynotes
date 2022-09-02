package com.suji.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CSVFile {


    public static String emptyStr = "empt";


    private CSVFile(File csvFile) {
        
    }
    
    
    
    public static Sheet getSheet(File file) {
        
        Sheet sheet = new Sheet();
        
        FileReader fr = null;
        BufferedReader br = null;
        try {
            //////////////  LOGIC ////////////////////
            
           // File file = new File("C:\\Users\\sujit\\OneDrive\\Desktop\\Book1.csv");
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            
            String line = null;
            
            while ((line = br.readLine()) != null ) {
                
                //System.out.println(line);
                List<String> list = new ArrayList<>();
                String[] tokens = line.split(",");
                
                boolean hasContent = false;
                
                for (String token : tokens) {
                    token = token.trim();
                    
                    if(token.length()> 0){
                        hasContent = true;
                        list.add(token);
                    }else{
                        list.add(emptyStr);
                    }  
                }
                if(hasContent) {
                    sheet.addList(list);
                }
            }
            
            ////////////////////////////////////////////
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
        return sheet;
    }
}
