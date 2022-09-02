package more.pdf;



import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import org.apache.pdfbox.pdmodel.PDPageContentStream;

import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 * Creates a "Hello World" PDF using the built-in Helvetica font.
 *
 * The example is taken from the PDF file format specification.
 */
public final class HelloWorldPDF {

    public static void main(String[] args) throws IOException {
        
        File f = new File("S:\\Work\\hellow.pdf");
        
        
        HelloWorldPDF.add(f.getPath(), "Hey how are you");
        
        
        if(f.exists())
            System.out.println("File created successfully.");
    }

    public static void add(String filename,String message) throws IOException {

        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage();
            doc.addPage(page);

          //  PDFont font = PDType1Font.HELVETICA_BOLD;

            try (PDPageContentStream contents = new PDPageContentStream(doc, page)) {
                contents.beginText();
             //   contents.setFont(font, 30);
                contents.newLineAtOffset(100, 700);
                contents.showText(message);
                contents.endText();
            }

            doc.save(filename);
        }
    }
}
