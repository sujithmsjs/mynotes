package learn.swings.frames;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MyFrame extends JFrame {

    Board board;
    boolean fullScrean = false;
    
    
    public MyFrame() {
        board = new Board();
        add(board);
        
        if (fullScrean) {
            this.setUndecorated(true);
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else {
            //this.setSize(600, 400);
            this.setResizable(true);
            pack();
            this.setLocationRelativeTo(null);
        }
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    public static void main(String[] args) {
        new MyFrame().setVisible(true);
    }

}

class Board extends JPanel{

    private Dimension SCREEN_SIZE = new Dimension(800,600);
    private static final Random ran = new Random();
    
    public Board() {
        setPreferredSize(SCREEN_SIZE);
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        int w = getWidth();
        int h = getHeight();
        
        g.setColor(Color.red);
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < 50; i++) {
           // Rectangle r = new Rectangle(ran.nextInt(SCREEN_SIZE.width - 50),ran.nextInt(SCREEN_SIZE.height - 50),50,50);
           Rectangle r = new Rectangle(ran.nextInt(w - 50),ran.nextInt(h - 50),50,50);
            g2.fill(r);
        }
        
        
       // Rectangle r = new Rectangle(SCREEN_SIZE.width/2 - 25, SCREEN_SIZE.height/2 - 25,50,50);
        
    }
}
