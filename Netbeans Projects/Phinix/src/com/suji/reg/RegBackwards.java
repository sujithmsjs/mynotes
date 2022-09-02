package com.suji.reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegBackwards {

    public static void main(String[] args) {
        String reg = "[A-Z][a-z]+";
        //String reg = "[A-Z]\\w+";
        String text = "Suji Ammu SumaSuni Sumanth";
        for (int i = 0; i < text.length(); i++) {
            System.out.println("index "+i+" : "+text.charAt(i));
        }
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(text);
        Matcher m2 = m.region(1,text.length());
        while(m2.find()){
            System.out.println(m2.group());
        }
        
        
    }

}
