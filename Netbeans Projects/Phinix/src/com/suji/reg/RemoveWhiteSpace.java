package com.suji.reg;


public class RemoveWhiteSpace {

    public static void main(String[] args) {
        String text = "       This        is      Sujith     .     \n"
                + "         This    \n"
                + "			\n"
                + " Hi 	\t\t\t		\n"
                + "	how		\n."
                + "	 \t \t		\n"
                + "	are		\n"
                + "  you?"
                + "	\t	is   				 My\n"
                + "	"
                + "	\n"
                + "		company   .    .     .\n"
                + "			\n"
                + "			Have    you  					 ever   			   \n"
                + "			\n"
                + "			\n"
                + "	found      my     laptop    ?      ??    ";
   
    
        String output = text.replaceAll("[\\s]+", " ").replaceAll("([\\s]*[.])+", ".").replaceAll("([\\s]*[?])+", "?\n");
                
                //replaceAll("([\\s]+[.]+)+", ".").replaceAll("([?]*[\\s]+[?]+)+", "?");
        System.out.println(output);
    
    
    }
    
        
    
    

}
