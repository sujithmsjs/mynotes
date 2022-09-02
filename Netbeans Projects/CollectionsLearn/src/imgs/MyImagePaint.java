package learn.animation.images;

import com.zetcode.Util;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import learn.animation.MyFrame;

public class MyImagePaint extends JPanel {

    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 600;
    public static final int IMAGE_SCALE = 60;

    private Image img;
    
   BufferedImage bufimg = new BufferedImage(600, 600, 
                BufferedImage.TYPE_BYTE_GRAY);
    
    
    public MyImagePaint() {
        initUI();
    }

    private void initUI() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setFocusable(true);
        setBackground(Color.BLACK);
        loadImage();
    }

    public void loadImage(){
        img = new ImageIcon(Util.imageLoc).getImage();
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setFont(new Font("Tahoma",Font.BOLD,40));

        

       
       g2d.drawImage(img, 0, 0, img.getWidth(null)*IMAGE_SCALE/100, img.getHeight(null)*IMAGE_SCALE/100, null);
       
       
    }

    public static void main(String[] args) {
        MyImagePaint S = new MyImagePaint();
        new MyFrame(S);

    }

}
