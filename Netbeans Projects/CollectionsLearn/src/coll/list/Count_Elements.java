package coll.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import util.obj.ReadyData;

public class Count_Elements {

    public static void main(String[] args) {
        ArrayList<Integer> al = ReadyData.getRandomIntsAL(20);
        System.out.println(al);
        Set<Integer> set = new LinkedHashSet<>(al);
        
        System.out.println("Total unique items :"+set.size());
        for (Integer integer : set) {
            System.out.println(integer+" : "+Collections.frequency(al, integer));
        }
    }
    
}
