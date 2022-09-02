package learn.animation.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;
import java.awt.geom.Rectangle2D;

public class BGUtil {

    public static final GradientPaint g = new GradientPaint(-360, 305, new Color(-1), -725, 845, new Color(-5304283), true);
    public static final GradientPaint g2 = new GradientPaint(-360, 305, new Color(-1), -725, 845, new Color(-806062), true);
    public static final GradientPaint g3 = new GradientPaint(-360, 305, new Color(-15751108), -725, 845, new Color(-4738053), true);
    public static final GradientPaint g4 = new GradientPaint(-360, 305, new Color(-8732980), -725, 845, new Color(-13674027), true);
    public static final GradientPaint g5 = new GradientPaint(-360, 305, new Color(-1589125), -725, 845, new Color(-1156016), true);
    public static final GradientPaint BLACK_BLUE = new GradientPaint(1270, 640, new Color(60, 62, 236), 0, 0, new Color(60, 62, 236), true);
    public static final GradientPaint g6 = new GradientPaint(-365, -255, new Color(-16127814), 630, 45, new Color(-11106667), true);
    public static final GradientPaint g7 = new GradientPaint(0, 390, new Color(63, 7, 91), 0, 0, new Color(63, 7, 91), true);
    public static final GradientPaint SIMPLE = new GradientPaint(1270, 650, new Color(15, 8, 93), 0, 0, new Color(15, 8, 93), true);

    public static final GradientPaint SIMPLE_PAPER = new GradientPaint(new Point2D.Double(100.0, 90.0), new Color(55, 154, 51), new Point2D.Double(727.0, 849.0), new Color(55, 154, 51));

    public static final GradientPaint getGradientPaint(Color foreColor, Color backColor) {
        GradientPaint gradientPaint = new GradientPaint(-360, 305, foreColor, -725, 845, backColor, true);
        return gradientPaint;
    }

    public static Paint getRadialGradientPaint(double x, double y, double width, double height) {
        
        Rectangle2D rect = new Rectangle2D.Double(x, y, width, height);
        return  getRadialGradientPaint(rect);
        
    }

    public static Paint getRadialGradientPaint(int x, int y, Dimension dimension) {
        
        Rectangle2D rect = new Rectangle2D.Double(x, y, dimension.width, dimension.height); 
        return getRadialGradientPaint(rect);
        
    }
    
    public static Paint getRadialGradientPaint(RectangularShape rect) {
        
        double x = rect.getX();
        double y = rect.getY();
        
        double w = rect.getWidth();
        double h = rect.getHeight();
        
        Point2D.Double center = new Point2D.Double(x+w/2, y+h/2);

        double radius = w/2;
        
        float[] dist = {0.1f, 1f};

        Color[] colors = {Color.GREEN, new Color(0,0,0,255/2)};

        return new RadialGradientPaint(center, (float) radius, dist, colors);
    }

    public static Paint getReadialBG(){
        return null;
    }

    
    
}
