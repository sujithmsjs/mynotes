
import java.awt.Point;
import java.awt.geom.Rectangle2D;


public class VerySimplePaint {

    public static void main(String[] args) {
        
    }
    
    
    private Rectangle2D getPointRect() {
        int xLeftGap = mouseMouedTo.x % UNIT_SIZE; //Before Gap
        int xRightGap = UNIT_SIZE - xLeftGap;
        int x = (xLeftGap < xRightGap) ? mouseMouedTo.x - xLeftGap : mouseMouedTo.x + xRightGap;
        //    int x = mouseMouedTo.x - xS; 
        //   int x2 = UNIT_SIZE-x; 
        int yTopGap = mouseMouedTo.y % UNIT_SIZE; //Before Gap
        int yBottomGap = UNIT_SIZE - yTopGap;
        int y = (yTopGap < yBottomGap) ? mouseMouedTo.y - yTopGap : mouseMouedTo.y + yBottomGap;

        return new Rectangle2D.Double(x - 10, y - 10, 20, 20);
    }

    private Point getExactPoint(Point p) {

        int xLeftGap = p.x % UNIT_SIZE; //Before Gap
        int xRightGap = UNIT_SIZE - xLeftGap;
        int x = (xLeftGap < xRightGap) ? p.x - xLeftGap : p.x + xRightGap;
        //    int x = mouseMouedTo.x - xS; 
        //   int x2 = UNIT_SIZE-x; 
        int yTopGap = p.y % UNIT_SIZE; //Before Gap
        int yBottomGap = UNIT_SIZE - yTopGap;
        int y = (yTopGap < yBottomGap) ? p.y - yTopGap : p.y + yBottomGap;

        return new Point(x, y);
    }

}
