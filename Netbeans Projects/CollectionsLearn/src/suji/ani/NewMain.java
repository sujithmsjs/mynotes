package suji.ani;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class Board extends JPanel implements ActionListener {

    public static final Dimension SCREEN_SIZE = new Dimension(600,600);
    private SpaceShip ship;
    private javax.swing.Timer timer;
    private static final int DELAY = 10;
    
    public Board() {
        setBackground(Color.BLACK);
        setPreferredSize(SCREEN_SIZE);
        setFocusable(true);
        addMouseListener(new MA());
        addKeyListener(new KA());
        
        ship = new SpaceShip();
        timer = new javax.swing.Timer(DELAY,this);
        timer.start();
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        ship.drawImage(g2d);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ship.move();
        System.out.println("Painting");
        repaint();
    }
   
    class KA extends KeyAdapter{

        @Override
        public void keyReleased(KeyEvent e) {
            ship.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            ship.keyPressed(e);
        }
        
    }
    
    
    class MA extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if(ship.contains(e.getPoint())){
                System.out.println("Collided.");
            }
        }
    } // MouseAdapter class closed.
}

public class NewMain extends JFrame {

    Board b;

    NewMain() {
        b = new Board();
        add(b);
        setTitle("Just demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new NewMain().setVisible(true);
    }

}
