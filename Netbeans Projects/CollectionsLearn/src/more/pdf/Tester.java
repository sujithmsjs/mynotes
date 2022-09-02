package more.pdf;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.text.PDFTextStripper;
import util.file.FileUtil;


public class Tester {

    public static void main(String[] args) {

        File file = FileUtil.chooseFile();
        try {
            
            //Load the doucument
            PDDocument document = Loader.loadPDF(file);
            
            //Get the access permision.
            AccessPermission ap = document.getCurrentAccessPermission();
            
            //Check where it can extractable or not
            boolean canExtractContent = ap.canExtractContent();
            
            //If file is not Extractable program has to EXIT
            if (canExtractContent) {
                System.out.println("Hey, you can Extract this file.");
            }else{
                throw new IOException("You do not have permission to extract text");
            }
            
            //Create Stripper.
            PDFTextStripper stripper = new PDFTextStripper();

            stripper.setSortByPosition(true);
            
            for (int p = 1; p <= document.getNumberOfPages(); ++p) {
                
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
        
    }

}
