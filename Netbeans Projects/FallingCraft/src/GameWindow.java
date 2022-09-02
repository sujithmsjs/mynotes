

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class Board extends JPanel implements ActionListener {

    public static final Dimension SCREEN_SIZE = new Dimension(800,600);
    private javax.swing.Timer timer;
    private static final int DELAY = 10;
    
    //Sprites
    private SpaceShip ship;
    private PathController path;
    Score score;
    
    public Board() {
        setBackground(Color.BLACK);
        setPreferredSize(SCREEN_SIZE);
        setFocusable(true);
        addMouseListener(new MA());
        addKeyListener(new KA());
        
        ship = new SpaceShip();
        path = new PathController();
        score = new Score();
        
        timer = new javax.swing.Timer(DELAY,this);
        timer.start();
        path.start();
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        
        ship.drawImage(g2d);
        path.draw(g2d);
        score.drawScore(g2d);
        
        System.out.println("Main Drawing Thread: " + Thread.currentThread().getId());
        System.out.println("Main Drawing Thread Name: " + Thread.currentThread().getName());

    }

    private void checkCollisions(){
        if(ship.getMaxY()>=SCREEN_SIZE.height){
            ship.blasted();
            timer.stop();
            path.stop();
            System.out.println("Game over");
        }
        
        if(path.checkCollision(ship)){ 
            ship.blasted();
            timer.stop();
            path.stop();
            System.out.println("Game over");
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        ship.move();
        path.move();
        score.updateScoure();
        
        checkCollisions();
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

public class GameWindow extends JFrame {

    Board b;

    GameWindow() {
        b = new Board();
        add(b);
        setTitle("Just demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new GameWindow().setVisible(true);
    }

}
