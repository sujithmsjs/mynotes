package com.suji.fun;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class BiblePromises {

    private static final int TOTAL_PROMS = 336;

    public static void main(String[] args) {
        String name = "sujith".toUpperCase();
        int month = 9;
        int day = 9;

        int n = getNumber(name, month, day);
        System.out.println(n);
    }

    public static int getNumber(String name, int month, int day) {
        name = name.toUpperCase().trim();
        LocalDate dob = LocalDate.of(1994, month, day);
        LocalDate now = LocalDate.of(2022, 1, 1);
        int doy = dob.getDayOfYear();
        int ny = now.getDayOfYear();

        int sum = 0;
        for (int i = 0; i < name.length(); i++) {

            sum += name.charAt(i) - 64;

        }
        int t = (sum * month) + doy + ny;//sum+m+d;
        return ((t % 335) + 1);
    }

    private static void m1() {
        String name = "Sujith".toUpperCase();

        int m = 4;
        int d = 1;
        int sum = 0;
        for (int i = 0; i < name.length(); i++) {
            System.out.println(name.charAt(i) - 64);
            sum += name.charAt(i) - 64;
        }
        int t = 1;//sum+m+d;
        System.out.println(t);
        System.out.println((t % 335) + 1);
    }
}
