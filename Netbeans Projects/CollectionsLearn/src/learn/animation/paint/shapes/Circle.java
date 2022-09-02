package learn.animation.paint.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Circle {

    private Point center;
    private int radius;

    public Circle(String name, Point center, int radius) {
        this.radius = radius;
        this.center = center;

    }

    public void draw(Graphics2D g, Color color, BasicStroke stroke) {
        g.setColor(color);
        g.setStroke(stroke);
        g.drawOval(center.x - radius, center.x - radius, radius * 2, radius * 2);
    }

    public void drawRadius(Graphics2D g, int angle,Color color,BasicStroke stroke) {
        g.setColor(color);
        g.setStroke(stroke); 
        int x, y;
        double rad = Math.toRadians(angle);

        x = (int) (radius * Math.cos(rad));
        y = (int) (radius * Math.sin(rad));

        g.drawLine(center.x, center.y, center.x + x, center.y + y);
    }

    public void fill(Color color, Graphics g) {
        g.setColor(color);
        g.fillOval(center.x - radius, center.x - radius, radius * 2, radius * 2);
    }



}
