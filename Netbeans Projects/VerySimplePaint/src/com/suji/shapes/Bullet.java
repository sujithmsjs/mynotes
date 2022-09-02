package com.suji.shapes;

import java.awt.Color;
import java.awt.geom.*;
import java.util.List;
import just.MShape;
import just.MShape;
import just.ShapesLoader;
import just.ShapesLoader;
import just.Sprite;
import just.Sprite;



public class Bullet extends Sprite implements ShapesLoader{

	public Bullet() {
		loadShapes(this);
	}

	@Override
	public void initShapes(List<MShape> shapes) {
		Ellipse2D bullethead = new Ellipse2D.Double(220.0, 200.0, 40.0, 40.0);
		shapes.add(new MShape(bullethead,new Color(255,0,51) ));

		Rectangle2D rect = new Rectangle2D.Double(120.0, 180.0, 120.0, 80.0);
		shapes.add(new MShape(rect,new Color(51,102,255) ));

		Rectangle2D bullet = new Rectangle2D.Double(200.0, 210.0, 40.0, 20.0);
		shapes.add(new MShape(bullet,new Color(255,255,255) ));
	}
}

class Bullet2 extends Bullet{
    
}