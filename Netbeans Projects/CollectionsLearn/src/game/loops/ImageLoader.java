package game.loops;

import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;


public class ImageLoader {

    public static Image getImage(String path) {
        File file = new File(path);
        Image img = null;
        if(file.exists()){
           img = new  ImageIcon(path).getImage();
           System.out.println("Image found: "+img.getWidth(null)+"x"+img.getWidth(null));
        }else{
            System.out.println("Image not found.");
        }
       return img;
    }
}
