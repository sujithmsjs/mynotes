package com.zetcode.fonts;

import com.zetcode.fonts.AllFontsEx;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;
import learn.animation.util.BGUtil;
import learn.timers.RealTimer;

class SoulmateEx_Panel extends JPanel implements Runnable{

    private final RealTimer timer;
    private final Font[] fonts;
    private final int TOTAL_FONTS;
    private final int LINE_SPACE =50;
    
    private int index;

    public SoulmateEx_Panel() {
        timer = new RealTimer(1000, this);
        index = 0;
        fonts =AllFontsEx.fonts;
        TOTAL_FONTS = fonts.length;
        setBackground(Color.black);
        timer.start();
    }
    
    
    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh
                = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);
        
        g2d.setPaint(BGUtil.g5);

        g2d.setFont(new Font(AllFontsEx.fonts[index].getName(), Font.PLAIN, 40));

        g2d.drawString("Most relationships seem so transitory", 20, LINE_SPACE*1);
        g2d.drawString("They're all good but not the permanent one", 20, LINE_SPACE*2);
        g2d.drawString("Who doesn't long for someone to hold", 20, LINE_SPACE*3);
        g2d.drawString("Who knows how to love you without being told", 20, LINE_SPACE*4);
        g2d.drawString("Somebody tell me why I'm on my own", 20, LINE_SPACE*5);
        g2d.drawString("If there's a soulmate for everyone", 20, LINE_SPACE*6);
        g2d.drawString(AllFontsEx.fonts[index].getName(), 20, LINE_SPACE*7);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public void run() {
        
        index = (index + 1) % TOTAL_FONTS;
        
        repaint();
    }
}

public class SoulmateEx extends JFrame {

    public SoulmateEx() {

        initUI();
    }

    private void initUI() {

        setTitle("Soulmate");

        add(new SoulmateEx_Panel());

        setSize(420, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                SoulmateEx ex = new SoulmateEx();
                ex.setVisible(true);
            }
        });
    }
}
