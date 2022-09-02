package oracle.imgs;

import com.zetcode.Util;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

/**
 * This class demonstrates how to load an Image from an external file
 */
public class GridImage extends Component {

    // Settings
    private static final int GAP = 5;
    private static final int SIDE_SIZE = 100;
    private static final int START_X = 0;
    private static final int START_Y = 0;
    private static int SCREEN_WIDTH;
    private static int SCREEN_HEIGHT;
    BufferedImage img;


    private void paint03(Graphics g) {
        int tempX = START_X;
        int tempY = START_Y;

        int panelX = SCREEN_WIDTH - START_X;

        int panelY = SCREEN_HEIGHT - START_Y;

        int rowBoxes = panelY / (SIDE_SIZE + GAP);

        int colBoxes = panelX / (SIDE_SIZE + GAP);

        
        //Increasing 1 point to fit to the image.
        rowBoxes++;
        colBoxes++;
        
        System.out.println("rowBoxes = " + rowBoxes);
        System.out.println("colBoxes = " + colBoxes);
        
       // g.drawImage(img, START_X, START_Y, null);
        for (int i = 0; i < rowBoxes; i++) {

            for (int j = 0; j < colBoxes; j++) {

                //g.fillRect(tempX, tempY, SIDE_SIZE, SIDE_SIZE);
                g.drawImage(img, tempX, tempY, tempX+SIDE_SIZE, tempY+SIDE_SIZE, tempX, tempY, tempX+SIDE_SIZE, tempY+SIDE_SIZE, null);
                
                tempX += (SIDE_SIZE + GAP);
                System.out.println(tempX + " / " + rowBoxes);

            }

            tempY += (SIDE_SIZE + GAP);
            tempX = START_X;
        }
    }
    
    
//<editor-fold defaultstate="collapsed" desc="Paint 02">
    private void paint02(Graphics g) {
        int tempX = START_X;
        int tempY = START_Y;
        
        int panelX = SCREEN_WIDTH - START_X;
        
        int panelY = SCREEN_HEIGHT - START_Y;
        
        int rowBoxes = panelY / (SIDE_SIZE + GAP);
        
        int colBoxes = panelX / (SIDE_SIZE + GAP);
        
        System.out.println("rowBoxes = " + rowBoxes);
        System.out.println("colBoxes = " + colBoxes);
        g.drawImage(img, START_X, START_Y, null);
        for (int i = 0; i < rowBoxes; i++) {
            
            for (int j = 0; j < colBoxes; j++) {
                
                g.fillRect(tempX, tempY, SIDE_SIZE, SIDE_SIZE);
                tempX += (SIDE_SIZE + GAP);
                System.out.println(tempX + " / " + rowBoxes);
                
            }
            
            tempY += (SIDE_SIZE + GAP);
            tempX = START_X;
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="paint01(g)">
    private void paint01(Graphics g) {
        int tempX = START_X;
        int panel = SCREEN_WIDTH - START_X;
        int rowBoxes = panel / (SIDE_SIZE + GAP);
        for (int i = 0; i < rowBoxes; i++) {

            g.fillRect(tempX, START_Y, SIDE_SIZE, SIDE_SIZE);
            tempX += (SIDE_SIZE + GAP);
            System.out.println(tempX + " / " + rowBoxes);
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Need Not">
    public void paint(Graphics g) {
        paint03(g);
    }

    public GridImage() {
        
        try {
            img = ImageIO.read(new File(Util.imageLoc));
            SCREEN_WIDTH = img.getWidth(null);
            SCREEN_HEIGHT = img.getHeight(null);
            

            setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
            
        } catch (IOException e) {
        }
    }

    public static void main(String[] args) {

        JFrame f = new JFrame("Load Image Sample");

        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        f.add(new GridImage());
        f.pack();
        f.setVisible(true);
    }
//</editor-fold>

}
