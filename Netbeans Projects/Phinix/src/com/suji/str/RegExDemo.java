package com.suji.str;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegExDemo {

    public static void main(String[] args) {
        Pattern p = Pattern.compile("[^\\d]+");
        Matcher m = p.matcher("ddfsf4");
        

        if(m.matches()){
            System.out.println("It's valid");
        }else{
            System.out.println("It's invalid");
        }
    }

}
