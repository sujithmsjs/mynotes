package coll.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import util.obj.ReadyData;

public class Reverse_List {

    public static void main(String[] args) {
        Integer[] is = ReadyData.getRandomInts(5);
        ArrayList<Integer> al = new ArrayList<>(Arrays.asList(is));
        System.out.println(al);
        
        Collections.reverse(al);
        System.out.println("After revers the list:");
        System.out.println(al);
    }
    
}
