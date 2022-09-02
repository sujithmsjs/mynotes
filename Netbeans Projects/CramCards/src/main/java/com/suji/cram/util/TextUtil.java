package com.suji.cram.util;

public class TextUtil {

    public static String removeSpaces(String str) {
        return str.replaceAll("\\s+", " ").trim();
    }
    
    public static String clean(String str){
        return upper(removeSpaces(str));
    }

    public static String upper(String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

}
