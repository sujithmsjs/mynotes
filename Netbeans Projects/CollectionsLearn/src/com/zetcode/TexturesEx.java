package com.zetcode;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

class TexturesEx_Panel extends JPanel {

    private BufferedImage wood;
    private BufferedImage java;
    private BufferedImage pane;
    
    private TexturePaint slatetp;
    private TexturePaint javatp;
    private TexturePaint panetp;

    public TexturesEx_Panel() {

        loadImages();
    }

    private void loadImages() {

        try {
            File file = new File("C:\\Users\\sujit\\OneDrive\\Documents\\NetBeansProjects\\CollectionsLearn\\src\\com\\zetcode\\wood.jpg");
            System.out.println(file.exists());
            
            wood = ImageIO.read(file);

        } catch (IOException ex) {

            Logger.getLogger(TexturesEx_Panel.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g.create();

        slatetp = new TexturePaint(wood, new Rectangle(0, 0, 90, 60));

        
        g2d.setPaint(slatetp);
        g2d.fillRect(0, 0, getSize().width,getSize().height);

        
        
        
//        g2d.setPaint(javatp);
//        g2d.fillRect(130, 15, 90, 60);
//
//        g2d.setPaint(panetp);
//        g2d.fillRect(250, 15, 90, 60);

        g2d.dispose();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }
}

public class TexturesEx extends JFrame {

    public TexturesEx() {

        initUI();
    }

    private void initUI() {

        add(new TexturesEx_Panel());

        setTitle("Textures");
        setSize(360, 120);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                TexturesEx ex = new TexturesEx();
                ex.setVisible(true);
            }
        });
    }
}
