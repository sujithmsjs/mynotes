package com.suji.shapes;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.util.List;
import just.MShape;
import just.ShapesLoader;
import just.Sprite;

public class FireBall extends Sprite implements ShapesLoader {

    public FireBall() {
        loadShapes(this);
    }

    @Override
    public void initShapes(List<MShape> shapes) {
        Rectangle re7 = new Rectangle(110, 60, 460, 460);
        shapes.add(new MShape(re7, new Color(64, 64, 64)));

        Ellipse2D demo = new Ellipse2D.Double(160.0, 140.0, 260.0, 260.0);
        shapes.add(new MShape(demo, new Color(255, 255, 255)));

        Path2D fire2 = new Path2D.Double();
        fire2.moveTo(140.0, 250.0);
        fire2.lineTo(160.0, 210.0);
        fire2.lineTo(170.0, 200.0);
        fire2.lineTo(190.0, 170.0);
        fire2.lineTo(210.0, 150.0);
        fire2.lineTo(250.0, 120.0);
        fire2.lineTo(280.0, 110.0);
        fire2.lineTo(290.0, 110.0);
        fire2.lineTo(320.0, 120.0);
        fire2.lineTo(350.0, 130.0);
        fire2.lineTo(380.0, 150.0);
        fire2.lineTo(460.0, 150.0);
        fire2.lineTo(430.0, 180.0);
        fire2.lineTo(430.0, 190.0);
        fire2.lineTo(440.0, 210.0);
        fire2.lineTo(480.0, 230.0);
        fire2.lineTo(470.0, 280.0);
        fire2.lineTo(470.0, 310.0);
        fire2.lineTo(400.0, 360.0);
        fire2.lineTo(390.0, 360.0);
        fire2.lineTo(340.0, 400.0);
        fire2.lineTo(320.0, 430.0);
        fire2.lineTo(270.0, 460.0);
        fire2.lineTo(210.0, 450.0);
        fire2.lineTo(220.0, 430.0);
        fire2.lineTo(190.0, 400.0);
        fire2.lineTo(170.0, 380.0);
        fire2.lineTo(170.0, 340.0);
        fire2.lineTo(160.0, 300.0);
        fire2.lineTo(150.0, 290.0);
        fire2.closePath();
        shapes.add(new MShape(fire2, new Color(255, 255, 0)));

        Path2D Fire = new Path2D.Double();
        Fire.moveTo(360.0, 140.0);
        Fire.lineTo(510.0, 110.0);
        Fire.lineTo(440.0, 200.0);
        Fire.lineTo(570.0, 210.0);
        Fire.lineTo(520.0, 260.0);
        Fire.lineTo(510.0, 370.0);
        Fire.lineTo(380.0, 380.0);
        Fire.lineTo(360.0, 450.0);
        Fire.lineTo(260.0, 520.0);
        Fire.lineTo(210.0, 490.0);
        Fire.lineTo(140.0, 470.0);
        Fire.lineTo(150.0, 390.0);
        Fire.lineTo(140.0, 360.0);
        Fire.lineTo(110.0, 260.0);
        Fire.lineTo(150.0, 190.0);
        Fire.lineTo(190.0, 150.0);
        Fire.lineTo(260.0, 60.0);
        Fire.lineTo(260.0, 80.0);
        Fire.lineTo(360.0, 80.0);
        Fire.closePath();
        shapes.add(new MShape(Fire, new Color(255, 51, 0)));

    }
}
