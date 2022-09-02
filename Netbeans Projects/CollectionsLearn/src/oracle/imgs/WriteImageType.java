package oracle.imgs;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class WriteImageType {

    public static void save() throws IOException{
        BufferedImage bi = new BufferedImage(300, 300, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bi.createGraphics();
   
        g.setPaint(Color.WHITE);
        g.setStroke(new BasicStroke(3,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
        g.drawRect(10, 10, 280, 280);
        String filePath = "C:\\Users\\sujit\\OneDrive\\Desktop\\Java created\\myPaint.png";
        if(ImageIO.write(bi, "PNG", new File(filePath))){
           System.out.println("Image saved.");
        }
    }
    
    
    public void save2() {
        BufferedImage bImg = new BufferedImage(200,200, BufferedImage.TYPE_INT_RGB);
        Graphics2D cg = bImg.createGraphics();
        //paintAll(cg);
        try {
            cg.drawRect(100, 100, 100, 100);
            String filePath = "C:\\Users\\sujit\\OneDrive\\Desktop\\Java created\\newImg2.png";
            File file = new File(filePath);

            if (ImageIO.write(bImg, "PNG", file)) {
                System.out.println("-- saved");
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void main(String args[]) throws IOException{
        save();
    }
    
    static public void man(String args[]) throws Exception {
        try {
            int width = 200, height = 200;

            // TYPE_INT_ARGB specifies the image format: 8-bit RGBA packed
            // into integer pixels
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            Graphics2D ig2 = bi.createGraphics();

            Font font = new Font("TimesRoman", Font.BOLD, 20);
            ig2.setFont(font);
            String message = "www.java2s.com!";
            FontMetrics fontMetrics = ig2.getFontMetrics();
            int stringWidth = fontMetrics.stringWidth(message);
            int stringHeight = fontMetrics.getAscent();
            ig2.setPaint(Color.black);
            ig2.drawString(message, (width - stringWidth) / 2, height / 2 + stringHeight / 4);
            ig2.drawRect(100, 100, 100, 100);
            
            String filePath = "C:\\Users\\sujit\\OneDrive\\Desktop\\Java created\\";
            
            ImageIO.write(bi, "PNG", new File(filePath+"yourImageName.PNG"));
            ImageIO.write(bi, "JPEG", new File(filePath+"yourImageName.JPG"));
            ImageIO.write(bi, "gif", new File(filePath+"yourImageName.GIF"));
            ImageIO.write(bi, "BMP", new File(filePath+"yourImageName.BMP"));

        } catch (IOException ie) {
            ie.printStackTrace();
        }

    }
}
