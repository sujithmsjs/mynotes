package com.util.recursive;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class ClassDesc {

    private Class cls;
    private Set<Class> classes;
    
    public ClassDesc(Class cls) {
        this.cls = cls;
        classes = new HashSet<Class>();
        classes.add(cls);
    }
    
    void show(){
        classes.clear();
        m2(cls);
    }

    public Set<Class> getClasses() {
        classes.clear();
        m2(cls);
        return classes;
    }
    
    void m2(Class cls) {
        if (cls != null) {
            //Get classes and Interfaces.
            Class supCls = cls.getSuperclass();
            Class[] intrs = cls.getInterfaces();

            if (supCls != null) {
                
                classes.add(supCls);
               // System.out.println("Super class: " + cls);
                
                m2(supCls);
            }
            for (Class intr : intrs) {
                classes.add(intr);
               // System.out.println("Interface : "+intr);
                m2(intr);
            }
        } else {
            Class[] c = cls.getInterfaces();
            for (Class cl : c) {
                m2(cl);
            }
           // System.out.println("Class is null " + cls);
        }
    }
    
    
    
    
    

}
