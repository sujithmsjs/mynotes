
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public final class ExtractTextByArea {


    public static void main(String[] args) {
        String fileName = "S:\\Work\\c5cfb002-4035-44ad-8ccd-92d9727d07b4 (2).pdf";
        File f = new File(fileName);
        ExtractTextByArea.show(f);
    }

    public static void show(File file) {
        try {
            PDDocument document = Loader.loadPDF(file);
            
            PDFTextStripperByArea stripper = new PDFTextStripperByArea();
            
            stripper.setSortByPosition(true);

            Rectangle rect = new Rectangle(10, 1, 275, 60);

            stripper.addRegion("class1", rect);
            
            PDPage firstPage = document.getPage(12);
            
            stripper.extractRegions(firstPage);
            
            System.out.println("Text in the area:" + rect);
            
            System.out.println(stripper.getTextForRegion("class1"));
            
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }

}
