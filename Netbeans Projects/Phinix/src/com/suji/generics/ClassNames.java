package com.suji.generics;


public class ClassNames {

    
    
    public static void printNamesForClass(final Class<?> clazz, final String label) {
        System.out.println(label + ":");
        System.out.println("    getName():          " + clazz.getName());
        System.out.println("    getCanonicalName(): " + clazz.getCanonicalName());
        System.out.println("    getSimpleName():    " + clazz.getSimpleName());
        System.out.println("    getTypeName():      " + clazz.getTypeName()); // added in Java 8
        System.out.println();
    }
    
    public static void main(String[] args) {
        printNamesForClass(
                int.class,
                "int.class (primitive)");
        printNamesForClass(
                String.class,
                "String.class (ordinary class)");
        printNamesForClass(
                java.util.HashMap.SimpleEntry.class,
                "java.util.HashMap.SimpleEntry.class (nested class)");
        printNamesForClass(
                new java.io.Serializable() {
                }.getClass(),
                "new java.io.Serializable(){}.getClass() (anonymous inner class)");
    }

}
