
import com.sun.glass.ui.Cursor;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import javax.swing.JPanel;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

public class Tutorial extends JPanel implements ActionListener {

    final Dimension SCREEN_SIZE = new Dimension(600, 400);
    final int STROKE_WIDTH = 20; // Dot diameter.
    final int UNIT_SIZE = 30;
    Timer timer;
    private double theta = 0;
    private int DELAY = 50;
    Rectangle2D rect = new Rectangle2D.Double(0, 0, 60, 60);
    
    
    private final int rules[] = {
        AlphaComposite.DST,
        AlphaComposite.DST_ATOP,
        AlphaComposite.DST_OUT,
        AlphaComposite.SRC,
        AlphaComposite.SRC_ATOP,
        AlphaComposite.SRC_OUT
    };

    Point2D point1 = new Point2D.Double(50, 50);
    Point2D point2 = new Point2D.Double(600, 600);
    Line2D line = new Line2D.Double(point1, point2);

    public Tutorial() {
        setBackground(Color.YELLOW);
        setPreferredSize(SCREEN_SIZE);
    //    addMouseListener(new ML());
    //    addMouseMotionListener(new ML());
        //  timer = new Timer(DELAY,this);
        // timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);

        Graphics2D g2d = (Graphics2D) g.create();
        drawBG(g2d, Color.BLACK);

       drawAffineTransfor(g2d);
       drawGeneralPath2(g2d);
       
    } 

    private void drawGeneralPath2(Graphics2D g) {
        Path2D path = new Path2D.Double();
        
        path.moveTo(0, 400);
        path.lineTo(100, 600);
        path.lineTo(200, 400);
        path.lineTo(100, 400);
        path.closePath();
        g.setColor(Color.BLUE);
        g.fill(path);
    }
    
    
    private void drawAffineTransfor(Graphics2D g) {
        
        g.setPaint(Color.YELLOW);
        g.fill(rect);
        
        AffineTransform reset = g.getTransform();

        //Green box moved and resized.
        AffineTransform tx1 = new AffineTransform();
        tx1.translate(100, 60);
        tx1.scale(2, 2);
        tx1.rotate(Math.PI/4);
        tx1.shear(0.3, 0.3);
        g.setTransform(tx1);
        g.setPaint(Color.GREEN);
        g.fill(rect);
        g.setTransform(reset);
        g.fill(new Ellipse2D.Double(0, UNIT_SIZE*4, UNIT_SIZE, UNIT_SIZE));
        

    }

//<editor-fold defaultstate="collapsed" desc="draw Rotate">
    private void drawRotate(Graphics2D g) {
        int w = 100;
        int h = 100;
        int x = SCREEN_SIZE.width / 2;
        int y = SCREEN_SIZE.height / 2 - h / 2;

        BufferedImage img = new BufferedImage(SCREEN_SIZE.width, SCREEN_SIZE.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D imgG = (Graphics2D) img.getGraphics();

        Rectangle2D rect = new Rectangle2D.Double(x, y, w, h);
        imgG.setPaint(Color.RED);

        imgG.rotate(theta, x, y);
        imgG.fill(rect);
        g.drawImage(img, 0, 0, this);

        for (int i = 0; i < 360; i += 15) {
            double radian = Math.toRadians(i);
            g.rotate(radian, x, y);
            g.setPaint(new Color(Color.HSBtoRGB(i / 360f, 1f, 1f)));
            g.fill(rect);
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException ex) {

            }
        }

    }

//</editor-fold>    
//<editor-fold defaultstate="collapsed" desc="draw Transform">
    private void drawTransform(Graphics2D g) {
        g.setPaint(Color.YELLOW);
        g.fillRect((SCREEN_SIZE.width / 2) - (UNIT_SIZE * 2), (SCREEN_SIZE.height / 2) - (UNIT_SIZE * 2), UNIT_SIZE * 4, UNIT_SIZE * 4);
        g.translate(SCREEN_SIZE.width / 2, SCREEN_SIZE.height / 2);
        g.setPaint(Color.BLACK);
        g.fill(new Rectangle2D.Double(0, 0, 50, 50));

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="draw BG">

    private void drawBG(Graphics2D g, Color color) {
        g.setPaint(color);
        g.fillRect(0, 0, SCREEN_SIZE.width, SCREEN_SIZE.height);
        
        g.setColor(Color.darkGray);
           for(int i= 0;i< SCREEN_SIZE.width/UNIT_SIZE ;i++){
            g.drawLine( i*UNIT_SIZE, 0 , i*UNIT_SIZE, SCREEN_SIZE.height);
        }
           
        for (int i = 0; i < SCREEN_SIZE.height / UNIT_SIZE; i++) {
            g.drawLine(0, i * UNIT_SIZE, SCREEN_SIZE.width, i * UNIT_SIZE);
        }
        
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="draw Chipping">

    private void drawChipping(Graphics2D g2) {
        Area a1 = new Area(new Rectangle2D.Double(50, 50, 200, 200));
        BufferedImage img = new BufferedImage(SCREEN_SIZE.width / 2, SCREEN_SIZE.height / 2, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) img.getGraphics();
        g.setPaint(Color.RED);
        g.fill(a1);
        g.clip(a1);
        g.setPaint(Color.BLUE);
        Area a2 = new Area(new Ellipse2D.Double(100, 100, 200, 200));
        g.fill(a2);

        drawBG(g2, Color.YELLOW);
        g2.setColor(Color.black);
        g2.drawImage(img, 150, 150, 100, 100, this);
        g2.drawLine(150, 0, 150, SCREEN_SIZE.width);
        g2.drawLine(0, 150, 0, SCREEN_SIZE.height);

        g2.drawRect(150, 150, 100, 100);
        g2.setColor(Color.YELLOW);
        g2.fill(new Ellipse2D.Double(0, 0, 100, 100));
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Draw GeneralPath">

    private void drawGeneralPath(Graphics2D g) {
        GeneralPath path = new GeneralPath();
        path.moveTo(10, 10);
        path.lineTo(300, 300);
        path.lineTo(300, 10);
        path.lineTo(10, 300);
        path.closePath();

        Stroke stroke = new BasicStroke(STROKE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, STROKE_WIDTH / 2 / 2);

        g.setColor(Color.WHITE);
        g.setStroke(stroke);
        g.draw(path);

        g.setPaint(Color.RED);
        g.fill(path);
    }

//</editor-fold> 
//<editor-fold defaultstate="collapsed" desc="Draw Path2D">
    private void drawPath2D(Graphics2D g) {
        Path2D path = new Path2D.Double();
        path.moveTo(10, 10);
        path.lineTo(300, 300);
        path.lineTo(300, 10);
        path.lineTo(10, 300);
        path.closePath();

        Stroke stroke = new BasicStroke(STROKE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

        g.setColor(Color.WHITE);
        g.setStroke(stroke);
        g.draw(path);

        g.setPaint(Color.RED);
        g.fill(path);
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Draw Composite">
    private void drawComposite(Graphics2D g) {
        Rectangle2D rect1 = new Rectangle2D.Double(50, 50, 200, 200);
        Rectangle2D rect2 = new Rectangle2D.Double(125, 125, 200, 200);
        Rectangle2D rect3 = new Rectangle2D.Double(200, 200, 200, 200);

        float alpha = 80 / 100f;
        System.out.println(alpha);

        AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OUT, alpha);

        BufferedImage buffImg = new BufferedImage(SCREEN_SIZE.width, SCREEN_SIZE.height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D gbi = buffImg.createGraphics();

        gbi.setPaint(Color.blue);
        gbi.fill(rect1);

        gbi.setComposite(alcom);

        gbi.setPaint(Color.green);
        gbi.fill(rect2);

        g.drawImage(buffImg, 0, 0, this);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Draw Areas">

    private void drawAreas(Graphics2D g) {
        Area a1 = new Area(new Rectangle2D.Double(0, 0, 200, 200));
        g.setPaint(Color.RED);
        // g.fill(a1);

        Area a2 = new Area(new Ellipse2D.Double(100, 100, 200, 200));
        //  g.fill(a2);

        //a1.add(a2);
        // a1.subtract(a2);
        a1.intersect(a2);
        Stroke stroke = new BasicStroke(STROKE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        g.setStroke(stroke);
        g.fill(a1);
        g.setPaint(Color.YELLOW);
        g.draw(a1);

        Stroke stroke2 = new BasicStroke();
        g.setStroke(stroke2);
        g.setPaint(Color.BLACK);
        Rectangle2D rect = a1.getBounds2D();

        g.draw(rect);

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Draw Strokes">
    private void drawStrokes(Graphics2D g) {
        Stroke stroke = new BasicStroke(STROKE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

        g.setStroke(stroke);
        //g2d.setPaint(Color.BLACK);

        Line2D line1 = new Line2D.Double(50, 50, 50, 50);
        Line2D line2 = new Line2D.Double(50, 50, SCREEN_SIZE.width - (STROKE_WIDTH / 2), SCREEN_SIZE.height - (STROKE_WIDTH / 2));

        Area area1 = new Area(line1);
        Area area2 = new Area(line2);

        area1.intersect(area2);

        g.fill(area2);
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Main Method">
    public static void main(String[] args) {
        Tutorial t = new Tutorial();
        MyFrame frame = new MyFrame(t, "Tutorial");
        frame.add(t);
        frame.setBackground(Color.BLACK);
        frame.setVisible(true);
    }
//</editor-fold>   

    @Override
    public void actionPerformed(ActionEvent e) {
        theta = (theta + 0.25) % 360;
        repaint();
    }

    
    
    class ML extends MouseAdapter implements MouseMotionListener{

        @Override
        public void mouseMoved(MouseEvent e) {
            if(rect.contains(e.getPoint().x,e.getPoint().x)){
               // setCursor(Cursor.CURSOR_MOVE);
                System.out.println("Mouse reached");
            }else{
                System.out.println("Exited");
//                setCursor(Cursor.CURSOR_DEFAULT);
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e); //To change body of generated methods, choose Tools | Templates.
        }
        
    }

}
