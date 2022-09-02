package learn;


public class Rough {

    public static void main(String[] args) {
      //  int n = getDistance(9, 3, 7, 2);
       // System.out.println(n);

    }
    
    public static double getDistance(int x1, int x2, int y1, int y2){
        double x =  Math.pow(x1-x2, 2);
        double y =  Math.pow(y1-y2, 2);
        double z = (int) Math.round(Math.sqrt(x+y));
        return z;
    }

}
