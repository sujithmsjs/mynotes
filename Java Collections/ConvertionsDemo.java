
// int Array to List<Integer>
// List<Integer> to int Array
// List<Integer> to Set<Integer>
// Set<Integer> to List<Integer>

import java.util.*;
import java.util.stream.*;


public class ConvertionsDemo{

	public static void main(String[] args){

		int[] arr = new int[]{1,2,3,4,5};

		// List is mutable.
		List<Integer> list = Arrays.stream(arr)
			.boxed()
			.collect(Collectors.toList());
	
		list.add(10);
		System.out.println(list);	
		Integer[] ls = list.toArray(new Integer[0]);


		System.out.println(ls);
	}
}

/*

Naive solution: a regular for-loop to add elements

*/