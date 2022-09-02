
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

public class Canvas extends JPanel implements ActionListener {

    private static final int UNIT_SIZE = 20;
    private static final Dimension SCREEN_SIZE = new Dimension(900, 600);
    private Shape selectedShape;
    
    //
    
    
    //Booleans
    private boolean isShapeHolded;
    private Point shapeHoldedAt;
    
    private Timer timer;

    private Point isDragging;

    // SHAPES To be drawn on screen.
    //RectangularShape rect = new Arc2D.Double(0, 0, 200, 200, 0 ,90 , Arc2D.CHORD);
    
    RectangularShape rect = new Rectangle2D.Double(0, 0, 100, 100);
    
   // RectShape rshape = new RectShape(rect);
    
    
    
    
    
    Color rectColor = Color.RED;

    Ellipse2D ellipse = new Ellipse2D.Double(UNIT_SIZE * 2, UNIT_SIZE * 2, UNIT_SIZE * 2, UNIT_SIZE * 2);
    Color ellipseColor = Color.GREEN;
    
    Arc2D arc = new Arc2D.Double();
    
    
    Canvas() {

        //Super Settings
        setPreferredSize(SCREEN_SIZE);
        setBackground(Color.BLACK);

        //Adding Listeners.
        addMouseListener(new MyMouseListener());
        addMouseMotionListener(new MyMouseListener());

        //init Tiemr
        //timer = new Timer(2, this);
        //timer.start();
    }

    private void drawRect(Graphics2D g) {
       
        
        
        
        g.setColor(rectColor);
        g.fill(rect);
        
    }

    private void drawEllipse(Graphics2D g) {
        
        AlphaComposite reset = (AlphaComposite) g.getComposite();
        AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
        
        g.setComposite(alcom);
        
        g.setColor(ellipseColor);
        g.fill(ellipse);
        
        g.setComposite(reset);
    }

    private void drawShapes(Graphics2D g) {
        drawRect(g);
        drawEllipse(g);
        drawSelectedShapeFrame(g);
    }

    private void drawSelectedShapeFrame(Graphics2D g) {
        if (selectedShape != null) {
            g.setColor(Color.white);
            g.draw(selectedShape.getBounds2D());
        }
    }

    class MyMouseListener extends MouseAdapter implements MouseMotionListener {

        @Override
        public void mouseMoved(MouseEvent e) {
            if (rect.contains(e.getX(), e.getY())) {
                setCursor(new Cursor(Cursor.MOVE_CURSOR));
            } else if (ellipse.contains(e.getX(), e.getY())) {
                setCursor(new Cursor(Cursor.MOVE_CURSOR));
            } else {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            int clickCount = e.getClickCount();
            
            if (clickCount > 1 && rect.contains(e.getX(), e.getY())) {
                rectColor = JColorChooser.showDialog(null, "ChooseColor", Color.BLACK);
                repaint();
            }
            if (clickCount > 1 && ellipse.contains(e.getX(), e.getY())) {
                ellipseColor = JColorChooser.showDialog(null, "ChooseColor", Color.BLACK);
                repaint();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

            if (rect.contains(e.getX(), e.getY())) {
                selectedShape = rect;
                isShapeHolded = true;
                shapeHoldedAt = e.getPoint();
                
            } else if (ellipse.contains(e.getX(), e.getY())) {
                selectedShape = ellipse;
                isShapeHolded = true;
                
            } else {
                System.out.println("Set to null");
                selectedShape = null;
                isShapeHolded = false;
            }
            
            repaint();

        }
        
        @Override
        public void mouseReleased(MouseEvent e){
            
            isShapeHolded = false;
            
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            
            isDragging = e.getPoint();

            if (isShapeHolded&&selectedShape.equals(rect)) {
                rect.setFrame(e.getX() - rect.getWidth() / 2, e.getY() - rect.getHeight() / 2, rect.getWidth(), rect.getHeight());     
                repaint();
            }
            
            if (isShapeHolded&&selectedShape.equals(ellipse)) {
                ellipse.setFrame(e.getX() - ellipse.getWidth() / 2, e.getY() - ellipse.getHeight() / 2, ellipse.getWidth(), ellipse.getHeight());
                repaint();
            }
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
        Canvas canvas = new Canvas();
        MyFrame frame = new MyFrame(canvas, "Canvas Paint");
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
