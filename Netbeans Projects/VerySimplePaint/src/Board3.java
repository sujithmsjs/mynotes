
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.awt.geom.RoundRectangle2D;

public class Board3 extends Canvas {

    //private ShapePlus shape = Util.getShapePlus();
  //  ArrayList<ShapePlus> sp = Util.getShapes(6);
    ArrayList<ShapePlus> sp;
    ShapePlus selectedShape;
    PathShape path;
    Arc2D arc;
    
    
    public Board3() {

        setFocusable(true);
        addMouseMotionListener(new ML());
        addMouseListener(new ML());
        addKeyListener(new KL());
        
     //   sp.add(path);
       // sp.add(new ShapePlus(path));
        selectedShape = sp.get(0);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.GREEN);

//        for (ShapePlus shapePlus : sp) {
//            shapePlus.drawMe(g2d);
//        }
        ListIterator list = sp.listIterator(sp.size());
        while (list.hasPrevious()) {

            ShapePlus s = (ShapePlus) list.previous();
            s.drawMe(g2d);
        }
        g2d.setColor(Color.WHITE);
        g2d.draw(selectedShape.getShape().getBounds2D());

    }

    public static void main(String[] args) {
        Board3 canvas = new Board3();
        MyFrame frame = new MyFrame(canvas, "Very Simple Painting");
        frame.add(canvas);
        frame.setVisible(true);
    }

    public void setNextSelection() {
        if (sp.contains(selectedShape)) {
            int index = sp.indexOf(selectedShape);
            selectedShape.setSelected(false); //Previouse Selection
            selectedShape = sp.get((index + 1) % sp.size());
            selectedShape.setSelected(true);
            System.out.println(selectedShape.getShapeName());
        }
    }

    class ML extends MouseAdapter implements MouseListener {

        @Override
        public void mouseDragged(MouseEvent e) {
            if (selectedShape != null && selectedShape.isHold() && selectedShape.isRectangle()) {
                RectangularShape r = (RectangularShape) selectedShape.getShape();
                Point p = Util.getExactPoint(e.getPoint());
                r.setFrame(p.x, p.y, r.getWidth(), r.getHeight());
                repaint();
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (selectedShape != null && e.getClickCount() > 1 && selectedShape.getShape().contains(e.getX(), e.getY())) {
                selectedShape.changeColor();
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {

            for (ShapePlus shapePlus : sp) {
                if (shapePlus.mouseMoved(e)) {
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                    break;
                } else {
                    setCursor(Cursor.getDefaultCursor());
                }
            }
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            selectedShape.setHold(false);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            ListIterator list = sp.listIterator(sp.size());
            while (list.hasPrevious()) {
                ShapePlus s = (ShapePlus) list.previous();
                if (s.contains(e.getX(), e.getY())) {
                    s.setSelected(true);
                    s.setHold(true);
                    selectedShape.setSelected(false);
                    selectedShape = s;
                }
            }
        }
    }

    class KL extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override

        public void keyPressed(KeyEvent e) {

            if (e.getKeyCode() == KeyEvent.VK_SPACE) {

                System.out.println(selectedShape.getShapeName());

                setNextSelection();

                repaint();
            } else {
                System.out.println("Not same as Tab");
            }

            if (selectedShape != null) {

                if (selectedShape.isRectangle()) {

                    RectangularShape r = (RectangularShape) selectedShape.getShape();

                    if (e.isShiftDown()) {
                        String name = selectedShape.getShapeName();
                        System.out.println(name);

                        switch (name) {
                            case ShapeCode.ARC2D:
                                Arc2D arc = (Arc2D) selectedShape.getShape();
                                switch (e.getKeyCode()) {
                                    case KeyEvent.VK_LEFT:
                                        arc.setAngleStart(arc.getAngleStart() + 1);
                                        break;
                                    case KeyEvent.VK_RIGHT:
                                        arc.setAngleStart(arc.getAngleStart() - 1);
                                        break;
                                    case KeyEvent.VK_UP:
                                        arc.setAngleExtent(arc.getAngleExtent() + 1);
                                        break;
                                    case KeyEvent.VK_DOWN:
                                        arc.setAngleExtent(arc.getAngleExtent() - 1);
                                        break;
                                    case KeyEvent.VK_1:
                                        arc.setArcType(Arc2D.CHORD);
                                        break;
                                    case KeyEvent.VK_2:
                                        arc.setArcType(Arc2D.OPEN);
                                        break;
                                    case KeyEvent.VK_3:
                                        arc.setArcType(Arc2D.PIE);
                                        break;

                                }
                                repaint();
                            case ShapeCode.RECT2D:
                                break;

                            case ShapeCode.ELLIPSE2D:
                                break;

                            case ShapeCode.ROUND_RECT2D:
                                break;
                        }
                    }

                    if (!e.isControlDown() && !e.isShiftDown()) {

                        switch (e.getKeyCode()) {
                            case KeyEvent.VK_DELETE:

                                if (sp.contains(selectedShape)) {
                                    int index = sp.indexOf(selectedShape);

                                    System.out.println(sp.size());

                                    setNextSelection();
                                    sp.remove(index);

                                    System.out.println(sp.size());

                                    repaint();
                                }

                                break;
                            case KeyEvent.VK_RIGHT:
                                r.setFrame(r.getX() + UNIT_SIZE, r.getY(), r.getWidth(), r.getHeight());
                                repaint();
                                break;
                            case KeyEvent.VK_LEFT:
                                r.setFrame(r.getX() - UNIT_SIZE, r.getY(), r.getWidth(), r.getHeight());
                                repaint();
                                break;
                            case KeyEvent.VK_UP:
                                r.setFrame(r.getX(), r.getY() - UNIT_SIZE, r.getWidth(), r.getHeight());
                                repaint();
                                break;
                            case KeyEvent.VK_DOWN:
                                r.setFrame(r.getX(), r.getY() + UNIT_SIZE, r.getWidth(), r.getHeight());
                                repaint();
                                break;
                        }
                    }
                    //Resizing options
                    if (e.isControlDown()) {
                        switch (e.getKeyCode()) {
                            case KeyEvent.VK_DOWN:
                                r.setFrame(r.getX(), r.getY(), r.getWidth(), r.getHeight() + Canvas.UNIT_SIZE);
                                repaint();

                                break;
                            case KeyEvent.VK_RIGHT:

                                r.setFrame(r.getX(), r.getY(), r.getWidth() + Canvas.UNIT_SIZE, r.getHeight());
                                repaint();
                                break;

                            case KeyEvent.VK_UP:
                                if (r.getHeight() > UNIT_SIZE) {
                                    r.setFrame(r.getX(), r.getY(), r.getWidth(), r.getHeight() - Canvas.UNIT_SIZE);
                                    repaint();
                                }

                                break;
                            case KeyEvent.VK_LEFT:
                                if (r.getWidth() > UNIT_SIZE) {
                                    r.setFrame(r.getX(), r.getY(), r.getWidth() - Canvas.UNIT_SIZE, r.getHeight());
                                    repaint();
                                }
                                break;

                            //Duplication Shape.
                            case KeyEvent.VK_J:

                                switch (selectedShape.getShapeName()) {

                                    case ShapeCode.ARC2D:
                                        Arc2D arc = (Arc2D) selectedShape.getShape();
                                        arc.setFrame(0, 0, arc.getWidth(), arc.getWidth());
                                        sp.add(new ShapePlus(arc));
                                        break;

                                    case ShapeCode.ELLIPSE2D:
                                        Ellipse2D ellipse = (Ellipse2D) selectedShape.getShape();
                                        ellipse.setFrame(0, 0, ellipse.getWidth(), ellipse.getWidth());
                                        sp.add(new ShapePlus(ellipse));
                                        break;

                                    case ShapeCode.RECT2D:
                                        Rectangle2D rect = (Rectangle2D) selectedShape.getShape();
                                        rect.setFrame(0, 0, rect.getWidth(), rect.getWidth());
                                        sp.add(new ShapePlus(rect));
                                        break;

                                    case ShapeCode.ROUND_RECT2D:
                                        RoundRectangle2D orect = (RoundRectangle2D) selectedShape.getShape();
                                        orect.setFrame(0, 0, orect.getWidth(), orect.getWidth());
                                        sp.add(new ShapePlus(orect));
                                        break;

                                }

                                repaint();

                                break;
                            case KeyEvent.VK_O:
                                break;
                            case KeyEvent.VK_A:
                                break;
                            case KeyEvent.VK_E:

                                break;
                        }

                    }
                }
            }
        }

    }

}
