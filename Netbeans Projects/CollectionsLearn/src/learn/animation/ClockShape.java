package learn.animation;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

public class ClockShape extends JPanel {

    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 600;
    public static final int UNIT_SIZE = 60;
    public static final int CLOCK_SIZE = 3;
    
    public static final int CIRCLE_DIAMETER = SCREEN_WIDTH - UNIT_SIZE * CLOCK_SIZE;

    public static final int HAND_SIZE = 60;

    private int circleX = UNIT_SIZE * CLOCK_SIZE / 2;
    private int circleY = UNIT_SIZE * CLOCK_SIZE / 2;

    private int lineX;
    private int lineY;
    private double angle = 0;
    private static final int ANGLE_INC = 1;

    public ClockShape() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        addKeyListener(new MyKeyListner());
        setFocusable(true);
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.DARK_GRAY);
        // drawGrid(g);
        drawClock(g);
        drawHand(g);
    }

    public void drawClock(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4));
        g.drawOval(circleX, circleY, CIRCLE_DIAMETER, CIRCLE_DIAMETER);

        int x = circleX + CIRCLE_DIAMETER / 2;
        int y = circleY + CIRCLE_DIAMETER / 2;

        //  g.drawLine(x, y, lineX, lineY);
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

    private void drawHand(Graphics g) {
        int x, y;
        int radius = CIRCLE_DIAMETER / 2;
        double rad = Math.toRadians(angle);

        x = (int) (radius * Math.cos(rad));
        y = (int) (radius * Math.sin(rad));

        g.setColor(Color.red);
        g.drawLine(circleX + CIRCLE_DIAMETER / 2, circleY + CIRCLE_DIAMETER / 2, circleX + CIRCLE_DIAMETER / 2 + x, circleX + CIRCLE_DIAMETER / 2 + y);

    }

    public static void main(String[] args) {
        MyFrame frame = new MyFrame("Key Listener Test");
        ClockShape demo = new ClockShape();
        frame.add(demo);
        frame.pack();
        frame.setVisible(true);
    }

    class MyKeyListner extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e); //To change body of generated methods, choose Tools | Templates.
            System.out.println("Key pressed");

            switch (e.getKeyCode()) {

                case KeyEvent.VK_UP:
                    System.out.println("Up pressed");
                    angle += ANGLE_INC;
                    break;

                case KeyEvent.VK_DOWN:
                    System.out.println("Down pressed");
                    angle -= ANGLE_INC;
                    break;
            }
            //angle = Math.toRadians(60);

            //   lineY = (int) Math.round(Math.sin(angle) * CIRCLE_DIAMETER / 2);
            //   lineX = (int) Math.round(Math.cos(angle) * CIRCLE_DIAMETER / 2);
            repaint();
        }

    }

}
