
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;


public class Canvas extends JPanel {
    public static int UNIT_SIZE = 10;
    public static final Dimension SCREEN_SIZE = new Dimension(UNIT_SIZE*80,UNIT_SIZE*60);
    
    private boolean showGrid = true;
    private boolean showDots = true;
    
    
    
    public Canvas() {
        setBackground(Color.BLACK);
        setPreferredSize(SCREEN_SIZE);
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        if(showDots){
            drawDots(g2d);
        }
        if(showGrid){
            drawGridLines(g2d);
        }
        
    }
    
    private void drawGridLines(Graphics2D g) {
        g.setColor(Color.darkGray);
        
        for (int i = 0; i < SCREEN_SIZE.width / UNIT_SIZE; i++) {
            g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_SIZE.height);
        }

        for (int i = 0; i < SCREEN_SIZE.height / UNIT_SIZE; i++) {
            g.drawLine(0, i * UNIT_SIZE, SCREEN_SIZE.width, i * UNIT_SIZE);
        }
    }
    
    public void showGrid(boolean showGrid){
        this.showGrid = showGrid;
    }
    
    public boolean getGrid(){
        return showGrid;
    }
    
    public boolean getDots(){
        return showDots;
    }
    
    public void showDots(boolean showDots){
        this.showDots = showDots;
    }

    private void drawDots(Graphics2D g) {

        final int CS = UNIT_SIZE / 4;

        int xS = -CS / 2;
        int yS = -CS / 2;

        int xPoint = SCREEN_SIZE.width / UNIT_SIZE;
        int yPoint = SCREEN_SIZE.width / UNIT_SIZE;

        for (int i = 0; i <= xPoint; i++) {
            int x = xS + (i * UNIT_SIZE);

            for (int j = 0; j <= yPoint; j++) {
                int y = yS + (j * UNIT_SIZE);
                Rectangle2D rect = new Rectangle2D.Double(x, y, CS, CS);
                g.fill(rect);
            }
        }
    }
}
