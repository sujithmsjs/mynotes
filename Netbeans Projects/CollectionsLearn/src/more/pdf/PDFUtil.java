package more.pdf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

public class PDFUtil extends PDFTextStripper {

    public PDFUtil() throws IOException {
    }

    public static String getText(File file, int staringPage, int endingPage) {
        String text = null;
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

                //Create Stripper.
                PDFTextStripper stripper = new PDFTextStripper();

                stripper.setSortByPosition(true);
                stripper.setStartPage(staringPage);
                stripper.setEndPage(endingPage);

                text = stripper.getText(document);
            }

        } catch (IOException ex) {
            System.out.println(ex);
        }
        return text;
    }

    public void showTextLocations(File file) throws IOException {

        try (PDDocument document = Loader.loadPDF(file)) {
            PDFTextStripper stripper = new PDFUtil();
            stripper.setSortByPosition(true);
            stripper.setStartPage(12);
            stripper.setEndPage(12);

            Writer dummy = new OutputStreamWriter(new ByteArrayOutputStream());

            stripper.writeText(document, dummy);
            showMe();
        }
    }

    String str ="";
    
    public void showMe(){
        System.out.println("Execute at last.");
        System.out.println(str.length());
    }
    
    @Override
    protected void writeString(String string, List<TextPosition> textPositions) throws IOException {
        
        TextPosition get = textPositions.get(0);
            
            if ((int)get.getXDirAdj() == 72) {
                System.out.println("New Line");
                str+="\n";
            }
            if ((int)get.getXDirAdj() == 93) {
                 str+="\n\t";
                System.out.println("New para");
                System.out.println();
            }
            
        for (TextPosition text : textPositions) {
            if(get.getUnicode().equals("\t")){
                System.out.print(" ");
                str+=" ";
            }
            else{
                 System.out.print(text.getUnicode());
                 str+=text.getUnicode();
            }
               
        }
        //System.out.println(al);

//        TextPosition fL = textPositions.get(0);
//        String unicode = fL.getUnicode();
//        System.out.println(fL);
//        
//        System.out.print("unicode = " + unicode);
//        
//        float x = fL.getX();
//        System.out.print(" x = " + x);
//        
//        float xDirAdj = fL.getXDirAdj();
//        System.out.print(" xDirAdj = " + xDirAdj);
//        
//        float xScale = fL.getXScale();
//        
//        System.out.println(" xScale = " + xScale);
//        for (TextPosition text : textPositions) {
//            al.add(text.getXDirAdj());
//            if(text.getXDirAdj()==72.0)
//                System.out.println("---------------- New line here.");
//
//            
//            System.out.println(text.getUnicode() + " [" + text.getXDirAdj() + ","
//                    + text.getYDirAdj() + " fs=" + text.getFontSize() + " xscale="
//                    + text.getXScale() + " height=" + text.getHeightDir() + " space="
//                    + text.getWidthOfSpace() + " width="
//                    + text.getWidthDirAdj() + "]");
//        }
    }

    private void writeString2(String string, List<TextPosition> textPositions) throws IOException {
        for (TextPosition text : textPositions) {
            System.out.println(text.getUnicode() + " [" + text.getXDirAdj() + ","
                    + text.getYDirAdj() + " fs=" + text.getFontSize() + " xscale="
                    + text.getXScale() + " height=" + text.getHeightDir() + " space="
                    + text.getWidthOfSpace() + " width="
                    + text.getWidthDirAdj() + "]");
        }
    }

}
