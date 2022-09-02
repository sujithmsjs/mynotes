/* Program to sort out not 2 and 3 multipliers in an Array */

import java.util.stream.IntStream;
import java.util.List;
import java.util.stream.Collectors;


public class MultipliersDemo{
	public static void main(String[] arge){
		
	IntStream is =IntStream.rangeClosed(100,200);	
	
	
	IntStream is2 = is.filter( n -> n%2==0 && n%3==0);
	List<Integer> list = is2.boxed().collect(Collectors.toList());

	/*
	is2.forEach( i-> System.out.println("> "+i));
	Note:
IllegalStateException: stream has already been operated upon or closed
	*/
	System.out.println(list);
}
}

