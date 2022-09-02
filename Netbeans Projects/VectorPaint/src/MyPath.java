
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class MyPath {

    ArrayList<Point> points;
    Path2D path = new Path2D.Double();

    MyPath() {
        points = new ArrayList<>();
    }

    public void draw(Graphics2D g) {
        path.reset();
        if (points.size() > 1) { // 0
            path.moveTo(points.get(0).x, points.get(0).y);
        }
        for (int i = 1; i < points.size(); i++) {
            path.lineTo(points.get(i).x, points.get(i).y);
        }
        if (points.size() >= 2) {

            //path.closePath();
            g.fill(path);
        }
    }

}
