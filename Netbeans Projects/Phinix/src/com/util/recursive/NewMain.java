package com.util.recursive;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;
import java.util.PriorityQueue;
import java.util.Properties;

public class NewMain {

    public static void main(String[] args) {
        
        
        Class mainCls = Arrays.class;
        
        
        ClassDesc cd = new ClassDesc(mainCls);
        Set<Class> classes = cd.getClasses();
        classes.add(mainCls);
        
        for (Class cls : classes) {
             showMethods(cls);
        }
        
        
    }
    
    public static void showMethods(Class mainClass){
        
       
        Set<Met> mainMethods = ExtMet.getAllMethods(mainClass);
        System.out.println("\n"+mainClass.toGenericString()+":");
        
        
        
        ClassDesc cd = new ClassDesc(mainClass);
        cd.show();
        Set<Class> classes = cd.getClasses();
        
        //Avoid Main Class methods.
        classes.remove(mainClass);
        
        
        // Go through each class.
        for (Class cls : classes) {
            
           // System.out.println(cls);
            
            //Go through Each Method.
            Set<Met> methods = ExtMet.getAllMethods(cls);
            mainMethods.removeAll(methods);
            
//            for (Met m : methods) {
//                System.out.println("\t"+m);
//            }
        }
        List<Met> ls = new ArrayList<>(mainMethods);
        Collections.sort(ls, new SortByName());
        
        for (Met mM : ls) {
            if(mM.getModifier().contains("public") && !mM.getModifier().contains("default")){
                System.out.println("\t"+mM);
            }
        }
                
    }
}
