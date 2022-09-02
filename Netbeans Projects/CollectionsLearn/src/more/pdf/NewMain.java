package more.pdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;


public class NewMain {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        File f = new File("S:\\Work\\How to say No3.txt");
        
         FileReader fr = new FileReader(f);
         StringBuilder sb = new StringBuilder();
         StringBuilder output = new StringBuilder();
         int ch = 0;
         while((ch = fr.read())!= -1){
             sb.append((char)ch);
         }
     //    System.out.println(sb);
         
         
         StringTokenizer st = new StringTokenizer(sb.toString(), ">>");
         while(st.hasMoreTokens()){
           String tempToken =   st.nextToken();
           
           if(isTitle(tempToken)){
               output.append(tempToken.trim());
               output.append("\n");
              // System.out.println(tempToken.trim());
           }else{
               output.append(tempToken.trim());
               output.append("\n");
           }
            // System.out.println(tempToken+"  "+isTitle(tempToken));
         }
         
         System.out.println(output);
    }
    
    private static boolean isTitle(String str){
        boolean isTitle = true;
        for (int i = 0; i < str.length(); i++) {
            if(Character.isAlphabetic(str.charAt(i))){
                if( ! Character.isUpperCase(str.charAt(i))){
                    isTitle = false;
                    break;
                }
            }
            
        }     
        return isTitle;
    }

}
