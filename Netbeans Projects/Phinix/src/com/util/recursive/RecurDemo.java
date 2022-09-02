package com.util.recursive;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

class A {
    private static int count;
    static void p() {
        count++;
        if (count <= 5) {
            System.out.println("hello " + count);
            p();
        }
    }
    
    static void m2(Class cls){
        if(cls != null){
            //Get classes and Interfaces.
            Class supCls = cls.getSuperclass();
            Class[] intrs = cls.getInterfaces();
            
            if(supCls != null){
                System.out.println(supCls);
                System.out.println("Interface "+cls);
                m2(supCls);
            }
            for (Class intr : intrs) {
                System.out.println(intr);
                m2(intr);
            }
        }else{
            Class[] c = cls.getInterfaces();
            for (Class cl : c) {
                m2(cl);
            }
            System.out.println("Class is null "+cls);
        }
    }
    
    
    private static int folders, files;
    static void m1(File file){
        if(file.exists()){
            if(file.isDirectory()){
                folders++; // Counting folders.
                System.out.println(file.getPath());
                
                File[] files = file.listFiles();
                for (File f : files) {
                    m1(f);
                }
                
            }else{
                System.out.println("\t"+file.getName());
            }
        }
    }
    static int getFolderCount(){
         return folders;
    }
}

    public class RecurDemo {

        public static void main(String[] args) {
          A.m2(LinkedList.class);
        }

    }
