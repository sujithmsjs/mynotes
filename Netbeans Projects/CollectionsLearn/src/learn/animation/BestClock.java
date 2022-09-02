package learn.animation;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import learn.animation.paint.shapes.Circle;

public class BestClock extends JPanel {

    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 600;
    public static final int UNIT_SIZE = 60;


    int angle = 0;
    
    
    public BestClock() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        addKeyListener(new MyKeyListner());
        setFocusable(true);
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.DARK_GRAY);
       //  drawGrid(g);
         
         draw(g);

    }
    
    public void draw(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4));
        
        Point p = new Point(SCREEN_WIDTH/2,SCREEN_HEIGHT/2);
        
        Circle c1 = new Circle("",p,180);
        Circle c2 = new Circle("",p,200);
        Circle c3 = new Circle("",p,190);
        Circle c4 = new Circle("",p,170);
        
        c1.fill(new Color(0, 46, 71),  g);
        
        c1.draw(g2d,Color.darkGray,new BasicStroke(1));
        c2.draw(g2d,Color.darkGray,new BasicStroke(5));
        c3.draw(g2d,Color.darkGray,new BasicStroke(3));
        
      // c4.drawRadius(g2d, angle,Color.WHITE,new BasicStroke(4, BasicStroke.CAP_ROUND,BasicStroke.CAP_ROUND));
       c4.drawRadius(g2d, 0,Color.WHITE,new BasicStroke(4, BasicStroke.CAP_ROUND,BasicStroke.CAP_ROUND));
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
        BestClock bc = new BestClock();
        JFrame frame = new JFrame("Key Listener Test");
        frame.add(bc);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    class MyKeyListner extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e); //To change body of generated methods, choose Tools | Templates.
           

            switch (e.getKeyCode()) {

                case KeyEvent.VK_UP:
                   
                    angle += 1;
                    break;

                case KeyEvent.VK_DOWN:
                   
                    angle -= 1;
                    break;
            }
            //angle = Math.toRadians(60);

            //   lineY = (int) Math.round(Math.sin(angle) * CIRCLE_DIAMETER / 2);
            //   lineX = (int) Math.round(Math.cos(angle) * CIRCLE_DIAMETER / 2);
            repaint();
        }

    }

}
