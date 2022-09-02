package learn.str;

import java.util.stream.IntStream;
import learn.kws.fnl.Rect;


public class StringDemo {

    public static void main(String[] args) {
        String str = "Sujith Manchala";
        
        int indexOf = str.indexOf("Sujt");
        String intern = str.intern();
        System.out.println("intern = " + intern);
    
        
    
    }   

}
