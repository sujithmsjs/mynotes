package more.pdf.practice;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import more.pdf.PDFUtil;

public class PDFCheck {
    
    
    public static void main(String[] args) throws IOException {
        File f = new File("S:\\Work\\c5cfb002-4035-44ad-8ccd-92d9727d07b4 (2).pdf");
      
        PDFUtil p = new PDFUtil();
        p.showTextLocations(f);

    }

}
