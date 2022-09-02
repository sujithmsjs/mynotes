package com.suji.str;


public class StringBuilderTest {

    public static void main(String[] args) {
        String str = "0123456789";
        StringBuilder sb = new StringBuilder(str);
        
        sb.insert(0, "x");
        System.out.println(sb); //x123456789
        sb.deleteCharAt(0);
        System.out.println(sb); //123456789
        
     
        System.out.println(sb.indexOf("6"));
        
        
        System.out.println(sb.charAt(9));
        
        
        
        
        
        
        
        
        
        
        
        
    }

}
