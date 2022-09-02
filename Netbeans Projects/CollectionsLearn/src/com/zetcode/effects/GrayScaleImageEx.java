package com.zetcode.images;

import com.zetcode.Util;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

class GrayScaleImageEx_Panel extends JPanel {

    private Image mshi;
    private BufferedImage bufimg;
    private Dimension d;

    public GrayScaleImageEx_Panel() {

        loadImage();
        determineAndSetSize();
        createGrayImage();
    }

    private void determineAndSetSize() {

        d = new Dimension();
        d.width = mshi.getWidth(null);
        d.height = mshi.getHeight(null);
        setPreferredSize(d);
    }

    private void createGrayImage() {

        bufimg = new BufferedImage(d.width, d.height,
                BufferedImage.TYPE_BYTE_GRAY);

        Graphics2D g2d = bufimg.createGraphics();
        g2d.drawImage(mshi, 0, 0, null);
        g2d.dispose();
    }

    private void loadImage() {

        mshi = new ImageIcon(Util.imageLoc).getImage();
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(bufimg, null, 0, 0);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }
}

public class GrayScaleImageEx extends JFrame {

    public GrayScaleImageEx() {

        initUI();
    }

    private void initUI() {

        GrayScaleImageEx_Panel dpnl = new GrayScaleImageEx_Panel();
        add(dpnl);

        pack();

        setTitle("GrayScale image");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GrayScaleImageEx ex = new GrayScaleImageEx();
                ex.setVisible(true);
            }
        });
    }
}
