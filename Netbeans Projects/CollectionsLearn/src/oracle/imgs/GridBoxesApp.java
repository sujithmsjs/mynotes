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
public class GridBoxesApp extends Component {

    private static final int PARTS = 10;
    private static final int GAP = 5;
    private static final int SIDE_SIZE = 50;
    private static final int START_X = 20;
    private static final int START_Y = 20;
    private static final int SCREEN_WIDTH = 200;
    private static final int SCREEN_HEIGHT = 200;

    Rectangle rect = new Rectangle(0, 0, SIDE_SIZE, SIDE_SIZE);

    BufferedImage img;

    Rectangle destImg = new Rectangle(300, 300, 150, 150);
    Rectangle srcImg = new Rectangle(300, 50, 150, 150);

    private void paint02(Graphics g) {
        int tempX = START_X;
        int tempY = START_Y;
        
        int panelX = SCREEN_WIDTH - START_X;
        
        int panelY = SCREEN_HEIGHT - START_Y;
        
        
        
        int rowBoxes = panelY / (SIDE_SIZE + GAP);
        
        int colBoxes = panelX / (SIDE_SIZE + GAP);
        
        System.out.println("rowBoxes = " + rowBoxes);
        System.out.println("colBoxes = " + colBoxes);
        
   
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
    
//<editor-fold defaultstate="collapsed" desc="paint01(g)">
    private void paint01(Graphics g)
    {
        int tempX = START_X;
        int panel = SCREEN_WIDTH - START_X;
        int rowBoxes = panel/(SIDE_SIZE+GAP);
        for (int i = 0; i < rowBoxes; i++) {
            
            g.fillRect(tempX, START_Y, SIDE_SIZE, SIDE_SIZE);
            tempX += (SIDE_SIZE + GAP);
            System.out.println(tempX+" / "+rowBoxes);
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Need Not">
    public void paint(Graphics g) {
        paint02(g);
    }
    
    public GridBoxesApp() {
        try {
            img = ImageIO.read(new File(Util.imageLoc));
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
        
        f.add(new GridBoxesApp());
        f.pack();
        f.setVisible(true);
    }
//</editor-fold>

}
