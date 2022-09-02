package com.suji.reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegDemo {

    public static void main(String[] args) {
        Pattern ptrn = Pattern.compile("[\\w]{2}");
        Matcher mat = ptrn.matcher("a aaabaaca");
        int countMat = 0;
        while(mat.find()){
            countMat++;
            System.out.println( mat.start()+"-"+mat.end()+" : "+mat.group()+": "+" : "+mat.hasTransparentBounds());
        }
        
        
        
        System.out.println("Matches : "+countMat);
       
        
        
    }

}
