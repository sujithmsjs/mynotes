package com.zetcode;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

class CompositionEx2_Panel extends JPanel {

    private static final int RECT_SIZE = 120;
    private static final int GAP_SIZE = 40;
    private static final int SHADOW_SIZE = 30;
    private static int x = 0;
    private static int y = 0;
    
    
    private final int rules[] = {
        AlphaComposite.DST,
        AlphaComposite.DST_ATOP,
        AlphaComposite.DST_OUT,
        AlphaComposite.SRC,
        AlphaComposite.SRC_ATOP,
        AlphaComposite.SRC_OUT
    };

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.translate(30, 30);
        for (int i = 0; i < rules.length; i++) {
            
            AlphaComposite ac = AlphaComposite.getInstance(rules[i], 0.8f);
            
            
            BufferedImage buffImg = new BufferedImage(1000, 600,
                    BufferedImage.TYPE_INT_ARGB);
            
            Graphics2D gbi = buffImg.createGraphics();
            
            gbi.setPaint(Color.red);
            gbi.fillRect(i*(RECT_SIZE+GAP_SIZE), 0, RECT_SIZE, RECT_SIZE);
            
            
            gbi.setComposite(ac);
            
            gbi.setPaint(Color.black);
            gbi.fillRect(i*(RECT_SIZE+GAP_SIZE)+SHADOW_SIZE, SHADOW_SIZE, RECT_SIZE, RECT_SIZE);
            
            g2d.drawImage(buffImg, 0, 0, null);
            gbi.dispose();
        }

        g2d.dispose();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }
}

public class CompositionEx2 extends JFrame {

    public CompositionEx2() {

        add(new CompositionEx2_Panel());

        setTitle("Composition");
       setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                CompositionEx2 ex = new CompositionEx2();
                ex.setVisible(true);
            }
        });
    }
}
