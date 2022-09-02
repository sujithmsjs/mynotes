package com.zetcode.images;



import com.zetcode.Util;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

class FlippedImageEx_Panel extends JPanel {

    private Image mshi;
    private BufferedImage bufimg;
    private final int SPACE = 10;

    public FlippedImageEx_Panel() {

        loadImage();
        createFlippedImage();
        setFlippedImageEx_PanelSize();
    }

    private void loadImage() {

        mshi = new ImageIcon(Util.imageLoc).getImage();
    }

    private void createFlippedImage() {

        bufimg = new BufferedImage(mshi.getWidth(null),
                mshi.getHeight(null), BufferedImage.TYPE_INT_RGB);

        Graphics gb = bufimg.getGraphics();
        gb.drawImage(mshi, 0, 0, null);
        gb.dispose();

        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-mshi.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx,
                AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        bufimg = op.filter(bufimg, null);
    }

    private void setFlippedImageEx_PanelSize() {

        int w = bufimg.getWidth();
        int h = bufimg.getHeight();

        Dimension d = new Dimension(3 * SPACE + 2 * w, h + 2 * SPACE);
        setPreferredSize(d);
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(mshi, SPACE, SPACE, null);
        g2d.drawImage(bufimg, null, 2 * SPACE + bufimg.getWidth(), SPACE);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }
}

public class FlippedImageEx extends JFrame {

    public FlippedImageEx() {

        initUI();
    }

    private void initUI() {

        add(new FlippedImageEx_Panel());
        pack();

        setTitle("Flipped image");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FlippedImageEx ex = new FlippedImageEx();
                ex.setVisible(true);
            }
        });
    }
}
