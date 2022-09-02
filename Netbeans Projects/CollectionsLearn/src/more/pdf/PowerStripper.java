package more.pdf;

import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

class PowerStripper extends PDFTextStripper {

    private static final float LINE_GAP = 16.564117f;
    private static final float PARA_GAP = 23.7659f;

    private StringBuilder text = new StringBuilder();
    private float lastY = 0;

    public PowerStripper() throws IOException {
    }

    protected void writeString(String string, List<TextPosition> tp) throws IOException {

        float nowY = tp.get(0).getY();
        float gap = nowY - lastY;
        TextPosition get = tp.get(0);
         boolean isTitle = false;
         
        if (lastY != nowY) { //Not in same line
            lastY = nowY;
         //   System.out.println();

//            if ((int) get.getXDirAdj() == 72) {
//                text.append("\n");
//            }
            if ((int) get.getXDirAdj() == 93) {
                text.append("\n>");
            }
            
            text.append("\n");
           
            for (int i = 0; i < string.length(); i++) {
                 isTitle = true;
                if(!Character.isUpperCase(string.charAt(i))){
                    isTitle = false;
                    break;
                }
            }
            
            if(isTitle){
              //  System.out.println(string);
                for (int i = 0; i < string.length(); i++) {
                //    System.out.print("__");
                }
            }
            
            
        }
        
        if(!isTitle){
            text.append(string);
           // System.out.print(gap + ": " + string);
        }
        

    }

//    @Override
//    protected void writeString(String string, List<TextPosition> tp) throws IOException {
//        
//        System.out.println(tp.get(0).getY()+" : "+
//                tp.get(0).getFontSizeInPt()+
//                "; Dir"+tp.get(0).getDir()+" "+
//                "; YScale"+tp.get(0).getYScale()+" "+
//                "; YDirAdj"+tp.get(0).getYDirAdj()+" "+
//                "; HeightDir"+tp.get(0).getHeightDir()+" "+
//                "; Height"+tp.get(0).getHeight()+" "+
//                 "; Height"+tp.get(0).getWidthOfSpace()+" "+
//                
//                string);
//    }
    public String getText() {
        return text.toString();
    }

}
