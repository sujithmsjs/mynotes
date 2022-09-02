package java8;

import java.util.*;
import java.util.function.Consumer;


public class LamdaCollections {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        
        list.forEach(
                (n) -> {
                    if(n%2==0) {
                        list.remove(n);
                    }
                }
        );
        
        System.out.println(list);
        
        
    }

}
