//<editor-fold defaultstate="collapsed" desc="Imports">

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.Timer;
//</editor-fold>

public class SimplePaint extends JPanel implements ActionListener {

    Path2D path;

    MyPath mypath = new MyPath();

    ArrayList<MyPath> al = new ArrayList<>();

    boolean isPaintingStarted = false;
    boolean mouseDragging = false;
    boolean mousePressed = false;
    boolean mouseReleased = false;

    Point lastPressedAt;
    Point mouseMouedTo = new Point(0, 0);

    final Dimension SCREEN_SIZE = new Dimension(1200, 800);
    final int STROKE_WIDTH = 20; // Dot diameter.
    final int UNIT_SIZE = 60;
    Timer timer;
    private double theta = 0;
    private int DELAY = 50;

    public SimplePaint() {

        setBackground(Color.YELLOW);
        setPreferredSize(SCREEN_SIZE);
        addMouseListener(new ML());
        addMouseMotionListener(new ML());
        setFocusable(true);
        addKeyListener(new KL());
        //  timer = new Timer(DELAY,this);
        // timer.start();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D) g.create();
        drawBG(g2d);
        doDraw(g2d);
    }

    public void doDraw(Graphics2D g) {

        Rectangle2D rect = getPointRect();

        g.setColor(Color.RED);

        // Printing all Paths.
        for (MyPath myPath : al) {
            myPath.draw(g);
        }

        mypath.draw(g);

        if (isPaintingStarted) {

            g.drawLine(lastPressedAt.x, lastPressedAt.y, mouseMouedTo.x, mouseMouedTo.y);
            mypath = new MyPath();

        }

    }

    private Rectangle2D getPointRect() {
        int xLeftGap = mouseMouedTo.x % UNIT_SIZE; //Before Gap
        int xRightGap = UNIT_SIZE - xLeftGap;
        int x = (xLeftGap < xRightGap) ? mouseMouedTo.x - xLeftGap : mouseMouedTo.x + xRightGap;
        //    int x = mouseMouedTo.x - xS; 
        //   int x2 = UNIT_SIZE-x; 
        int yTopGap = mouseMouedTo.y % UNIT_SIZE; //Before Gap
        int yBottomGap = UNIT_SIZE - yTopGap;
        int y = (yTopGap < yBottomGap) ? mouseMouedTo.y - yTopGap : mouseMouedTo.y + yBottomGap;

        return new Rectangle2D.Double(x - 10, y - 10, 20, 20);
    }

    private Point getExactPoint(Point p) {

        int xLeftGap = p.x % UNIT_SIZE; //Before Gap
        int xRightGap = UNIT_SIZE - xLeftGap;
        int x = (xLeftGap < xRightGap) ? p.x - xLeftGap : p.x + xRightGap;
        //    int x = mouseMouedTo.x - xS; 
        //   int x2 = UNIT_SIZE-x; 
        int yTopGap = p.y % UNIT_SIZE; //Before Gap
        int yBottomGap = UNIT_SIZE - yTopGap;
        int y = (yTopGap < yBottomGap) ? p.y - yTopGap : p.y + yBottomGap;

        return new Point(x, y);
    }

    //<editor-fold defaultstate="collapsed" desc="draw BG">
    private void drawBG(Graphics2D g) {
      //  g.setPaint(Color.BLACK);
       // g.fillRect(0, 0, SCREEN_SIZE.width, SCREEN_SIZE.height);

        g.setColor(Color.darkGray);
        for (int i = 0; i < SCREEN_SIZE.width / UNIT_SIZE; i++) {
            g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_SIZE.height);
        }

        for (int i = 0; i < SCREEN_SIZE.height / UNIT_SIZE; i++) {
            g.drawLine(0, i * UNIT_SIZE, SCREEN_SIZE.width, i * UNIT_SIZE);
        }

        
        
        g.setPaint(Color.YELLOW);
        g.fill(getPointRect());
        
//        g.setColor(Color.RED);
//        for (int i = 0; i <= SCREEN_SIZE.width / UNIT_SIZE; i++) {
//           x+(UNIT_SIZE)*i
//            
//            g.drawRect(, y, RECT, RECT);
//            
//            
//            
//            for (int i = 0; i <= SCREEN_SIZE.height / UNIT_SIZE; i++) {
//                g.drawRect(x, y+(UNIT_SIZE)*i, RECT, RECT);
//            }
//            
//            
//        }
        
        
        
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="draw GeneralPath2">
    private void drawGeneralPath2(Graphics2D g) {
        path = new Path2D.Double();
        path.moveTo(0, 0);

        path.lineTo(100, 300);
        path.lineTo(450, 320);
        path.lineTo(500, 200);
        path.closePath();

        Rectangle2D re = path.getBounds2D();

        g.setColor(Color.BLUE);
        g.fill(path);
        g.draw(re);
    }
//</editor-fold>

    public void actionPerformed(ActionEvent e) {

    }

    class ML extends MouseAdapter implements MouseMotionListener {

        @Override
        public void mouseMoved(MouseEvent e) {
            mouseMouedTo = e.getPoint();
            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            mouseDragging = true;

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            mousePressed = false;
            mouseDragging = false;
        }

        @Override
        public void mousePressed(MouseEvent e) {

            mousePressed = true;

            if (!isPaintingStarted) {

                isPaintingStarted = true;
                mypath = new MyPath();
                JOptionPane.showMessageDialog(null, "New Paint Started.");
            }

            //Paint started at
            lastPressedAt = getExactPoint(e.getPoint());

            //Added to points.
            mypath.points.add(lastPressedAt);

            //Save the Path.
            if (mypath.points.size() > 2 && mypath.points.get(0).equals(lastPressedAt)) {

                mypath.path.closePath();

                al.add(mypath);
                System.out.println(al);
                JOptionPane.showMessageDialog(null, "Path Saved.");

                resetPaint();

            }

            System.out.println(mypath.points + "; Len: " + mypath.points.size());
            repaint();

        }

        private void resetPaint() {

            lastPressedAt = null;
            isPaintingStarted = false;
            mypath = null;
            JOptionPane.showMessageDialog(null, "Resisted.");

        }

    }

    class KL extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (isPaintingStarted) {

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    mypath.path.reset();
                    mypath.points.clear();
                    isPaintingStarted = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    mypath.path.closePath();
                    isPaintingStarted = false;
                }
            }
        }

    }

    public static void main(String[] args) {
        SimplePaint t = new SimplePaint();
        MyFrame frame = new MyFrame(t, "SimplePaint");
        frame.add(t);
        frame.setBackground(Color.BLACK);
        frame.setVisible(true);
    }

}
