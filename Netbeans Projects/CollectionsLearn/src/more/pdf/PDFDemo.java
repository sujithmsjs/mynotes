package more.pdf;

import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class PDFDemo {

    public static void main(String[] args) throws IOException {
        //Creating PDF document object   
        PDDocument doc = new PDDocument();

        for (int i = 0; i < 10; i++) {

            //Creating a new page.
            PDPage page = new PDPage();

            //Adding page to document.
            doc.addPage(page);

        }
        //Saving the document  
        doc.save("G:\\MyJavaPdf.pdf");

        System.out.println("PDF created");

        //Closing the document    
        doc.close();
    }
}
