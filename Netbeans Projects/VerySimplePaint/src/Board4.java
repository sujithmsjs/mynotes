
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.geom.Rectangle2D;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Board4 extends Canvas implements ActionListener {

    public Board4() {
        draw();
        t = new Timer(DELAY,this);
        t.start();
    }
    int angle = 0;

    Timer t;
    int DELAY = 10;
    Rectangle2D rect;
    Shape shape;
    
    private void draw() {
        rect = new Rectangle2D.Double(100, 100, 100, 100);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(0x1234567));
        g2d.draw(shape.getBounds2D());
        g2d.fill(shape);
    }
    
    public static void main(String[] args) {
        Board4 canvas = new Board4();
        MyFrame frame = new MyFrame(canvas, "Very Simple Painting");
        frame.add(canvas);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       shape = Util.getShape(rect, 20, 20, 200, 200, angle++);
       
       repaint();
    }
}
