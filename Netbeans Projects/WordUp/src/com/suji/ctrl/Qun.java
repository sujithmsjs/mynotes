package com.suji.ctrl;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class Qun {
    
    private String qun;
    private String hint;
    private String ans;
    
    
    
    
    
    

    public static String main(String str) {
        Random ran = new Random();
        String hint = str.replaceAll(".", "-");
        
        int hints = str.length()/2;   
        
        System.out.println(hint);
        StringBuilder sb = new StringBuilder(hint);
        
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < hints; i++) {
            while (true){
                int n = ran.nextInt(sb.length());
                if( set.add(n) ){
                    
                    sb.replace(n, n+1, str.charAt(n)+"");
                    System.out.println(set);
                    break;
                
                }
            }
        }
        return sb.toString(); 
    }
    
    
    public static String closeWord(String str){
        int hints = 0;
        
        if (str.length() < 5) {
            hints = 2;
        } else if (str.length() < 8) {
            hints = 3;
        } else {
            hints = 4;
        }
        
        Random ran = new Random();
        
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < hints; i++) {
            while(true){
                if(!set.add(ran.nextInt(str.length()))){
                    break;
                }
            }
        }
        System.out.println(set);
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < str.length(); i++) {
            if(set.contains(i)){
                 sb.append(" _ ");
            }else{
               
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }

}
