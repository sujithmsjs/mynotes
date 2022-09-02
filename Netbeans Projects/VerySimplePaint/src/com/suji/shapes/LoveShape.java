package com.suji.shapes;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.util.List;
import just.MShape;
import just.ShapesLoader;
import just.Sprite;


public class LoveShape extends Sprite implements ShapesLoader {

    public LoveShape() {
        loadShapes(this);
    }

    @Override
    public void initShapes(List<MShape> shapes) {
        Path2D tri = new Path2D.Double();
        tri.moveTo(120.0, 280.0);
        tri.lineTo(360.0, 280.0);
        tri.lineTo(240.0, 450.0);
        tri.closePath();
        shapes.add(new MShape(tri, new Color(255, 0, 51)));

        Ellipse2D c1 = new Ellipse2D.Double(110.0, 170.0, 140.0, 150.0);
        shapes.add(new MShape(c1, new Color(255, 0, 51)));

        Ellipse2D c2 = new Ellipse2D.Double(230.0, 170.0, 140.0, 140.0);
        shapes.add(new MShape(c2, new Color(255, 0, 51)));

    }

    
}
