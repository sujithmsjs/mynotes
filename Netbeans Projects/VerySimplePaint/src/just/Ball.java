package just;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.util.List;

public class Ball extends Sprite implements ShapesLoader{

    public Ball() {
        loadShapes(this);
    }

    @Override
    public void initShapes(List<MShape> shapes) {
        Rectangle re7 = new Rectangle(155, 62, 248, 248);
        shapes.add(new MShape(re7, new Color(64, 64, 64)));

        Ellipse2D e = new Ellipse2D.Double(155.0, 62.0, 248.0, 248.0);
        shapes.add(new MShape(e, new Color(153, 0, 255)));

        Path2D tri = new Path2D.Double();
        tri.moveTo(155.0, 186.0);
        tri.lineTo(403.0, 186.0);
        tri.lineTo(279.0, 310.0);
        tri.lineTo(279.0, 62.0);
        tri.closePath();
        shapes.add(new MShape(tri, new Color(255, 255, 0)));
    }
}

