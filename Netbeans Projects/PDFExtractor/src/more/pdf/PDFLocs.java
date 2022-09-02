package more.pdf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;

public class PDFLocs {

    private String str = "";
    int len;

    public int getLen() {
        return len;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
//         File f2 = new File("S:\\Work\\How to say No4.txt");
//         PrintWriter pw = new PrintWriter(f2);         
        String text = getText().toString(); 
         System.out.println(text);
//       pw.println(text);
//        pw.close();
    }
    
    public static StringBuilder getText() throws IOException {
        File f = new File("S:\\Work\\c5cfb002-4035-44ad-8ccd-92d9727d07b4 (2).pdf");
        
        PDFLocs pl = new PDFLocs();
        String output = pl.showTextLocations(f);
        

        
        System.out.println("\n\n\n");
        StringBuilder str = new StringBuilder(output);
         StringBuilder text = new StringBuilder();
        
        for (int i = 0; i < str.length(); i++) {
            //    System.out.print(" "+output.charAt(i)+":"+(int)output.charAt(i));
            

            if (str.charAt(i) == 10) {
               // text.append("\n");
                if (str.charAt(i + 1) == 10) { 
                   // System.out.println("\n");
                    text.append("\n");
                    text.append("\n");
                    // str.deleteCharAt(i); 
                    
                }else if(str.charAt(i+1)==9){
                    str.deleteCharAt(i+1);
                    text.append("\t");
                    
                    
                   // str.deleteCharAt(i+1);
                  //  text.insert(i+1, ' ');
//                    try {
//                        text.replace(i+1,i+2, " ");
//                    } catch (Exception ex) {
//                        System.out.println(ex);
//                    }
                  
                }else{
                    text.append(">>");
                  //  System.out.println("Something is here: "+(int)str.charAt(i));
                }
                
            }else if(str.charAt(i)==9){
                text.append("\t");
             //   System.err.println("Sujith: "+(int)str.charAt(i));
            }
            else{
                text.append(str.charAt(i));
               // System.out.print(str.charAt(i));
            }
        }
        //  pw.println(output);
        //   pw.close();
        //    System.out.println("File saved suffessfully.");
        
        return text;
     //   System.out.println(text);
    }

    public String showTextLocations(File file) throws IOException {
        String output = "";
        try (PDDocument document = Loader.loadPDF(file)) {
            PowerStripper stripper = new PowerStripper();
            stripper.setSortByPosition(true);
            stripper.setStartPage(12);
            stripper.setEndPage(111); //111
            Writer dummy = new OutputStreamWriter(new ByteArrayOutputStream());

            stripper.writeText(document, dummy);

            String text = stripper.getText();
            output = text.replaceAll("\t", " ").replaceAll(">", "\n\t");

        }
        return output;
    }
}
