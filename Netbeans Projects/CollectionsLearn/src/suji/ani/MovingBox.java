package suji.ani;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

class MovingBoxPanel extends JPanel implements ActionListener {

    //USER Settings
    public final Rectangle.Double box = new Rectangle.Double(0, 0, 30, 30);
    public static final Dimension SCREEN_SIZE = new Dimension(500, 800);
    private static final Random random = new Random();

    private static Point direction = new Point(0,1);
    private static int step = 1;
    /*
    DIRECTION X STEP = DISPLACEMENT.
    DIRECTION works as a HEAD.
    STEP words as a LEG.
     */

    private Timer timer;

    public MovingBoxPanel() {
        //Initialization
        timer = new Timer(10, this);

        //Settings
        setPreferredSize(SCREEN_SIZE);
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(new KL());

        newMovingBox();
        timer.start();
    }

    public void draw(Graphics2D g) {
        g.setPaint(Color.ORANGE);
        g.fill(box);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
   
        int xDisplacement = (int) (box.x + (step * direction.x));
        box.x = xDisplacement;
        
        int yDisplacement = (int) (box.y + (step * direction.y));
        box.y =yDisplacement;
        
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        draw(g2d);
    }

    public void newMovingBox() {
        box.x = (SCREEN_SIZE.width + box.width) / 2;
        box.y = (SCREEN_SIZE.height + box.height) / 2;
    }

    private class KL extends KeyAdapter {
        
        boolean isPreviousKey = true;
     
        @Override
        public void keyPressed(KeyEvent e) {
            
            
            
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    if(isPreviousKey){
                        
                    }
                    direction.y = -1;
                    
                    break;
                case KeyEvent.VK_DOWN:
                    direction.y = 1;
                    break;
                case KeyEvent.VK_LEFT:
                    direction.x = -1;
                    break;
                case KeyEvent.VK_RIGHT:
                    direction.x = 1;
                    break;
            }
        }
    }

}

//<editor-fold defaultstate="collapsed" desc="MovingBox Class">
public class MovingBox extends JFrame {

    MovingBoxPanel boxPanel;

    public MovingBox() {
        boxPanel = new MovingBoxPanel();
        add(boxPanel);
        setTitle("Ballon Animation");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new MovingBox().setVisible(true);
    }

}
//</editor-fold>
