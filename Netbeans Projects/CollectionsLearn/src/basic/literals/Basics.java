package basic.literals;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JPanel;
import game.loops.*;
import java.awt.Image;
import util.swing.ReusableFrame;


public class Basics extends JPanel implements GameLooper {

    public static final int UNIT_SIZE = 10;
    public static Dimension SCREEN_SIZE;
    public static final Dimension FRAME_SIZE = new Dimension(300,250);
    private GameLoop gameLoop;
    private Random ran;
    private int camX, camY;
    private int xDir, yDir;
    private int speed = 5;
    Image image;
    
    int colors[];
    

    public Basics() {
        setPreferredSize(FRAME_SIZE);
        addKeyListener(new KA());
        setBackground(Color.BLACK);
        setFocusable(true);
        gameLoop = new GameLoop(true,this);
        colors = colors750();
        ran = new Random(0);
        image = ImageLoader.getImage("C:\\Users\\sujit\\OneDrive\\Desktop\\jesus.png");
       
        SCREEN_SIZE = new Dimension(image.getWidth(null),image.getHeight(null));
        
        System.out.println(FRAME_SIZE);
        System.out.println(SCREEN_SIZE);
        
        
        gameLoop.start();
    }
   
    @Override
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;   
        g2d.translate(camX, camY);
        //g2d.translate(100, 100);
        g2d.setColor(Color.WHITE);
        g2d.drawImage(image, 0, 0, null);
        //g2d.translate(-camX, -camY);
        
        
        
        g2d.setColor(Color.WHITE);
        //g2d.fillOval(FRAME_SIZE.width-10,FRAME_SIZE.height-10, 20, 20);
        
        
        /*int xBlocks = SCREEN_SIZE.width/UNIT_SIZE;
        int yBlocks = SCREEN_SIZE.height/UNIT_SIZE;
        int tBlocks = xBlocks * yBlocks;
        System.out.println("tBlocks = " + tBlocks);
        
        int n = 0;
        for (int xx = 0; xx < xBlocks; xx++) {
        for (int yy = 0; yy < yBlocks; yy ++) {
        g2d.setColor(new Color(colors[n++]));
        g2d.fillRect(xx*UNIT_SIZE, yy*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
        }
        }  */    
    }

    
    
    
    
    
    
    
    public static int[] colors750() {
        float hue = 1F / 30;
        float sat = 1F / 5;
        float bri = 1F / 5;

       // System.out.println(30 * 5 * 5);
        int n = 0;
        int[] color = new int[750];
        for (int i = 1; i <= 30; i++) { //0 - 30
            for (int j = 1; j <= 5; j++) {
                for (int k = 1; k <= 5; k++) {
                    color[n++] = Color.HSBtoRGB(i * hue, j * sat, k * bri);
                    //System.out.println(++n+" hue"+i+"; sat "+j+"; bri"+k);
                }
            }
        }
        return color;
    }

    public void coloredBlocks(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int xBlocks = SCREEN_SIZE.width / UNIT_SIZE;
        int yBlocks = SCREEN_SIZE.height / UNIT_SIZE;
        int tBlocks = xBlocks * yBlocks;
       // System.out.println("tBlocks = " + tBlocks);
        int cNum = 1;
        float colSigment = 1F / tBlocks;

        for (int xx = 0; xx < xBlocks; xx++) { // xx Bounds 0 to 29
            for (int yy = 0; yy < yBlocks; yy++) {
                //float color = xx*yy*colCode; // 0*0*0.0016 to 0.91833335
                Color c = new Color(Color.HSBtoRGB(++cNum * colSigment, 1, 1));
                g2d.setColor(c);
                g2d.fillRect(xx * UNIT_SIZE, yy * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
            }
        }

        /* 
        
        g2d.setColor(Color.WHITE);
        for (int i = 0; i <= SCREEN_SIZE.width; i += UNIT_SIZE) {
        g2d.drawLine(i, 0, i, SCREEN_SIZE.height);
        }
        
        for (int i = 0; i <= SCREEN_SIZE.height; i += UNIT_SIZE) {
        g2d.drawLine(0, i, SCREEN_SIZE.width, i);
        }
        
         */
        // int x = ran.nextInt(xBlocks)*UNIT_SIZE;
        // int y = ran.nextInt(yBlocks)*UNIT_SIZE;

    }

    @Override
    public void tick() {
        camX -= xDir*speed;
        camY -= yDir*speed;
        if(camX > 0){
            camX = 0;
        }
        
        if(camY > 0){
            camY = 0;
        }
        if(camX <  FRAME_SIZE.width - SCREEN_SIZE.width){
            camX = FRAME_SIZE.width - SCREEN_SIZE.width;
            System.out.println("Cam X reached end..."+camX);
        } 
        if (camY < FRAME_SIZE.height - SCREEN_SIZE.height) { //300 - 3000 = -2500;
            camY = FRAME_SIZE.height - SCREEN_SIZE.height;
            System.out.println("Cam Y reached end..." + camY);
        }
    }

    @Override
    public void render() {
        repaint();
    }
    
    
    private class KA extends KeyAdapter{

        @Override
        public void keyReleased(KeyEvent e) {
            switch(e.getKeyCode()){
                case KeyEvent.VK_UP:
                    yDir = 0;
                    break;
                case KeyEvent.VK_DOWN:
                    yDir = 0;
                    break;
                case KeyEvent.VK_LEFT:
                    xDir = 0;
                    break;
                case KeyEvent.VK_RIGHT:
                    xDir = 0;
                    break;
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    yDir = -1;
                    break;
                case KeyEvent.VK_DOWN:
                    yDir = 1;
                    break;
                case KeyEvent.VK_LEFT:
                    xDir = -1;
                    break;
                case KeyEvent.VK_RIGHT:
                    xDir = 1;
                    break;
            }
            System.out.println("camX:"+camX+" camY:"+camY);
        }
        
    }
    
    
    
    public static void main(String[] args) {
        Basics b = new Basics();
        ReusableFrame f = new ReusableFrame(b,"Demo",false);
        f.setResizable(false);
        f.setVisible(true);
    }

}
