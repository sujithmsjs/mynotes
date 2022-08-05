import java.util.stream.*;
import java.util.OptionalDouble;


public class RangeDemo{
    public static void main(String[] args) {
        
	IntStream is= IntStream.range(0,100).filter(f -> f%2==0 && f%3==0);
	//is.forEach( i -> System.out.println(i));        
	OptionalDouble d = is.average();    
	System.out.println(d.getAsDouble());	
	System.out.println(d.orElse(0));	
		
}
}


/*

Stream: a single-use sequence of data 
Note:
A Stream should be operated on (invoking an intermediate or terminal stream operation) only once. A Stream implementation may throw IllegalStateException if it detects that the Stream is being reused.

Whenever a terminal operation is called on a Stream object, the instance gets consumed and closed.

*/