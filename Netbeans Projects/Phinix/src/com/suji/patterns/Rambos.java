package com.suji.patterns;


public class Rambos {

    public static void main(String[] args) {
        int n = 5;
        
        
        
        //Top-Left
        for (int i = 0; i < n; i++) {
            
            
            for (int j = i; j < n; j++) {
                System.out.print("__");
            }
            // n = 4, i = 0; rounds = j-n
                    
            for (int j = 0; j < i; j++) {
                System.out.print(" *");
            }
            
            
            for (int j = 0; j < i-1; j++) {
                System.out.print(" *");
            }
            System.out.println();
        }
        
        System.out.println("Page break");
        //Top left
        for (int i = 0; i < n; i++) {
            //Spacing
            for (int j = 0; j < i; j++) {
                System.out.print("__");
            }
            for (int j = i; j < n; j++) {
                System.out.print(" *");
            }
            System.out.println();
        }
        
        
        
        
        // Top- Right
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(" *");
            }
            System.out.println();
        }
        
        //Bottom - Right
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                System.out.print(" *");
            }
            System.out.println();
        }
        
        
    }

}
