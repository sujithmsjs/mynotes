package com.suji.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class HintGenerator {

    public static String makeHint(String str) {
        Random ran = new Random();
        String hint = str.replaceAll(".", "-");

        int hints = str.length() / 2;

        System.out.println(hint);
        StringBuilder sb = new StringBuilder(hint);

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < hints; i++) {
            while (true) {
                int n = ran.nextInt(sb.length());
                if (set.add(n)) {

                    sb.replace(n, n + 1, str.charAt(n) + "");
                    System.out.println(set);
                    break;

                }
            }
        }
        return sb.toString();
    }
}
