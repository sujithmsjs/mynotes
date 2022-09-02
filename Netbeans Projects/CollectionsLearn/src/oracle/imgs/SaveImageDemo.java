package oracle.imgs;

import com.zetcode.Util;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

class Board extends JPanel{

    public static final Dimension SCREEN_SIZE = new Dimension(800, 600);

    private  Graphics2D g2d;
    private  BufferedImage bi;
    
    public Board() {
        setPreferredSize(SCREEN_SIZE);
        setBackground(Color.BLACK);
        getFormats();
    }
    
    //To print the supported formats.
    public String[] getFormats() {
        String[] formats = ImageIO.getWriterFormatNames();

        TreeSet<String> formatSet = new TreeSet<String>();
        for (String s : formats) {
            formatSet.add(s.toLowerCase());
            System.out.println(s);
        }
        return formatSet.toArray(new String[0]);
    }
    
    @Override
    public void paintComponent(Graphics g){
            super.paintComponent(g);
            
            Graphics2D g2d =  (Graphics2D) g;
            g2d.drawRect(100, 100, 100, 100);  
    }
    

    
    
    
    
}


public class SaveImageDemo extends JFrame {

    Board b;

    SaveImageDemo() {
        b = new Board();
        add(b);
        setTitle("Just demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new SaveImageDemo().setVisible(true);
      
    }

}
