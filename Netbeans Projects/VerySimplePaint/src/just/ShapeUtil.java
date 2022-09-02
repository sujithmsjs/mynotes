package just;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;


public class ShapeUtil {

    public static Rectangle getRect(List<MShape> shapes) {
        System.out.println(shapes.size());
        Point max = findMax(shapes);
        Point min = findMin(shapes);
        int width = max.x - min.x;
        int height = max.y - min.y;
        return new Rectangle(min.x, min.y, width, height);
    }

    public static Point findMin(List<MShape> shapes) {
        Point min = new Point(0, 0);
        if (!shapes.isEmpty()) {
            min.x = shapes.get(0).getShape().getBounds().x;
            min.y = shapes.get(0).getShape().getBounds().y;
        }

        for (MShape shape : shapes) {
            Rectangle r = shape.getShape().getBounds();

            if (r.x < min.x) {
                min.x = r.x;
            }
            if (r.y < min.y) {
                min.y = r.y;
            }
        }
        return min;
    }

    public static Point findMax(List<MShape> shapes) {
        Point max = new Point(0, 0);
        if (!shapes.isEmpty()) {
            max.x = (int) shapes.get(0).getShape().getBounds().getMaxX();
            max.y = (int) shapes.get(0).getShape().getBounds().getMaxY();
        }

        for (MShape shape : shapes) {
            Rectangle r = shape.getShape().getBounds();

            if (r.getMaxX() > max.x) {
                max.x = (int) r.getMaxX();
            }
            if (r.getMaxY() > max.y) {
                max.y = (int) r.getMaxY();
            }
        }
        return max;
    }

}
