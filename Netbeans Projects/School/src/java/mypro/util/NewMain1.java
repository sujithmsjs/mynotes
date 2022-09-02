/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypro.util;

import java.util.Stack;

/**
 *
 * @author sujit
 */
public class NewMain1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Stack<String> s = new Stack<String>();
        Token t = new Token(s);
        
        
        
        String str = "welcome";
        
        
        
        for(int i=1;i<=t.getTokensCount(str);i++){
             
             System.out.println(t.getToten(str, i));
        }
        
        
        // TODO code application logic here
    }
    
}
