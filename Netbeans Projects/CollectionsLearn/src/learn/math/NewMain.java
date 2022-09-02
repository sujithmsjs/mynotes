package learn.math;


public class NewMain {

    public static void main(String[] args) {
        double adj = 5;
        double angle = 65;
        double rad = Math.toRadians(65);
        
        double opp =  adj * Math.tan(rad);
        System.out.println("OppositeSize = " + opp);
    
        double hyp = Math.sqrt(Math.pow(adj,2)+Math.pow(opp,2));
        System.out.println("hyp = " + hyp);
    
        double hyp2 = opp / Math.sin(rad);
        System.out.println("hyp2 = " + hyp2);
        
        //y is adj
        double x = hyp * Math.sin(rad);
        System.out.println("x = " + x);
        double y = hyp * Math.cos(rad);
        System.out.println("y = " + y);
        
    }

}
