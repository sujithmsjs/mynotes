package com.suji.str;

import java.util.Scanner;
import java.util.regex.Pattern;


public class RegEx_Demo {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input ="";
        System.out.print("Enter RegEx: ");
        String regEx = scan.next();
        System.out.println();
        while(true){
            
            System.out.print("Enter Input: ");
            
            input = scan.next();
            if(input.equalsIgnoreCase("exit"))
                return;
            
            boolean p = Pattern.matches(regEx, input);
            
            System.out.println(p);
            
            
        }
        
        //
        
    }

}
