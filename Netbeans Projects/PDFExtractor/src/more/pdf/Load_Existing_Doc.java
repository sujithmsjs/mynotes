package more.pdf;

import java.io.File;
import org.apache.pdfbox.pdmodel.PDDocument;  
import util.file.FileUtil;

public class Load_Existing_Doc {

    public static void main(String[] args) {

        File file = FileUtil.chooseFile();
        
        PDDocument doc = new PDDocument();
        
       // PDDocument.load(file);
        
        
        
        
    }
}

    
