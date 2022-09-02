
import java.awt.*;
import java.awt.geom.Path2D; 

public class Board2 extends Canvas {

    Path2D path;

    public Board2() {
       
        path = new Path2D.Double();
        path.moveTo(50, 50);
        path.lineTo(250, 50);
        path.lineTo(150,150 );
        path.closePath();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GREEN);
        g2d.fill(path);

    }
    
    void  movePath(){
        
    }

    public static void main(String[] args) {
        Board2 canvas = new Board2();
        MyFrame frame = new MyFrame(canvas, "Very Simple Painting");
        frame.add(canvas);
        frame.setVisible(true);
    }
}
