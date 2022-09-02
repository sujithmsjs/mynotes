package com.suji.coll;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class TreeDemo {

    public static void main(String[] args) {
        Set<Box> set = new LinkedHashSet<>();
        set.add(new Box(5,10));
        set.add(new Box(5,10));
        set.add(new Box(10,5));
        set.add(new Box(11,10));
        set.add(null);
        set.add(null);
        set.add(new Box(10,5));
        set.add(new Box(5,10));
        set.add(new Box(10,5));
        set.add(new Box(11,10));
        
        set.add(new Box(11,10));
        
        System.out.println(set);
        
    }

}
