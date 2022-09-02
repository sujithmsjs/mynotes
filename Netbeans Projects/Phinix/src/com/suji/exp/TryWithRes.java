package com.suji.exp;

import java.io.Closeable;
import java.io.FileWriter;
import java.io.IOException;

class A implements AutoCloseable{

    public void m1(){
        System.out.println("M1 method.");
    }
    
    @Override
    public void close() throws IOException {
        
        System.out.println("This is inside close method.");
     
    }
  
    
}


public class TryWithRes {

    public static void main(String[] args) {
        try(A a = new A()){
            a.m1();
        } catch (IOException ex) {
            System.out.println("ex: "+ex.getMessage());
        }
    }

}
