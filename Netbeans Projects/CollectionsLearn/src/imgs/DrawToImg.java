package learn.animation.images;

import com.zetcode.Util;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import learn.animation.MyFrame;

public class DrawToImg extends JPanel {

    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 600;
    public static final int IMAGE_SCALE = 60;

    BufferedImage img;

    public DrawToImg() throws IOException {
        initUI();
    }

    public void loadImg() throws IOException {

    }

    private void initUI() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setFocusable(true);
        setBackground(Color.BLACK);
        loadImage();
    }

    public void loadImage() {
        try {
            img = ImageIO.read(new File(Util.imageLoc));
            BufferedImage off_Image= new BufferedImage(100, 50, BufferedImage.TYPE_INT_ARGB);
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D) g;

        
//        Graphics2D img2d = off_Image.createGraphics();

    }

    public static void main(String[] args) throws IOException {
        DrawToImg S = new DrawToImg();
        new MyFrame(S);

    }

}
