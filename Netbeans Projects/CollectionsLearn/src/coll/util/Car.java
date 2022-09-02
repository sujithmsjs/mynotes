package coll.util;


public class Car {
    
    
    
    public class Engine{
        
    } 
    
    public static void main(String[] args) {
        String str = "hello";
        String hey = "hello";
        String w = hey;
        System.out.println("str = " + str.hashCode());
        System.out.println("hey = " + hey.hashCode());
        System.out.println("w = " + w.hashCode()) ;
    }

}
