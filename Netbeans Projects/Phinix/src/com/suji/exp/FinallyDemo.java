package com.suji.exp;


public class FinallyDemo {

    public static void main(String[] args) {
        try{
            
            System.out.println(5/0);
            
        }finally{
            System.out.println("Finally Block");
        }
    
        System.out.println("Program executed successfully.");
    
    }
    

}
