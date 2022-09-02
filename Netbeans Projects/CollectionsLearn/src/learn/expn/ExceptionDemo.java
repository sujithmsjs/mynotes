package learn.expn;

import java.util.logging.Level;
import java.util.logging.Logger;


public class ExceptionDemo {

    public static void main(String[] args) {
        Nt1 n1 = new Nt1();
        Nt2 n2 = new Nt2();
        
        try {
            n1.showEvenNumber(3);
        } catch (CheckedException ex) {
            System.out.println("ex = " + ex);
        }
        
        n2.showOddNumber(22);
        
    }

}


class Nt2{
    public void showOddNumber(int n){
        int remainder = n%2;
        if(remainder==0){
          //  throw new UnckeckedException("Even number not be shown here.");
        }else{
            System.out.println("Printing odd: "+n);
        }
        
        System.out.println("Clear");
    }
}

class Nt1{
    
    public void showEvenNumber(int n) throws CheckedException{
        int remainder = n%2;
        if(remainder==1){
           // throw new CheckedException("Odd number not be shown here.");
        }else{
            System.out.println("Printing Even: " + n);
        }
            
        
        System.out.println("Clear");
    }
    
    
}

class CheckedException extends Exception{

    public CheckedException(String message) {
   //     super(message);
    }    
}

class UnckeckedException extends RuntimeException{

    public UnckeckedException(String message) {
    //    super(message);
    }
    
}