package com.suji.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


public class StringProvider {

    
    public static ListIterator<String> getString(int offset){
        List<String> list = new ArrayList<>();
        int limit = 10;
        int start = offset*limit;
        int end = start+limit;
        for (int i = start; i < end; i++) {
            list.add("Word: "+i);
        }
        
        return list.listIterator();
    }

}
