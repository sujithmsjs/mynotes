package oracle.imgs;

import com.zetcode.Util;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

/**
 * This class demonstrates how to load an Image from an external file
 */
public class GridImagePer extends Component {

    // Settings
    private static final int GAP = 5;
    private static final int BORDER = 10;
    private static final int ROWS = 3;
    private static final int COLS = 10;


    private static int SCREEN_WIDTH = 800;
    private static int SCREEN_HEIGHT = 600;

    BufferedImage img;

    private void paint02(Graphics g) {
        int pWid = SCREEN_WIDTH - 2 * BORDER;
        int pHig = SCREEN_WIDTH - 2 * BORDER;

        int x1 = BORDER;
        int y1 = BORDER;
        
        g.setColor(Color.ORANGE);
        for (int i = 0; i < COLS; i++) {
            x1 = (pWid* i/COLS)+BORDER;
            int w = x1-(pWid* i/COLS);
            int h = (pHig * 1/ROWS)+BORDER;
            g.drawRect(x1, y1, w,  h);
            
        }
    }

//<editor-fold defaultstate="collapsed" desc="Paint 01">
    private void paint01(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        
        int panelWidth = SCREEN_WIDTH - 2 * BORDER;
        System.out.println("panelWidth = " + panelWidth);
        int panelHeight = SCREEN_HEIGHT - 2 * BORDER;
        
        g.drawRect(BORDER, BORDER, panelWidth, panelHeight);
        
        for (int i = 1; i < COLS; i++) {
            
            int x = panelWidth * i / COLS;
            g.setColor(Color.red);
            g.drawLine(x + BORDER, BORDER, x + BORDER, panelHeight + BORDER);
            g.setColor(Color.MAGENTA);
            g.drawLine(x + BORDER + GAP, BORDER, x + BORDER + GAP, panelHeight + BORDER);
            g.drawLine(x + BORDER - GAP, BORDER, x + BORDER - GAP, panelHeight + BORDER);
        }
        
        for (int i = 1; i < ROWS; i++) {
            g.setColor(Color.blue);
            int y = panelHeight * i / ROWS;
            g.drawLine(BORDER, y + BORDER, panelWidth + BORDER, y + BORDER);
            g.setColor(Color.cyan);
            g.drawLine(BORDER, y + BORDER + GAP, panelWidth + BORDER, y + BORDER + GAP);
            g.drawLine(BORDER, y + BORDER - GAP, panelWidth + BORDER, y + BORDER - GAP);
        }
        
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Need Not">
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, SCREEN_HEIGHT, SCREEN_WIDTH);
        paint01(g);
        paint02(g);
    }

    public GridImagePer() {

        try {
            setBackground(Color.black);
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
        f.setBackground(Color.black);
        f.add(new GridImagePer());
        f.pack();
        f.setVisible(true);
    }
//</editor-fold>

}
