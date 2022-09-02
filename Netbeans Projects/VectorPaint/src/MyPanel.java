
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;


public class MyPanel extends JPanel implements ActionListener {

    
    final Dimension SCREEN_SIZE = new Dimension(600,400);
    final int UNIT_SIZE  = 30;
    final int DELAY  = 6;
    Timer timer;
    
    //Mouse parameters
    Point pressedAt;
    Point releasedAt;
    Point draggedAt;
    
    boolean isMouseDragging = false;
    boolean isMousePressed = false;
    
    Rectangle2D.Double rect;
    
    public MyPanel() {
        
        setBackground(Color.BLACK);
        setPreferredSize(SCREEN_SIZE);
        addObjects();
        
        timer = new Timer(DELAY,this);
        timer.start();
        
        addMouseListener(new MA());
        addMouseMotionListener(new MA());
        
        
        
        /*        
        InputTestAdapter l = new InputTestAdapter();
        
        addMouseListener(l);
        addMouseMotionListener(l);
        addMouseWheelListener(l);
        addKeyListener(l);
        
        */
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        Graphics2D g2d = (Graphics2D) g;
        doDrawing(g2d);
    }
    
    private void doDrawing(Graphics2D g) {
         g.setColor(Color.RED);
         g.fill(rect);
         if(isMouseDragging){
             g.drawLine(pressedAt.x, pressedAt.y, draggedAt.x, draggedAt.y);
            //g.drawRect(pressedAt.x, pressedAt.y, pressedAt.x+draggedAt.x, pressedAt.y+draggedAt.y);
            
            int x = pressedAt.x - draggedAt.x;
            int y = pressedAt.y - draggedAt.y;
            
             
            
         }
         
    }

    private void addObjects() {
        rect = new Rectangle2D.Double(0,0,UNIT_SIZE,UNIT_SIZE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
      //  System.out.println(rect);
    }


    
    class MA extends MouseAdapter implements MouseMotionListener{

        @Override
        public void mouseMoved(MouseEvent e) {
            rect.x = e.getX()-(rect.width/2);
            rect.y = e.getY()-(rect.height/2);
            repaint();
            
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            isMouseDragging = true;
            draggedAt = e.getPoint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            isMousePressed = false;
            isMouseDragging = false;
            releasedAt = e.getPoint();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            isMousePressed = true;
            pressedAt = e.getPoint();
        }
        
    }

    

}
