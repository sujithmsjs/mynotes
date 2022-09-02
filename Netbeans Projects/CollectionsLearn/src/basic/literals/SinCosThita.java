package basic.literals;


public class SinCosThita {
    public static void main(String[] args) {
        int angle = 90;
        int radius = 3;
        
        double x = radius * Math.sin(Math.toRadians(angle));
        System.out.println(x);
        double y = radius * Math.cos(Math.toRadians(angle));
        System.out.println(y);
    }

}
