package com.suji.paint;


import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Board extends Canvas {

    //private ShapePlus shape = Util.getShapePlus();
    ArrayList<ShapePlus> sp;
    static Set<String> nameSet;

    PathShape path;
    private boolean pathPaintingStarted;
    private DashBoard dashBoard;
    private SaveCodePanel saveCode;
    private ShapePlus selectedShape;
    
    int xGap;
    int yGap;

    public Board() {

        setFocusable(true);
        addMouseMotionListener(new ML());
        addMouseListener(new ML());
        addKeyListener(new KL());
        
        
        sp = new ArrayList<ShapePlus>();
        dashBoard = new DashBoard(sp);
        saveCode = new SaveCodePanel(sp);
        
        Arc2D pacMan = new Arc2D.Double(SCREEN_SIZE.width/2 - 50, SCREEN_SIZE.height/2 - 50,  100  , 100, 46.0, 283.0, 2);
        sp.add(new ShapePlus(pacMan,Color.YELLOW,"pacMan"));
        nameSet = new HashSet<String>();
        nameSet.add("demo");
        selectedShape = sp.get(0);
        path = new PathShape();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        dashBoard.draw(g2d);
        dashBoard.drawDesc(selectedShape, g2d);
        
        g.setColor(Color.GREEN);

        if (pathPaintingStarted) {
            
            g2d.setPaint(Color.DARK_GRAY);
            path.draw(g2d);
            
            super.showDots(true);
           
        } else {
            
            super.showDots(false);
        }

        if (!sp.isEmpty()) {

            ListIterator list = sp.listIterator(sp.size());
            while (list.hasPrevious()) {

                ShapePlus s = (ShapePlus) list.previous();
                s.drawMe(g2d);
            }
            if (selectedShape != null) {
                drawSelectedBorder(g2d);
            }

        }

    }

    public void drawSelectedBorder(Graphics2D g) {
        Rectangle2D r = selectedShape.getShape().getBounds2D();
        g.draw(r);
        g.draw(new Line2D.Double(r.getCenterX(), 0, r.getCenterX(), SCREEN_SIZE.getHeight()));
        g.draw(new Line2D.Double(0, r.getCenterY(), SCREEN_SIZE.height, r.getCenterY()));
    }

    public void setNextSelection() {
        int size = sp.size();

        switch (size) {
            case 0:
                JOptionPane.showMessageDialog(null, "No Shapes were drawn.");
                break;
            case 1:
                selectedShape = sp.get(0);
                break;
            default:
                int index = sp.indexOf(selectedShape);
                int newIndex = (index + 1) % sp.size();
                selectedShape = sp.get(newIndex);
                break;
        }

        if (size == 0) {

        } else if (size == 1) {
            selectedShape = sp.get(0);
        } else {

        }

//        if (sp.contains(selectedShape)) {
//            
//            int index = sp.indexOf(selectedShape);
//            
//            selectedShape.setSelected(false); //Previouse Selection
//            
//            int newIndex = 0;
//            
//            if(sp.size() <= 0){ // If Empty
//                JOptionPane.showMessageDialog(null, "No Shapes were drawn.");
//            }else{
//                newIndex = (index + 1) % sp.size();
//                System.out.println("newIndex = " + newIndex);
//                selectedShape = sp.get(newIndex);
//                selectedShape.setSelected(true);
//            }
//        }
    }

    public void comeForth() {
        if (selectedShape != null && sp.contains(selectedShape)) {
            int index = sp.indexOf(selectedShape);
            System.out.println("Selected Index." + index);
            if (index < sp.size() - 1) {
                Collections.swap(sp, index, index + 1);
            } else {
                System.out.println("Alreay");
            }
        }
    }

    public void comeBackword() {
        if (selectedShape != null && sp.contains(selectedShape)) {

            int index = sp.indexOf(selectedShape);

            System.out.println("Selected Index." + index);

            if (index > 0) {
                Collections.swap(sp, index, index - 1);
            } else {
                System.out.println("Alreay");
            }
        }
    }
    
    public  String getName(){
        String name = null;
        String input = JOptionPane.showInputDialog("Enter shape name");
        if(input != null && !nameSet.contains(input) ){
            name = input;
            
        }else{
            input = null;
            
        }
        System.out.println("Name: "+name);
        return name;
    }
    
    public void copyShapePlus(){
      //  ShapePlus sPlus = new ShapePlus(selectedShape.getShape(),this, selectedShape.getPaint(), selectedShape.getOpacity(), selectedShape.getAngle());
         
      
      RectShape r = new RectShape(selectedShape.getShape());
      
      String name = getName();
     if(name != null){
         ShapePlus sPlus = new ShapePlus(r, this, selectedShape.getPaint(), selectedShape.getOpacity(), selectedShape.getAngle(),name);
         sPlus.setHold(false);
         sPlus.setSelected(false);
         sp.add(sPlus);
         selectedShape.setAngle(selectedShape.getAngle());
     }else{
         JOptionPane.showMessageDialog(null, "Name already existed.");
     }
      
    }

//<editor-fold defaultstate="collapsed" desc="KeyListener">
    class KL extends KeyAdapter {
        
        //Creating new Shapes.
        private void addNew(KeyEvent e) {
            //New Shapes will be created using this methods.
            if (e.isControlDown() && !e.isShiftDown()) {
                 
                Color color = null;
                ShapePlus shape = null;
                
                switch (e.getKeyCode()) {
                    
                    case KeyEvent.VK_P:
                        pathPaintingStarted = true;
                        break;
                        
                    case KeyEvent.VK_O: // Ctrl+O is to create Rounded Rectangle
                        
                        RoundRectangle2D orect = new RoundRectangle2D.Double(0, 0, 4 * Canvas.UNIT_SIZE, 4 * Canvas.UNIT_SIZE, 2 * Canvas.UNIT_SIZE, 2 * Canvas.UNIT_SIZE);
                        
                        String name = getName();
                        
                        if(name != null){
                            
                            color = JColorChooser.showDialog(null, "Select shape Color", Color.WHITE);
                            
                            shape = new ShapePlus(orect,color, name);
                            sp.add(shape);
                            selectedShape = shape;
                        }else{
                            JOptionPane.showMessageDialog(null, "Name already existed", "Try new name.", JOptionPane.ERROR_MESSAGE);
                        }
                        
                        break;
                        
                    case KeyEvent.VK_A: // Ctrl + A is to draw Ark
                        Arc2D arc = new Arc2D.Double(0, 0, 4 * Canvas.UNIT_SIZE, 4 * Canvas.UNIT_SIZE, 0, 90, Arc2D.PIE);
                       // color = JColorChooser.showDialog(null, "Select shape Color", Color.WHITE);
                        
                        String name2 = getName();

                        if (name2 != null) {

                            color = JColorChooser.showDialog(null, "Select shape Color", Color.WHITE);

                            shape = new ShapePlus(arc, color, name2);
                            sp.add(shape);
                            selectedShape = shape;
                        } else {
                            JOptionPane.showMessageDialog(null, "Name already existed", "Try new name.", JOptionPane.ERROR_MESSAGE);
                        }
                        
                        break;
                        
                    case KeyEvent.VK_E: // Ctrl + E for ellipse
                        
                        Ellipse2D ellipse = new Ellipse2D.Double(0, 0, 4 * Canvas.UNIT_SIZE, 4 * Canvas.UNIT_SIZE);
                        
                        String name3 = getName();

                        if (name3 != null) {

                            color = JColorChooser.showDialog(null, "Select shape Color", Color.WHITE);

                            shape = new ShapePlus(ellipse, color, name3);
                            sp.add(shape);
                            selectedShape = shape;
                        } else {
                            JOptionPane.showMessageDialog(null, "Name already existed", "Try new name.", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                        
                    case KeyEvent.VK_R: // Ctrl+ R for rectangle
                        Rectangle2D rect = new Rectangle2D.Double(0, 0, 4 * Canvas.UNIT_SIZE, 4 * Canvas.UNIT_SIZE);
                        String name4 = getName();

                        if (name4 != null) {

                            color = JColorChooser.showDialog(null, "Select shape Color", Color.WHITE);

                            shape = new ShapePlus(rect, color, name4);
                            sp.add(shape);
                            selectedShape = shape;
                        } else {
                            JOptionPane.showMessageDialog(null, "Name already existed", "Try new name.", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    case KeyEvent.VK_S:
                        System.out.println("Save file dialog");
                        
                        ShapePlusArraySaver save = new ShapePlusArraySaver(sp);
                        save.doSave();
                        
                        
                        
                } // Switch Closed.
                
                repaint();
            }
            
        }
        
        // Method end.
        //Used to resize Selected Shape. Any Shape should be selected.
        private void scaleDimensions(KeyEvent e) {
            if (selectedShape != null && selectedShape.isRectangle() && e.isControlDown() && !e.isShiftDown()) {
                //All properties same for RectangularShape.
                RectangularShape r = (RectangularShape) selectedShape.getShape();
                
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_DOWN:
                        r.setFrame(r.getX(), r.getY(), r.getWidth(), r.getHeight() + Canvas.UNIT_SIZE);
                        break;
                        
                    case KeyEvent.VK_RIGHT:
                        r.setFrame(r.getX(), r.getY(), r.getWidth() + Canvas.UNIT_SIZE, r.getHeight());
                        break;
                        
                    case KeyEvent.VK_UP:
                        if (r.getHeight() > Canvas.UNIT_SIZE) {
                            r.setFrame(r.getX(), r.getY(), r.getWidth(), r.getHeight() - Canvas.UNIT_SIZE);
                        }
                        
                        break;
                    case KeyEvent.VK_LEFT:
                        if (r.getWidth() > Canvas.UNIT_SIZE) {
                            r.setFrame(r.getX(), r.getY(), r.getWidth() - Canvas.UNIT_SIZE, r.getHeight());
                        }
                        break;
                        
                    case KeyEvent.VK_U:
                        selectedShape.changeColor();
                        break;
                        
                    case KeyEvent.VK_G:
                        System.out.println("VK_PLUS Pressed.");
                        break;
                    case KeyEvent.VK_MINUS:
                        System.out.println("Minus pressed.");
                        if(Canvas.UNIT_SIZE > 1){
                            Canvas.UNIT_SIZE -= 1;
                        }
                        break;
                    case KeyEvent.VK_EQUALS:
                        Canvas.UNIT_SIZE += 1; 
                        break;
                    case KeyEvent.VK_H:
                        if(getGrid()){
                            showGrid(false);
                        }else{
                            showGrid(true);
                        }         
                        break;
                    
                } // Close switch
                repaint();
                
            } // Close if
        } // Close Method.
        
        //To create a new duplicate shape object.
        private void duplicateCopy(KeyEvent e) {
            
            if (selectedShape != null && e.isControlDown() && e.getKeyCode() == KeyEvent.VK_J) {
                copyShapePlus();
                /*
                switch (selectedShape.getShapeName()) {
                    
                    case ShapeCode.ARC2D:
                        
                        
                        Arc2D arc = (Arc2D) selectedShape.getShape();
                        
                        
                        Shape newArc = new Arc2D.Double(arc.getX()+UNIT_SIZE, arc.getX()+UNIT_SIZE, arc.getWidth(), arc.getHeight(), arc.getAngleStart(), arc.getAngleExtent(), arc.getArcType());
                        
                        ShapePlus sPlus = new ShapePlus(selectedShape.getShape(), parent, opacity, angle);
                        
                        System.out.println(arc);
                        
                        
                        sp.add(new ShapePlus(newArc));
                        
                        
                        
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
                        
                } // Close Switch
                */
                repaint();
                
            } //  Close If
            
        } // Close Method.
        
        //Every shape contains few spicial features.
        private void specialProperties(KeyEvent e) {
            
            if (selectedShape != null && selectedShape.isRectangle()) {
                
                RectangularShape r = (RectangularShape) selectedShape.getShape();
                
                if (e.isShiftDown() && !e.isControlDown()) {
                    
                    String name = selectedShape.getShapeName();
                   
                    
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
                    } // Close shift
                } // Close If
                
            } // Close inner if
        } // Close method.
        
        //To change the alpha using Number keys.
        private void changeOpacity(KeyEvent e) {
            
            if (selectedShape != null && !e.isControlDown() && !e.isShiftDown()) {
                
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_0:
                        selectedShape.setOpacity(1f);
                        break;
                    case KeyEvent.VK_1:
                        selectedShape.setOpacity(0.9f);
                        break;
                    case KeyEvent.VK_2:
                        selectedShape.setOpacity(0.8f);
                        break;
                    case KeyEvent.VK_3:
                        selectedShape.setOpacity(0.7f);
                        break;
                    case KeyEvent.VK_4:
                        selectedShape.setOpacity(0.6f);
                        break;
                    case KeyEvent.VK_5:
                        selectedShape.setOpacity(0.5f);
                        break;
                    case KeyEvent.VK_6:
                        selectedShape.setOpacity(0.4f);
                        break;
                    case KeyEvent.VK_7:
                        selectedShape.setOpacity(0.3f);
                        break;
                    case KeyEvent.VK_8:
                        selectedShape.setOpacity(0.2f);
                        break;
                    case KeyEvent.VK_9:
                        selectedShape.setOpacity(0.1f);
                        break;
                }
                
                repaint();
            }
        }
        
        // These things are same for all the shapes.
        private void commonToAllShapes(KeyEvent e) {
            
            if (selectedShape != null && selectedShape.isRectangle() && !e.isControlDown() && !e.isShiftDown()) {
                RectangularShape r = (RectangularShape) selectedShape.getShape();
                switch (e.getKeyCode()) {
                    
                    //Delete a shape
                    case KeyEvent.VK_DELETE:
                        
                        if (sp.contains(selectedShape)) {
                            int index = sp.indexOf(selectedShape);
                            sp.remove(index);
                            repaint();
                            setNextSelection();
                            
                            System.out.println(sp.size());
                            System.out.println(sp);
                            
                        }
                        break;
                        
                        // Moving Right
                    case KeyEvent.VK_RIGHT:
                        
                        if ((r.getX() + Canvas.UNIT_SIZE) < SCREEN_SIZE.width) {
                            r.setFrame(r.getX() + Canvas.UNIT_SIZE, r.getY(), r.getWidth(), r.getHeight());
                        }
                        
                        break;
                        
                        // Mvoing left
                    case KeyEvent.VK_LEFT:
                        if ((r.getX() + r.getWidth() - Canvas.UNIT_SIZE) >= 0) {
                            r.setFrame(r.getX() - Canvas.UNIT_SIZE, r.getY(), r.getWidth(), r.getHeight());
                        }
                        break;
                        
                        //Moving Up
                    case KeyEvent.VK_UP:
                        if ((r.getY() + r.getHeight() - Canvas.UNIT_SIZE) >= 0) {
                            r.setFrame(r.getX(), r.getY() - Canvas.UNIT_SIZE, r.getWidth(), r.getHeight());
                        }
                        break;
                        
                    case KeyEvent.VK_DOWN:
                        if ((r.getY() + Canvas.UNIT_SIZE) < SCREEN_SIZE.height) {
                            r.setFrame(r.getX(), r.getY() + Canvas.UNIT_SIZE, r.getWidth(), r.getHeight());
                        }
                        break;
                        
                    case KeyEvent.VK_SPACE:
                        if (sp.size() > 0) {
                            setNextSelection();
                            
                            
                            
                        } else {
                            JOptionPane.showMessageDialog(null, "No shapes to select : Shape:" + sp.size());
                        }
                        
                        break;
                } // Switch closed
                repaint();
            }
        } // Method closed.
        
        private void ctrlShiftDown(KeyEvent e) {
            if (selectedShape != null && e.isControlDown() && e.isShiftDown()) {
                // Ctrl + Shift + UP: Shape come to top
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_DOWN:
                        System.out.println("CTRL + SHIFT + DOWN");
                        comeForth();
                        break;
                    case KeyEvent.VK_UP:
                        System.out.println("CTRL + SHIFT + UP");
                        comeBackword();
                        break;
                    case KeyEvent.VK_LEFT:
                        System.out.println("CTRL + SHIFT + LEFT");
                       // selectedShape.setAngle(selectedShape.getAngle() + 1);
                        break;
                    case KeyEvent.VK_RIGHT:
                       // selectedShape.setAngle(selectedShape.getAngle() - 1);
                        System.out.println("CTRL + SHIFT + RIGHT");
                        break;
                    case KeyEvent.VK_S:
                        saveCode.save();
                        System.out.println("Save code Pressed.");
                        break;
                }
                repaint();
            }
            
        }
        
        @Override
        public void keyPressed(KeyEvent e) {
            
            //If Path2D Painting started, not will work unit is is set to false.
            if (!pathPaintingStarted) {
                addNew(e);
                scaleDimensions(e);
                duplicateCopy(e);
                specialProperties(e);
                commonToAllShapes(e);
                changeOpacity(e);
                ctrlShiftDown(e);
            } else {
                
                
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    pathPaintingStarted = false;
                    path.clear();
                }
                
                String name = getName();
                if(name != null){
                    path.keyPressed(e,name);
                    if (path.isPathClosed()) {
                        pathPaintingStarted = false;
                        
                        path.createShape();
                        
                        sp.add(new ShapePlus(path, path.getColor(), path.getName()));

                        //Clearing the previous object.
                        path = new PathShape();
                    }
                }
                
                repaint();
                
            }
        }
        
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Mouse Listener">

    class ML extends MouseAdapter implements MouseListener {

        @Override
        public void mouseDragged(MouseEvent e) {
            if (!pathPaintingStarted) {
                if (selectedShape != null && selectedShape.isHold() && selectedShape.isRectangle()) {
                    RectangularShape r = (RectangularShape) selectedShape.getShape();
                    
                  //  Point p = Util.getExactPoint(e.getPoint(), Canvas.UNIT_SIZE);
                    
                    int x = e.getX() - xGap;
                    int y = e.getY() - yGap;
                    
                    Point p1= Util.getExactPoint(new Point(x,y),Canvas.UNIT_SIZE);
                    
                    r.setFrame(p1.x, p1.y, r.getWidth(), r.getHeight());
                    
                    repaint();
                }
            }

        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (!pathPaintingStarted) {
                if (selectedShape != null && e.getClickCount() > 1 && selectedShape.getShape().contains(e.getX(), e.getY())) {
                    selectedShape.changeColor();
                }
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if (!pathPaintingStarted) {
                for (ShapePlus shapePlus : sp) {
                    if (shapePlus.mouseMoved(e)) {
                        setCursor(new Cursor(Cursor.HAND_CURSOR));
                        break;
                    } else {
                        setCursor(Cursor.getDefaultCursor());
                    }
                }
                repaint();
            } else {
                path.mouseMoved(e);
                repaint();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (!pathPaintingStarted && selectedShape != null) {
                selectedShape.setHold(false);
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (!pathPaintingStarted) {
                ListIterator list = sp.listIterator(sp.size());
                while (list.hasPrevious()) {
                    ShapePlus s = (ShapePlus) list.previous();
                    if (s.contains(e.getX(), e.getY())) {
                        s.setSelected(true);
                        s.setHold(true);
                        xGap = e.getX() - s.getShape().getBounds().x;
                        yGap = e.getY() - s.getShape().getBounds().y;
                        selectedShape.setSelected(false);
                        selectedShape = s;

                    }
                }
            } else {
                path.mousePressed(e);

            }
            repaint();
        }
    }
//</editor-fold>
}
