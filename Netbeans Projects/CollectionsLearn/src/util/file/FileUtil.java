package util.file;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class FileUtil {
    
    public static File saveFile(){
        File file = null;

        //Createing JFileChooser
        JFileChooser fc = new JFileChooser();

        // Setting up Current Directory.
        fc.setCurrentDirectory(new File("S:\\Work"));

        //Creating the PDF FileFiler is to avoid selection of other files.
        PDFFileFilter pdfff = new PDFFileFilter();
        
        //Setting the PDF File Filter.
        fc.setFileFilter(pdfff);

        //Handle open button action.
        int returnVal = fc.showSaveDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            //This is where a real application would open the file.
        }

        return file;
    }
    
    
    public static File chooseFile() {
        File file = null;

        //Createing JFileChooser
        JFileChooser fc = new JFileChooser();

        // Setting up Current Directory.
        fc.setCurrentDirectory(new File("S:\\Work"));

        //To avoid selection of Folders/Directors.
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

        //Creating the PDF FileFiler is to avoid selection of other files.
        PDFFileFilter pdfff = new PDFFileFilter();

        //Setting the PDF File Filter.
        fc.setFileFilter(pdfff);

        //Handle open button action.
        int returnVal = fc.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            //This is where a real application would open the file.
        }

        return file;
    }

    private static class PDFFileFilter extends FileFilter {

        @Override
        public boolean accept(File f) {
            if (f.isDirectory()) {
                return true;
            }
            String extension = getExtension(f);
            return extension.equals(".pdf");
        }

        @Override
        public String getDescription() {
            return "Desc";
        }

        String getExtension(File f) {
            String name = f.getName();
            int index = name.lastIndexOf('.');
            return name.substring(index, name.length());
        }
    }
}
