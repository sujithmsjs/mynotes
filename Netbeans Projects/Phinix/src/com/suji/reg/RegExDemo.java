package com.suji.reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.util.regex.MatchResult;

public class RegExDemo {

    public static void main(String[] args) {
        Pattern p = Pattern.compile("([A-Z])\\w+");
        
        
        String input = "Hey this is Sujith2. manchala Hellow";
        for (int i = 0; i < input.length(); i++) {
            System.out.println("Index " + i + " : " + input.charAt(i));
        }

        Matcher m = p.matcher(input);
        
        while(m.find()){
            System.out.println(m.group()+" : "+m.start()+" : "+m.hasAnchoringBounds()+" : "+m.hasTransparentBounds());
            
        }
        System.out.println(m.replaceAll("*"));
       
        
        /*
        Scanner scan = new Scanner(System.in);
        
        
        int n = 0;
        do {
            System.out.println("1. Exit 2.Next 3.Prev");
            n = scan.nextInt();

            switch (n) {
                case 2:

                    break;

                case 3:
                    break;
            }

            }while (n != 1);
         */
        //System.out.println(m.group()+" "+m.end());
    }

}
