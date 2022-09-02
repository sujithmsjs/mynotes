package learn.animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

public class MyDrawing04 extends JPanel {

    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 600;
    public static final int UNIT_SIZE = 10;

    public static final int CIRCLE_DIAMETER = UNIT_SIZE * 3;
    private int circleX = 0;
    private int circleY = 0;

    public MyDrawing04() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        addKeyListener(new MyKeyListner());
        setFocusable(true);
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.DARK_GRAY);
        drawGrid(g);
        drawText(g);
    }

    public void drawText(Graphics g) {
        g.drawOval(circleX, circleY, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
    }

    private void drawGrid(Graphics g) {
        //Vertical Lines
        for (int i = 0; i <= SCREEN_WIDTH / UNIT_SIZE; i++) {
            g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);

        }

        //Draw horinzoltal lines
        for (int i = 0; i <= SCREEN_HEIGHT / UNIT_SIZE; i++) {
            g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
        }
    }

    public static void main(String[] args) {
        MyFrame frame = new MyFrame("Key Listener Test");
        MyDrawing04 demo = new MyDrawing04();
        frame.add(demo);
        frame.pack();
        frame.setVisible(true);
    }

    class MyKeyListner extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e); //To change body of generated methods, choose Tools | Templates.
            switch (e.getKeyCode()) {

                case KeyEvent.VK_LEFT:
                    if (circleX > 0) {
                        circleX -= UNIT_SIZE;
                    }
                    break;

                case KeyEvent.VK_RIGHT:
                   
                    if (circleX + CIRCLE_DIAMETER < SCREEN_WIDTH) {
                        circleX += UNIT_SIZE;
                    }
                    break;

                case KeyEvent.VK_UP:
                    if (circleY > 0) {
                        circleY -= UNIT_SIZE;
                    }
                    break;

                case KeyEvent.VK_DOWN:
             
                    if (circleY + CIRCLE_DIAMETER < SCREEN_HEIGHT) {
                        circleY += UNIT_SIZE;
                    }
                    break;

            }
            repaint();
        }

    }

}
