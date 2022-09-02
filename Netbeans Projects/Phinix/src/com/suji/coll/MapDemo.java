package com.suji.coll;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class MapDemo {
    public static void main(String[] args) {
        Map<Integer,Integer> map = new HashMap<>();
        map.put(1, 200);
        map.put(2, 300);
        map.put(3, 3434);
        map.put(4, 2034340);
        System.out.println(map);
        int n = map.get(4);
        System.out.println(n);
        
         Set<Entry<Integer,Integer>> set =  map.entrySet();
         for (Entry<Integer, Integer> entry : set) {
             System.out.println(entry.getKey()+" - "+entry.getValue());
        }
    }
}
