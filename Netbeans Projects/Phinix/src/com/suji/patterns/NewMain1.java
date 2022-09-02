package com.suji.patterns;


public class NewMain1 {

    public static void main(String[] args) {
        //Diamond
        int n =10; 


        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                System.out.print(" *");
            }
            System.out.println();
        }

        // 7 starts at top, 7 sarts at right.
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                System.out.print(" *");
            }
            System.out.println();
        }
    }

}
