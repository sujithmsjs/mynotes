package com.suji.str;

import java.util.StringTokenizer;


public class IndexOfDemo {

    public static void main(String[] args) {
        String str = ">Sujit>hSujit>h>";
        String rp = "\nHey :\n";
        StringBuilder sb = new StringBuilder(str);
        
        
        int n = 0;
        
        while((n = sb.indexOf(">", 0)) != -1){
            
            
            //sb.insert(n, rp);
            sb.replace(n, n+1, rp);
            System.out.println(n);
            n++;
        }
        
        
        for (int i = 0; i < str.length(); i++) {
            System.out.println("Index "+ i+" :"+str.charAt(i));
        }

        System.out.println(sb);
//        
//        System.out.println(str.indexOf('S',-7));
//        
//        System.out.println(str.lastIndexOf("S", 7));
    }

}
