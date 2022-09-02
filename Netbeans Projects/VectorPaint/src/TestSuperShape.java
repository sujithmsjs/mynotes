
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

public class TestSuperShape extends JPanel {

    private static final int UNIT_SIZE = 30;
    private static final Dimension SCREEN_SIZE = new Dimension(900, 600);
    

    private ArrayList<MShape> shapes;

    TestSuperShape() {

        //Super Settings
        setPreferredSize(SCREEN_SIZE);
        setBackground(Color.BLACK);

        loadRects();
    }

    private void loadRects() {
        Random random = new Random();

         shapes = new ArrayList<>();
         
        Path2D path = new Path2D.Double();
        path.moveTo(0, 0);
        path.lineTo(300, 0);
        path.lineTo(150, 150);
        path.closePath();
        
        Line2D line = new Line2D.Double(0, 0, 300, 300);

        Rectangle2D rect = new Rectangle.Double(0, 0, 300, 300);
        
        shapes.add(new MShape(line));
        shapes.add(new MShape(rect));
        shapes.add(new MShape(path));
        
        shapes.get(2).setFrame(0, 400, 300, 300);
    }

    private void drawRect(Graphics2D g) {
        g.setColor(Color.yellow);
        for (MShape shape : shapes) {
            g.draw(shape);
        }

    }

    private void drawShapes(Graphics2D g) {
        drawRect(g);
    }

//<editor-fold defaultstate="collapsed" desc="Paint Component Method">
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.

        Graphics2D g2d = (Graphics2D) g;

        //To Draw Background Grid and Got.
      //  drawBackground(g2d);

        //Sprite Drawing.
        drawShapes(g2d);
    }

//</editor-fold>    
//<editor-fold defaultstate="collapsed" desc="Draw Background">
    private void drawBackground(Graphics2D g) {
        //Draw row grid
        drawGridLines(g);

        //Draw Dots
        drawDots(g);

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Main Method">
    public static void main(String[] args) {
        TestSuperShape canvas = new TestSuperShape();
        MyFrame frame = new MyFrame(canvas, "TestSuperShape Paint");
        frame.setVisible(true);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Grid Lines">

    private void drawGridLines(Graphics2D g) {
        g.setColor(Color.darkGray);
        for (int i = 0; i < SCREEN_SIZE.width / UNIT_SIZE; i++) {
            g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_SIZE.height);
        }

        for (int i = 0; i < SCREEN_SIZE.height / UNIT_SIZE; i++) {
            g.drawLine(0, i * UNIT_SIZE, SCREEN_SIZE.width, i * UNIT_SIZE);
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Draw Dots">

    private void drawDots(Graphics2D g) {

        final int CS = UNIT_SIZE / 4;

        int xS = -CS / 2;
        int yS = -CS / 2;

        int xPoint = SCREEN_SIZE.width / UNIT_SIZE;
        int yPoint = SCREEN_SIZE.width / UNIT_SIZE;

        for (int i = 0; i <= xPoint; i++) {
            int x = xS + (i * UNIT_SIZE);

            for (int j = 0; j <= yPoint; j++) {
                int y = yS + (j * UNIT_SIZE);
                Rectangle2D rect = new Rectangle2D.Double(x, y, CS, CS);
                g.fill(rect);
            }
        }
    }

//</editor-fold>
}
