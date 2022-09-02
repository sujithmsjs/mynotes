package com.suji.str;

import java.util.StringTokenizer;


public class StringTokenizerDemo {

    public static void main(String[] args) {
        
        String str = "Hey this is Sujith Manchala!";
        
        //First String and Delim.
        StringTokenizer tokenizer = new StringTokenizer(str, " ");
        
        
        while(tokenizer.hasMoreTokens()){
            System.out.println(tokenizer.nextToken());
        }
        
    }

}
