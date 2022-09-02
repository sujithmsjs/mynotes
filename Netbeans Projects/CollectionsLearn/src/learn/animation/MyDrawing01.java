package learn.animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class MyDrawing01 extends JPanel {

    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 600;
    public static final int UNIT_SIZE = 60;

    public MyDrawing01() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        //GrawGrid
        drawGrid(g);

    }

    public static void main(String[] args) {
        MyFrame frame = new MyFrame("Key Listener Test");
        MyDrawing01 demo = new MyDrawing01();
        frame.add(demo);
        frame.pack();
        frame.setVisible(true);

    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.red);

        g.drawLine(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        g.drawLine(SCREEN_WIDTH, 0, 0, SCREEN_HEIGHT);

        //Outer circle
        g.drawOval(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        g.setColor(Color.WHITE);
        for (int i = 0; 0 < SCREEN_WIDTH - UNIT_SIZE * i * 2; i++) {
            g.drawRect(UNIT_SIZE * i, UNIT_SIZE * i, SCREEN_WIDTH - UNIT_SIZE * i * 2, SCREEN_HEIGHT - UNIT_SIZE * i * 2);
            System.out.println(SCREEN_WIDTH - UNIT_SIZE * i * 2);
        }

        g.setColor(Color.WHITE);
        for (int i = 0; 0 < SCREEN_WIDTH - UNIT_SIZE * i * 2; i++) {
            g.drawOval(UNIT_SIZE * i, UNIT_SIZE * i, SCREEN_WIDTH - UNIT_SIZE * i * 2, SCREEN_HEIGHT - UNIT_SIZE * i * 2);
            System.out.println(SCREEN_WIDTH - UNIT_SIZE * i * 2);
        }

//        g.drawRect(UNIT_SIZE * 1, UNIT_SIZE * 1, SCREEN_WIDTH - UNIT_SIZE * 2, SCREEN_HEIGHT - UNIT_SIZE * 2);
//        g.drawRect(UNIT_SIZE * 2, UNIT_SIZE * 2, SCREEN_WIDTH - UNIT_SIZE * 4, SCREEN_HEIGHT - UNIT_SIZE * 4);
        g.setColor(Color.red);

        //Verticle lines
        for (int i = 0; i < SCREEN_WIDTH; i++) {
            g.drawLine(0, i * UNIT_SIZE, SCREEN_HEIGHT, i * UNIT_SIZE);
        }

        //Horizontal lines
        for (int i = 0; i < SCREEN_HEIGHT; i++) {
            g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_WIDTH);
        }
    }

}
