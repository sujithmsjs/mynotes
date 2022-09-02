package more.pdf;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import util.file.FileUtil;

public final class AddTextToPDF {

    public static void main(String[] args) {
        File file = FileUtil.saveFile();
        String fileName = "sujith.pdf";
        String message = "Hellow world, this is my first pdf.";
        AddTextToPDF.add(fileName, message);
    }

    public static void add(String fileName, String message) {
        try {
            
            PDDocument doc = new PDDocument();

            PDPage page = new PDPage();

            doc.addPage(page);

     //       PDFont font = PDType1Font.HELVETICA_BOLD;

            PDPageContentStream contents = new PDPageContentStream(doc, page);

            contents.beginText();
      //      contents.setFont(font, 12);
            contents.newLineAtOffset(100, 700);
            contents.showText(message);
            contents.endText();

            doc.save(fileName);
        } catch (IOException ex) {
            System.out.println("ex = " + ex);
        }
    }

}
