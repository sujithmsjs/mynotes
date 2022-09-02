package com.suji.str;

public class Remove_Spaces {

    public static void main(String[] args) {

        String str = "Hey    this isss    sujith       Manchala".trim();
        System.out.println(str.length());

        StringBuilder sb = new StringBuilder(str);

        for (int i = 0; i < sb.length(); i++) {
            
            //Encounter first space
            if(sb.charAt(i) == 32){
                // Step ++ and delete if it is space
                System.out.println("First space : "+i);
                
                for (i++; i < sb.length(); i++) {
                    
                    if(sb.charAt(i) == 32){
                        
                    System.out.println("Distine to delete : "+i);
                        sb.deleteCharAt(i);
                        i--;
                    }else{
                    
                        break;
                        
                    }
                }
            }
            System.out.println("Text : "+i);
        }
        System.out.println(sb);

        
    }
}
