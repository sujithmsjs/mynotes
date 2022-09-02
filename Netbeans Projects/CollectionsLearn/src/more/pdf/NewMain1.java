package more.pdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import static more.pdf.PDFLocs.getText;


public class NewMain1 {

    private static StringBuilder text = new StringBuilder();
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
         String str = getText().toString();
         tokenize(str);
         
         
        File f2 = new File("S:\\Work\\How to say No Org.txt");
        PrintWriter pw = new PrintWriter(f2);

        pw.println(text);
        pw.close();
        
        
        
        System.out.println(text);
  //       System.out.println("File written successfully.");
    }
    
    public static void tokenize(String str){
        StringTokenizer st = new StringTokenizer(str.toString(), "**");
        
        while (st.hasMoreTokens()) {
            subtokenize(st.nextToken().trim());
            text.append("\n");
            text.append("\n");
        }
    }
    
    public static void subtokenize(String str){
        StringTokenizer st = new StringTokenizer(str.toString(), ">>");
        
        
        
        
        while (st.hasMoreTokens()) {
            
            String s = st.nextToken().trim();
   
            
            if(isTitle(s)){
               text.append("\n");
                text.append("\n");
               text.append(s);
               text.append("\n");
               
            }else{
                text.append(" ");
                text.append(s);
            }
                  
            
            
            
        }
    }
    
    
    public static StringBuilder getText() throws FileNotFoundException, IOException{
        File f = new File("S:\\Work\\How to say No4.txt");

        FileReader fr = new FileReader(f);
        StringBuilder sb = new StringBuilder();
        
        int ch = 0;
        while ((ch = fr.read()) != -1) {
            sb.append((char) ch);
        }
        
        
        return sb;
    }
    
    
    private static boolean isTitle(String str) {
        boolean isTitle = true;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isAlphabetic(str.charAt(i))) {
                if (!Character.isUpperCase(str.charAt(i))) {
                    isTitle = false;
                    break;
                }
            }
        }
        return isTitle;
    }

}
