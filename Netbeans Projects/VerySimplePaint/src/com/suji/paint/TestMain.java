package com.suji.paint;


import java.io.File;
import java.util.TreeSet;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;


public class TestMain {

    public static void main(String[] args) {
        File f = saveFile();
        System.out.println(f.getPath());
        
    }

    public static File saveFile() {
        File file = null;

        JFileChooser fc = new JFileChooser();

        fc.setCurrentDirectory(new File("C:\\Users\\sujit\\OneDrive\\Desktop\\Java created"));

       fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

        BufferImageFilter bif = new BufferImageFilter();

        fc.setFileFilter(bif);
       
       
        int returnVal = fc.showSaveDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
        }

        return file;
    }
    
 
    
    private static class BufferImageFilter extends FileFilter {
        String[] ext;
           
        BufferImageFilter(){
            ext = getFormats();
        }
        
        @Override
        public boolean accept(File f) {
            if (f.isDirectory()) {
                return true;
            }
            String extension = getExtension(f);
            boolean flag  = false;
            for (int i = 0; i < ext.length; i++) {
                if(ext[i].equalsIgnoreCase(extension)){
                    System.out.println("Accepted: "+f.getPath());
                    flag = true;
                    break;
                }
            }  
            return flag;
        }
        
        private String[] getFormats() {
            String[] formats = ImageIO.getWriterFormatNames();

            TreeSet<String> formatSet = new TreeSet<String>();
            for (String s : formats) {
                formatSet.add(s.toLowerCase());
                
            }
            return formatSet.toArray(new String[0]);
        }

        @Override
        public String getDescription() {
            return "Desc";
        }

        String getExtension(File f) {
            String name = f.getName();
            
            System.out.println(name);
            int index = name.lastIndexOf('.');
            
            if(index>-1){
                name = name.substring(index, name.length());
            }else{
                name = "";
            }     
            
            return name;
        }
    }
}
