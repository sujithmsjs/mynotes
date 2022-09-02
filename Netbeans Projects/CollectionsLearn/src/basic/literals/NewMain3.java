package basic.literals;


public class NewMain3 {

    public static void main(String[] args) {
        int n = 39;
        int m = -4;
        System.out.println(n/m);
        System.out.println(n%m);
        
        
    }
    
    public static void man(String[] args) {
        System.out.println(Byte.MAX_VALUE);
        System.out.println(Byte.MIN_VALUE);
       
        for (int i = 120; i < 300; i++) {
          
           int n = i%256> Byte.MAX_VALUE ? i%128+ Byte.MIN_VALUE : i%128;
           
            System.out.println(i+"; "+(i%-128));
           
           byte b = (byte) i;
            if(b!=n){
                System.out.println("b:"+b+"  n:"+n+"; i"+i);
                System.exit(0);
            }
        }
        
        
               
    } 
    
}
