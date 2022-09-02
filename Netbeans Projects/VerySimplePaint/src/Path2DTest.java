
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class Path2DTest extends Canvas {

    Rectangle2D rect = new Rectangle2D.Double(0, 0, 1, 2);
    Path2D path = new Path2D.Double();
   // AffineTransform at = new AffineTransform();
    Shape shape;
    Shape shape2;
    Path2D path2;

    public Path2DTest() {
        setFocusable(true);
        addMouseMotionListener(new ML());
        addMouseListener(new ML());
        addKeyListener(new KL());
        setPath();

    }

    private void setPath() {
        path.moveTo(0, 0);
        path.lineTo(40,0);
        path.lineTo(20,20);
        
    
        path.closePath();

          AffineTransform at = new AffineTransform();
        Rectangle2D r = new Rectangle2D.Double(0, 0, 100, 100);
        
        shape2 = getShape(r, 0, 0, 100, 200, 45);
        
       // at.rotate(Math.toRadians(45),(shape2.getBounds2D().getWidth()/2)+shape2.getBounds2D().getX(),(shape2.getBounds2D().getHeight()/2)+shape2.getBounds2D().getY());

    }
    
    public Shape getShape(final Shape shape,final double x,final double y,final double w,final double h,final int angle) { 
        
        AffineTransform at = new AffineTransform();
        double wP = 1 / shape.getBounds2D().getWidth();
        double hP = 1 / shape.getBounds2D().getHeight();
        at.scale(w * wP, h * hP);
        if(angle>0){
            at.rotate(Math.toRadians(angle),(shape.getBounds2D().getWidth()/2)+shape.getBounds2D().getX(),(shape.getBounds2D().getHeight()/2)+shape.getBounds2D().getY());
        }
        Shape scaledShape = at.createTransformedShape(shape);
        
        AffineTransform at2 = new AffineTransform();
        
        at2.setToTranslation(x-scaledShape.getBounds2D().getX(), y-scaledShape.getBounds2D().getY());
        
        return at2.createTransformedShape(scaledShape);
    }

    private void doDrawing(Graphics2D g2d) {

        g2d.setColor(new Color(0x123456));
        
        g2d.fill(shape2);
        g2d.draw(shape2.getBounds2D());

//       g2d.setTransform(at);
//       
//       g2d.fill(rect);
//       g2d.setColor(Color.YELLOW);
//       g2d.draw(rect.getBounds2D());
//       
//       
//       
//       g2d.setTransform(reset);
//       g2d.setColor(Color.RED);
//       
        //g2d.draw(rect);
        //  System.out.println(path.getBounds2D().getHeight());
    }

    class ML extends MouseAdapter implements MouseListener {

        @Override
        public void mouseDragged(MouseEvent e) {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }
    }

    class KL extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override

        public void keyPressed(KeyEvent e) {
            
            //For Moving Perpose
            if (!e.isControlDown()&&!e.isShiftDown()) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:

                        at.setToTranslation(0, -UNIT_SIZE);
                        shape2 = at.createTransformedShape(shape2);
                        break;

                    case KeyEvent.VK_DOWN:

                        at.setToTranslation(0, +UNIT_SIZE);
                        shape2 = at.createTransformedShape(shape2);
                        break;

                    case KeyEvent.VK_LEFT:
                        at.setToTranslation(-UNIT_SIZE, 0);
                        shape2 = at.createTransformedShape(shape2);
                        break;
                    case KeyEvent.VK_RIGHT:
                        at.setToTranslation(UNIT_SIZE, 0);
                        shape2 = at.createTransformedShape(shape2);
                        break;

                }
                repaint();
            }

            
            if (e.isControlDown()) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        at.setToScale((1/shape2.getBounds2D().getWidth())/30, 1);
                        shape2 = at.createTransformedShape(shape2);
                        break;
                    case KeyEvent.VK_DOWN:
                       at.setToScale((1/shape2.getBounds2D().getWidth())*30, 1);
                        shape2 = at.createTransformedShape(shape2);
                        break;
                    case KeyEvent.VK_LEFT:
                        break;
                    case KeyEvent.VK_RIGHT:
                        break;

                }
                repaint();
            }
        }

    }

    //<editor-fold defaultstate="collapsed" desc="Main Methods">
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        doDrawing(g2d);

    }

    public static void main(String[] args) {
        Path2DTest canvas = new Path2DTest();
        MyFrame frame = new MyFrame(canvas, "Very Simple Painting");
        frame.add(canvas);
        frame.setVisible(true);
    }
//</editor-fold>
}
