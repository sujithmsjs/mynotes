package com.suji.generics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


class WildcardError {

   static  void foo(List<?> l) {
       for (int i = 0; i < 10; i++) {
           fooHelper(l);
           // Object can't be converted int CAP#1
        // reason: actual argument Object cannot be converted to CAP#1 by method invocation conversion
       }   
    }
   static <T> void fooHelper(List<T> list){
       list.set(0, list.get(0));
   }
   
}



public class NewMain {
    public static void main(String[] args) {
      
        List<? extends Number> list = new ArrayList<>();
        WildcardError.foo(list);
        
        
    }
}
