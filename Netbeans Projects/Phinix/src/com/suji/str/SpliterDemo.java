package com.suji.str;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SpliterDemo {

    public static void main(String[] args) {
        Pattern p = Pattern.compile("[^\\d]+");
        Matcher m = p.matcher("abc2def3565hij4jkls3e654656");
        while (m.find()) {
            System.out.println("["+m.group()+"]");
        }
    }

}
