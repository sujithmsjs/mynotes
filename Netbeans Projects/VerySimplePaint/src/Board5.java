
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board5 extends Canvas {

 
    PathShape r;
    
    public Board5() {
        draw();
        setFocusable(true);
        addKeyListener(new KL());
        
    }

    private void draw() {
        r = new PathShape();
        r.add(0,0);
        r.add(100,0);
        r.add(50,50);
        r.setClosePath(true);
        
        System.out.println("x "+r.getX());
        System.out.println("y "+r.getY());
        System.out.println("w "+r.path.getBounds2D().getWidth());
        System.out.println("h "+r.getHeight());
        
        
      //  r.setFrame(r.getX() + UNIT_SIZE, r.getY(), r.getWidth(), r.getHeight());
       
        
       // r.setFrame(0, 0, 100, 100);
        System.out.println("Bounds"+r.getBounds2D());
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setColor(new Color(0x1234567));
        g2d.draw(r.getBounds2D());
        g2d.fill(r);
        
    }

    public static void main(String[] args) {
        Board5 canvas = new Board5();
        MyFrame frame = new MyFrame(canvas, "Very Simple Painting");
        frame.add(canvas);
        frame.setVisible(true);
    }
    
    class KL extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()){
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
            System.out.println(r.getBounds2D());      
            System.out.println(UNIT_SIZE);
        }
        
    }

}
