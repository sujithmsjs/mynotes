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

public class ImgBuff extends JPanel {

    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 600;
    public static final int IMAGE_SCALE = 60;

    BufferedImage img ;

    public ImgBuff() throws IOException {
        initUI();
    }
    
    public void loadImg() throws IOException{
    
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
            
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setFont(new Font("Tahoma", Font.BOLD, 40));

        g2d.drawImage(img, 0, 0, img.getWidth(null) * IMAGE_SCALE / 100, img.getHeight(null) * IMAGE_SCALE / 100, null);

    }

    public static void main(String[] args) throws IOException {
        ImgBuff S = new ImgBuff();
        new MyFrame(S);

    }

}
