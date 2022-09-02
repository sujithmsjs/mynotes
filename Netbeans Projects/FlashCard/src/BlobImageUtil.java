
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class BlobImageUtil {

    public static void main(String[] args) {

    }

    public static InputStream getBlobFile(File file) {
        InputStream is = null;
        try {

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return is;
    }

    public static ImageIcon getImageIcon(File file, Dimension size) {
        ImageIcon imageIcon = null;
        BufferedImage img = null;
        try {
            if (file != null) {
                img = ImageIO.read(file);
                Image dimg = img.getScaledInstance((int) size.getWidth(), (int) size.getHeight(), Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(dimg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageIcon;
    }
    
    
    
    

    public static InputStream toIPS(JLabel label) {
        InputStream is = null;
        try {
            ImageIcon imgIcon = (ImageIcon) label.getIcon();
            BufferedImage bi = getBufferedImage(imgIcon);
            is = getInputStream(bi);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return is;
    }

    public static BufferedImage getBufferedImage(ImageIcon icon) {
        int h = icon.getIconHeight();
        int w = icon.getIconWidth();
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();
        // paint the Icon to the BufferedImage.
        icon.paintIcon(null, g, 0, 0);
        g.dispose();
        return bi;
    }

    public static InputStream getInputStream(BufferedImage buffImage) {
        InputStream is = null;
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(buffImage, "jpeg", os);
            // Passing: ​(RenderedImage im, String formatName, OutputStream output)
            is = new ByteArrayInputStream(os.toByteArray());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return is;
    }

    public static ImageIcon getImageIcon(Blob blob, Dimension size) {
        ImageIcon imageIcon = null;
        try {
            InputStream is = blob.getBinaryStream();
            BufferedImage bimg = ImageIO.read(is);
            Image image = bimg.getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(image);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return imageIcon;
    }
    
    
    public static ImageIcon getImageIcon(InputStream inputStream, Dimension size) {
        ImageIcon imageIcon = null;
        try {
            BufferedImage bimg = ImageIO.read(inputStream);
            Image image = bimg.getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(image);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return imageIcon;
    }
    
    public static ImageIcon getImageIcon(InputStream inputStream) {
        ImageIcon imageIcon = null;
        try {
            BufferedImage bimg = ImageIO.read(inputStream);
           // Image image = bimg.getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(bimg);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return imageIcon;
    }
    
    
    

    public static ImageIcon setImageFile(Component parent,int h, int w) {
        File file = null;
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg", "png", "jpeg");
        fc.setFileFilter(filter);

        int ip = fc.showOpenDialog(parent);

        if (ip == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
        }
        ImageIcon imageIcon = BlobImageUtil.getImageIcon(file, new Dimension(h,w));
        return imageIcon;
        //label.setIcon(imageIcon);
    }
    public static ImageIcon setImageFile(Component parent,Dimension size){
        File file = null;
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg", "png", "jpeg");
        fc.setFileFilter(filter);

        int ip = fc.showOpenDialog(parent);

        if (ip == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
        }
        ImageIcon imageIcon = BlobImageUtil.getImageIcon(file,size);
        return imageIcon;
    }
    
}
