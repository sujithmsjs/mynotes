
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

public class TestResize extends JPanel implements ActionListener {

    private static final int UNIT_SIZE = 30;
    private static final Dimension SCREEN_SIZE = new Dimension(900, 600);
    Path2D path;
    
    
    private ArrayList<MShape> shapes;


    TestResize() {

        //Super Settings
        setPreferredSize(SCREEN_SIZE);
        setBackground(Color.BLACK);

        //Adding Listeners.
        addMouseListener(new MyMouseListener());
        addMouseMotionListener(new MyMouseListener());
        loadRects();
        //init Tiemr
        //timer = new Timer(2, this);
        //timer.start();
    }

    private void loadRects() {
        Random random = new Random();

            shapes = new ArrayList<>();

            
            path = new Path2D.Double();
            path.moveTo(0, 250);
            path.lineTo(300, 250);
            path.lineTo(150, 400);
            path.closePath();
   
            Rectangle2D rect = new Rectangle.Double(0, 0, 100, 100);
            
           shapes.add(new MShape(rect,this));
           shapes.add(new MShape(path,this)); 
           
    }

    private void drawRect(Graphics2D g) {

        for (MShape shape : shapes) {
            g.draw(shape);
        }
        
      //  g.setTransform(at);
        g.fill(path);
        g.setColor(Color.YELLOW);
        g.draw(path.getBounds2D());
    }

    private void drawShapes(Graphics2D g) {
        drawRect(g);
    }

    class MyMouseListener extends MouseAdapter implements MouseMotionListener {

        private boolean isResizingXY;
        private boolean isResizingWH;

        @Override
        public void mouseMoved(MouseEvent e) {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            
        }

    }

//<editor-fold defaultstate="collapsed" desc="Paint Component Method">
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.

        Graphics2D g2d = (Graphics2D) g;

        //To Draw Background Grid and Got.
        drawBackground(g2d);

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
        TestResize canvas = new TestResize();
        MyFrame frame = new MyFrame(canvas, "TestResize Paint");
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
    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
