package com.zetcode;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

class FadeOutEx_Panel extends JPanel
        implements ActionListener {

    private Image img;
    private Timer timer;
    private float alpha = 1f;
    private float fade = 0.01f;

    private final int DELAY = 40;
    private final int INITIAL_DELAY = 500;

    public FadeOutEx_Panel() {

        loadImage();
        setFadeOutEx_PanelSize();
        initTimer();
    }

    private void loadImage() {
        String imageLoc = "C:\\Users\\sujit\\OneDrive\\Documents\\NetBeansProjects\\CollectionsLearn\\src\\com\\zetcode\\wolvorine.jpeg";
        img = new ImageIcon(imageLoc).getImage();
    }

    private void setFadeOutEx_PanelSize() {

        int h = img.getHeight(this);
        int w = img.getWidth(this);
        setPreferredSize(new Dimension(w, h));
    }

    private void initTimer() {

        timer = new Timer(DELAY, this);
        timer.setInitialDelay(INITIAL_DELAY);
        timer.start();
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g.create();

        AlphaComposite acomp = AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, alpha);
        System.out.println(alpha);
        g2d.setComposite(acomp);
        g2d.drawImage(img, 0, 0, null);

        g2d.dispose();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    private void step() {
        if (alpha <= 0) {
            fade = 0.01f;
        }else if( alpha >= 1.0f){
            fade = -0.01f;
        }
        alpha += fade;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        step();
        repaint();
    }
}

public class FadeOutEx extends JFrame {

    public FadeOutEx() {

        initUI();
    }

    private void initUI() {

        add(new FadeOutEx_Panel());

        pack();

        setTitle("Fade out");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                FadeOutEx ex = new FadeOutEx();
                ex.setVisible(true);
            }
        });
    }
}
