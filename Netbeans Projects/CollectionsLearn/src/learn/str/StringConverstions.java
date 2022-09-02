package learn.str;


public class StringConverstions {

    public static void main(String[] args) {
    
        String num = "123456i";
       // int n = Integer.parseInt(num);
       int n = 0;
        System.out.println(n);
        Integer n1 = n;
        n1 = 7;
        n = n1;
        
        Integer integer = Integer.valueOf(num);
        System.out.println("integer = " + integer);
        
        int s = integer.byteValue();
        
        
        
        
        
    }

    



}
