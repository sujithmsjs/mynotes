package learn.animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JPanel;
import learn.animation.paint.shapes.Circle;

public class MyDrawing03 extends JPanel {

    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 600;
    public static final int UNIT_SIZE = 50;



    public MyDrawing03() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        Point p = new Point(SCREEN_WIDTH/2,SCREEN_HEIGHT/2);

        
        
    }
    
   

    public void paint(Graphics g) {
        super.paintComponent(g);
        //GrawGrid
        drawCircle(g);
        g.setColor(Color.DARK_GRAY);
      //  drawGrid(g);
    }  
    
    private void drawCircle(Graphics g){
//        g.setColor(Color.yellow);
//        Circle c = new Circle(new Point(250,250),100);
//        c.draw(g);
//        Circle c2 = new Circle(0,250,50);
//        c2.draw(g);
//        c.drawLine(c2,g);
//        
//        Circle c3 = new Circle(0,0,150);
//        c3.draw(g);
//        c.drawLine(c3, g);
//        
//        c.draw(g);
    }
    
    
    

    private void drawGrid(Graphics g) {
        //Vertical Lines
        for (int i = 0; i <= SCREEN_WIDTH / UNIT_SIZE; i++) {
            g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
            System.out.println(SCREEN_WIDTH / UNIT_SIZE);
        }

        //Draw horinzoltal lines
        for (int i = 0; i <= SCREEN_HEIGHT / UNIT_SIZE; i++) {
            g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
        }
    }

    public static void main(String[] args) {
        MyFrame frame = new MyFrame("Key Listener Test");
        MyDrawing03 demo = new MyDrawing03();
        frame.add(demo);
        frame.pack();
        frame.setVisible(true);
    }


}
