package com.suji.cls;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

interface Y{
    void y1();
}


interface X{
    void x1();
}
class W{
    void w1(){}
}
class Z extends W implements X,Y{
    public void m2(){
        
    }
    public void x1(){}
    public void y1(){}
}







public class GetMethods {

    public static List<Met> getMethods(Class cls) {
        
        Set<Met> set = ownMethods(cls);
        List<Met> list = new ArrayList<>(set);
        Collections.sort(list, new SortByName());
        
        
        return list;
    }
    
    public static Set<Met> ownMethods(Class c){
        
        
        
        
        
        
        Set<Met> metsList = ClassMethods.getMethods(c);
        //System.out.println(metsList);

        Set<Met> mL = new HashSet<>();
        Class[] classes = c.getInterfaces();
        for (Class cls : classes) {
           // System.out.println(cls);
            Set<Met> intList = ClassMethods.getMethods(cls);
           // mL.addAll(intList);
            metsList.removeAll(intList);
        }
       // System.out.println(metsList);
       
        Class sup = c.getSuperclass();
        if(sup!=null){
            Set<Met> supList = ClassMethods.getMethods(sup);
            metsList.removeAll(supList);
        }
        
        
      
            Set<Met> supList = ClassMethods.getMethods(Object.class);
            metsList.removeAll(supList);
        
      
        return metsList;
    }
    
   

}
