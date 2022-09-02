package com.suji.exp;

import java.util.logging.Level;
import java.util.logging.Logger;



public class Exp {

    public static void main(String[] args) {
      
        Account acc = new Account();
        boolean flag = true;
        try {
            
            acc.checkPassword("Sujith", "1234");
            
        } catch (CheckPasswordException ex) {
            flag = false;
            System.out.println("Wrong Password, try again."); 
        }
        
        System.out.println(flag);
       
    }

}
