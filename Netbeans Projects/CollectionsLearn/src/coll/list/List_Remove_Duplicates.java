
package coll.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class List_Remove_Duplicates {
    
    public static void main(String[] args) {
        Integer ar[] = new Integer[]{1,4,2,3,1,2,5,6,3,4,2,2};
        List<Integer> list = Arrays.asList(ar);
        System.out.println(list.add(6));
        
        //Set has no duplicate values.
        Set<Integer> set = new LinkedHashSet(list);
        System.out.println(set.getClass()); 
        /**
        Here Set Internally hold the LinkedHashSet object
        Set, HashSet and LinkedHashSet don't have methods other than derived methods from Collection interface.
        */
        System.out.println(set);
        
        
    }
    
}
