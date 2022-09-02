package more.pdf;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.text.PDFTextStripper;

public class ExtractTextSimple {

    ExtractTextSimple() {
       
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            usage();
        }

        try (PDDocument document = Loader.loadPDF(new File(args[0]))) {
            
            AccessPermission ap = document.getCurrentAccessPermission();
            
            if (!ap.canExtractContent()) {
                throw new IOException("You do not have permission to extract text");
            }

            PDFTextStripper stripper = new PDFTextStripper();

            stripper.setSortByPosition(true);

            for (int i = 1; i <= document.getNumberOfPages(); i++) {
                // Set the page interval to extract. If you don't, then all pages would be extracted.
                stripper.setStartPage(i);
                stripper.setEndPage(i);

                // let the magic happen
                String text = stripper.getText(document);

                // do some nice output with a header
                String pageStr = String.format("page %d:", i);
                System.out.println(pageStr);
                for (int j = 0; j < pageStr.length(); ++j) {
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
        }
    }

    /**
     * This will print the usage for this document.
     */
    private static void usage() {
        System.err.println("Usage: java " + ExtractTextSimple.class.getName() + " <input-pdf>");
        System.exit(-1);
    }
}
