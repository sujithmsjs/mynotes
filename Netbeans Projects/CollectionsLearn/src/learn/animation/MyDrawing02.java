package learn.animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class MyDrawing02 extends JPanel {

    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 600;
    public static final int UNIT_SIZE = 60;

    public MyDrawing02() {
        setBackground(Color.BLACK);
        addMouseListener(new MyMouseListener());
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        //GrawGrid
        drawGrid(g);

    }

    

    private void drawGrid(Graphics g) {
        //Vertical Lines
        for (int i = 0; i < SCREEN_WIDTH/UNIT_SIZE; i++) {
            g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
            System.out.println(SCREEN_WIDTH/UNIT_SIZE);
        }
        
        //Draw horinzoltal lines
        for (int i = 0; i < SCREEN_HEIGHT/UNIT_SIZE; i++) {
            g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
        }
        
        //Draw horinzoltal lines
        for (int i = 0; i <= SCREEN_HEIGHT/UNIT_SIZE; i++) {
            g.drawLine(0, i*UNIT_SIZE, i*UNIT_SIZE,0);
            System.out.println(i*UNIT_SIZE);
        }
    }
    
    
    public static void main(String[] args) {
        MyFrame frame = new MyFrame("Key Listener Test");
        MyDrawing02 demo = new MyDrawing02();
        frame.add(demo);
        frame.pack();
        frame.setVisible(true);
    }
    
    class MyMouseListener extends MouseAdapter{

        @Override
        public void mouseMoved(MouseEvent e) {
            Point point = e.getPoint();
            System.out.println(point);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            super.mouseEntered(e); //To change body of generated methods, choose Tools | Templates.
        }
        
    }

}
