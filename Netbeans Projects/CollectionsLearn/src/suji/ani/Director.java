package suji.ani;

import java.awt.Point;
import java.util.ArrayList;

public class Director extends Point {

    public static final Point STOP = new Point(0, 0);

    public static final Point UP = new Point(0, -1);
    public static final Point DOWN = new Point(0, 1);
    public static final Point LEFT = new Point(-1, 0);
    public static final Point RIGHT = new Point(1, 0);

    public static final Point TOP_LEFT = new Point(-1, -1);
    public static final Point TOP_RIGHT = new Point(1, -1);
    public static final Point DOWN_LEFT = new Point(-1, 1);
    public static final Point DOWN_RIGHT = new Point(1, 1);

    private static final ArrayList<Point> directions;
    private int directionCode = 0; // DIRECTION CODE IS FROM 0 TO 8;

    static {
        directions = new ArrayList<>();
        directions.add(UP);
        directions.add(TOP_RIGHT);
        directions.add(RIGHT);
        directions.add(DOWN_RIGHT);
        directions.add(DOWN);
        directions.add(DOWN_LEFT);
        directions.add(LEFT);
        directions.add(TOP_LEFT);
     //   directions.add(STOP);
    }
    


    public Director(Point direction) {
        
        directionCode = directions.indexOf(direction);
        
        if(directionCode==-1){
            directionCode = 8;
        }
        
        System.out.println(directionCode);
        
    }

    
    public void setDirection(int directionCode) {
        
        x = directions.get(directionCode).x;
        y = directions.get(directionCode).y;
        this.directionCode = directionCode;
        
    }

    public void setDirection(Point direction) {
        directionCode = directions.indexOf(direction);
        x = directions.get(directionCode).x;
        y = directions.get(directionCode).y;
        this.directionCode = directionCode;
    }

    public Point getDirectors() {
        return directions.get(directionCode);
    }

    public void turnSlightLeft() {
        
        directionCode--;
       
        if(directionCode<0) {
            directionCode = 7;
        }
                    
        setDirection(directionCode);
    }

    public void turnSlightRight() {
        directionCode = (directionCode + 1) % directions.size();
        
        setDirection(directionCode);
    }

}
