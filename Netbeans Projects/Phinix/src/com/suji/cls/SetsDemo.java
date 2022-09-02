package com.suji.cls;

import java.util.HashSet;
import java.util.Set;


public class SetsDemo {

    public static void main(String[] args) {
        Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);set1.add(2);
        set1.add(3);set1.add(3);
        set1.add(4);set1.add(4);
        set1.add(5);
        
        Set<Integer> set2 = new HashSet<>();
        set2.add(4);
        set2.add(5);set2.add(5);
        set2.add(6);set2.add(6);
        set2.add(7);set2.add(7);
        set2.add(8);
        
        System.out.println(set2); //No duplicates
        
        //Union
       // set2.addAll(set1);
       // System.out.println(set2);
        
        //Set2 - Set1
        //set2.removeAll(set1);
        //System.out.println(set2);
        
        //Intersection
        //set1.retainAll(set2);
        set2.retainAll(set1);
        System.out.println(set2);
    }

}
